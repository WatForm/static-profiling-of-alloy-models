package com.alloyprofiling.characteristics.multifile.length;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Computes the length (not including blank lines) of Alloy models in a given repository
public class ModelLength {
    private static int multiFileModels = 0;
    private static String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(path);
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size());
            /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
        } finally {
            stream.close();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\CharacteristicsOfModels\\MultiFile\\Length\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
        }

        //repository of models
        String path = "corpus";
        System.out.println("Counting non-empty lines (excluding comments) for Alloy models in  " + path);

        //file containing the number of lines in each model
        String fp_lines = directoryName + "length.txt";
        //deleting results file if it already exists
        try {
            Files.deleteIfExists(Paths.get(fp_lines));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        while (sc.hasNextLine()) {
            long totalModelLength = 0;
            String model = sc.nextLine();
            String[] files_to_parse = model.split(",");
            if (files_to_parse.length > 1) {
                multiFileModels++;
            }
            for (String file : files_to_parse) {
                Path filePath = Paths.get(file);
                if (Files.isRegularFile(filePath)) {
                    try {
                        System.out.println("File: " + filePath.toFile());

                        String s = readFile(String.valueOf(filePath));
                        Pattern p = Pattern.compile("/\\*[\\s\\S]*?\\*/|(//)[^\\n\\r]*[\\n\\r]|(--)[^\\n\\r]*[\\n\\r]");

                        Matcher m = p.matcher(s);

                        int comments = 0;
                        while (m.find()) {

                            String lines[] = m.group(0).split("\n");
                            for (String string : lines) {
                                if (!string.isBlank())
                                    comments++;
                            }
                        }

                        //counting non-empty lines in alloy model
                        long count = Files.lines(Paths.get(String.valueOf(filePath))).filter(line -> line.length() > 0).
                                count();

                        System.out.println("File Length w/ comments: " + count);
                        System.out.println("Total lines for comments in file: " + comments);
                        long length = count - comments;
                        System.out.println("File Length: " + length);

                        totalModelLength += length;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Model Length: " + totalModelLength);

            //writing results file containing the number of lines in each model
            try {
                FileWriter lengthWriter = new FileWriter(fp_lines, true);
                lengthWriter.write(Long.toString(totalModelLength) + '\n');
                lengthWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        System.out.println("Multifile models: " + multiFileModels);
    }

    public static long getLength(String path) throws IOException {
        String s = readFile(path);
        Pattern p = Pattern.compile("/\\*[\\s\\S]*?\\*/|(//)[^\\n\\r]*[\\n\\r]|(--)[^\\n\\r]*[\\n\\r]");

        Matcher m = p.matcher(s);

        int total = 0;
        while (m.find()) {

            String lines[] = m.group(0).split("\n");
            for (String string : lines) {
                if (!string.isBlank())
                    total++;
            }
        }

        //counting non-empty lines in alloy model
        long count = Files.lines(Paths.get(path)).filter(line -> line.length() > 0).
                count();
        long length = count - total;

        return length;
    }
}
