package com.alloyprofiling.characteristics.multifile.signatures;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
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

//class that counts the number of abstract signatures in Alloy models
public class AbstractSigs {
    //counter for total number of abstract signatures (across all models)
    private static int totalAbsCount = 0;

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
        System.out.println("Counting abstract signature declarations in Alloy models in " + path);

        //file containing the number of abstract sigs in each model
        String fp_absSigs = directoryName + "absSigs.txt";
        //String fp_absSigs = directoryName + "absSigs_expert.txt";

        //deleting results file if it already exist
        try {
            Files.deleteIfExists(Paths.get(fp_absSigs));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int totalAbs_model = 0;
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

                        //getting names of abstract signatures using SigRetriever
                        List<String> absSigs = SigRetriever.getSigs("abstract", parser, tree);

                        //incrementing total counter for model
                        totalAbs_model += absSigs.size();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Number of Abstract Signatures: " + totalAbs_model + "\n");
            totalAbsCount += totalAbs_model;

            //writing to result file containing total number of top-level signatures in each model
            ResultWriter.writeResults(fp_absSigs, totalAbs_model);
        }
        System.out.println("Total number of Abstract Signatures: " + totalAbsCount);
    }
}
