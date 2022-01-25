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

//class that counts subset signatures in Alloy models
public class SubsetSigs {
    //class attribute containing total number of subset signatures across all models
    private static int totSubsets = 0;

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

        System.out.println("Counting subset signature declarations in Alloy models in " + path);

        //file containing the top-level signature count for each model
        String fp_subsetSigs = directoryName + "subSigs.txt";
        //String fp_subsetSigs = directoryName + "subSigs_expert.txt";

        //deleting results file if it already exist
        try {
            Files.deleteIfExists(Paths.get(fp_subsetSigs));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        while (sc.hasNextLine()) {
            int totalSub_model = 0;
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

                        //extracting the names of subset signatures using SigRetriever
                        List<String> subsets = SigRetriever.getSigs("subsets", parser, tree);

                        //incrementing total counter for model
                        totalSub_model += subsets.size();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Subset Signatures: " + totalSub_model + "\n");
            totSubsets += totalSub_model;

            //writing to result file containing the number of subset signatures in each model
            ResultWriter.writeResults(fp_subsetSigs, totalSub_model);
        }
        System.out.println("Total number of subset signatures: " + totSubsets );
    }
}
