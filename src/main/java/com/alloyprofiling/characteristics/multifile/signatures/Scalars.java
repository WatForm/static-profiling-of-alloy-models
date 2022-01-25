package com.alloyprofiling.characteristics.multifile.signatures;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.EnumRetriever;
import com.alloyprofiling.retrievers.SigRetriever;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

//class that computes the number of scalars in Alloy models
public class Scalars {
    //counter for total number of one sigs across all models
    private static int totOne = 0;
    //counter for total number of enums across all models
    private static int totEnum = 0;

    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\CharacteristicsOfModels\\MultiFile\\Signatures\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (!directory.exists()){
            directory.mkdirs();
        }

        //repository of models
        String path = "corpus";
        //String path = "expert";
        System.out.println("Counting scalars in Alloy models in " + path);

        //file containing the number of one sigs per models
        String fp_oneSig = directoryName + "oneSigs.txt";
        //String fp_oneSig = directoryName + "oneSigs_expert.txt";
        //file containing number of enum (extensions) per model
        String fp_enum = directoryName + "enums.txt";
        //String fp_enum = directoryName + "enums_expert.txt";

        //deleting result files if they already exists
        try {
            Files.deleteIfExists(Paths.get(fp_oneSig));
            Files.deleteIfExists(Paths.get(fp_enum));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over repository of models
        while (sc.hasNextLine()) {
            int totalOne_model = 0;
            int totalEnum_model = 0;
            String model = sc.nextLine();
            String[] files_to_parse = model.split(",");
            for (String file: files_to_parse) {
                Path  file_path = Paths.get(file);
                if (Files.isRegularFile(file_path)) {
                    try {
                        String source = Files.readString(file_path);

                        ALLOYLexer lexer = new ALLOYLexer(CharStreams.fromString(source));
                        ALLOYParser parser = new ALLOYParser(new CommonTokenStream(lexer));
                        ParseTree tree = parser.specification();

                        System.out.println("File: " + file_path.toFile());

                        //extracting all signatures with multiplicity one using SigRetriever
                        List<String> oneSigs = SigRetriever.getSigs("one", parser, tree);

                        //calling EnumRetriever to get enum (extensions)
                        List<String> enumList = EnumRetriever.getEnums("extensions", parser, tree);

                        totalOne_model += oneSigs.size();
                        totalEnum_model += enumList.size();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Number of One Sigs: " + totalOne_model);
            System.out.println("Number of Enums: " + totalEnum_model + "\n");

            //incrementing counters over all models
            totOne += totalOne_model;
            totEnum += totalEnum_model;

            //writing result files containing the number of one sigs and enums in each file
            ResultWriter.writeResults(fp_oneSig, totalOne_model);
            ResultWriter.writeResults(fp_enum, totalEnum_model);
        }
        System.out.println("Total number of one sigs: " + totOne);
        System.out.println("Total number of enums: " + totEnum);    }
}
