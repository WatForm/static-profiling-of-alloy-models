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

//class that count the number of top-level signatures in Alloy models
public class TopLevelSigs {
    //counter for total number of signatures (across all models)
    private static int totTopLevel = 0;

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
        System.out.println("Counting top-level signature declarations in Alloy models in " + path);

        //file containing the top-level signature count for each model
        String fp_topSigs = directoryName + "topSigs.txt";
        //String fp_topSigs = directoryName + "topSigs_expert.txt";


        //deleting results file if it already exists
        try {
            Files.deleteIfExists(Paths.get(fp_topSigs));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int totalTop_model = 0;
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

                        //getting names of all top-level sigs using SigRetriever
                        List<String> topSigs = SigRetriever.getSigs("top", parser, tree);
                        //getting names of top-level enums using SigRetriever
                        //enum Time [morning, noon, night] --> only Time is top-level
                        List<String> topEnums = EnumRetriever.getEnums("top", parser, tree);

                        //total number of top-level signatures in a file (i.e. top sig declarations + top enums)
                        int total = topSigs.size() + topEnums.size();

                        //incrementing total counter for model
                        totalTop_model += total;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Top-level Signatures: " + totalTop_model + "\n");
            totTopLevel += totalTop_model;

            //writing to result file containing total number of top-level signatures in each model
            ResultWriter.writeResults(fp_topSigs, totalTop_model);
        }
        System.out.println("Total number of top-level Signatures: " + totTopLevel);
    }
}
