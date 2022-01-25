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

//class that count the number of signatures in Alloy models
public class Signatures {
    //counter for total number of signatures (across all models)
    private static int totSigCount = 0;

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
        System.out.println("Counting signature declarations in Alloy models in " + path);

        //file containing the signature count for each model
        String fp_sigUse = directoryName + "sigs.txt";

        //deleting results file if it already exists
        try {
            Files.deleteIfExists(Paths.get(fp_sigUse));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over repository of models
        while (sc.hasNextLine()) {
            int sigs_model = 0;
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

                        //getting all signature names in the model using SigRetriever
                        List<String> totalSigs = SigRetriever.getSigs("all", parser, tree);
                        //getting all enum names in the model using EnumRetriever
                        List<String> enums = EnumRetriever.getEnums("all", parser, tree);

                        //total number of sets (i.e. sigs + enums)
                        int total = totalSigs.size() + enums.size();

                        //incrementing counter (across all models)
                        totSigCount += totalSigs.size() + enums.size();
                        sigs_model += total;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Number of signatures: " + sigs_model + "\n");

            //writing to result file containing total number of signatures in each model
            ResultWriter.writeResults(fp_sigUse, sigs_model);
        }
        System.out.println("Total sigs: " + totSigCount);
    }
}
