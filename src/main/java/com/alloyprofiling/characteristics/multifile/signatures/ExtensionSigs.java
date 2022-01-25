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
import java.util.stream.Collectors;

//class that count the number of extension signatures in Alloy models
public class ExtensionSigs {
    //counter for total number of subsignature extensions (across all models)
    private static int totExt = 0;
    private static int totExtNonOne = 0;
    private static int totExtOne = 0;
    private static int totEnumExt = 0;

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
        System.out.println("Counting signature declarations in Alloy models in " + path);

        ///file containing the number of extension signatures in each model
        String fp_extSigs = directoryName + "extSigs.txt";
        //String fp_extSigs = directoryName + "extSigs_expert.txt";

        //deleting results file if it already exist
        try {
            Files.deleteIfExists(Paths.get(fp_extSigs));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // pass the path to the file as a parameter
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        while (sc.hasNextLine()) {
            int totalExt_model = 0;
            int totalExtOne_model = 0;
            int totalExtNonOne_model = 0;
            int totalEnumExt_model = 0;
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

                        //extracting the names of subsignature extensions using SigRetriever
                        List<String> extensionSigs = SigRetriever.getSigs("extensions", parser, tree);

                        //extracting enum extension using EnumRetriever
                        List<String> extensionEnums = EnumRetriever.getEnums("extensions", parser, tree);

                        //extracting one sigs using SigRetriever
                        List<String> oneSigs = SigRetriever.getSigs("one", parser, tree);

                        //identifying subsignature extensions that are not one sigs
                        List<String> extSigsNonOne = extensionSigs.stream().filter(s -> !(oneSigs.contains(s))).
                                collect(Collectors.toList());

                        //identifying subsignature extensions that are one sigs
                        List<String> extSigsOne = extensionSigs.stream().filter(s -> oneSigs.contains(s)).
                                collect(Collectors.toList());

                        //incrementing total counter for each model
                        totalExt_model += extensionSigs.size();
                        totalEnumExt_model += extensionEnums.size();
                        totalExtOne_model += extSigsOne.size();
                        totalExtNonOne_model += extSigsNonOne.size();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Subsignature extensions: " + totalExt_model);
            System.out.println("Enum extensions: " + totalEnumExt_model);
            System.out.println("Subsignature extensions that are not one sigs: " + totalExtNonOne_model);
            System.out.println("Subsignature extensions that are one sigs: " + totalExtOne_model + "\n");

            //incrementing counter over all models
            totExt += totalExt_model;
            totExtNonOne += totalExtNonOne_model;
            totExtOne += totalExtOne_model;
            totEnumExt += totalEnumExt_model;

            //writing to result file containing total number of subsignature extensions in each model
            ResultWriter.writeResults(fp_extSigs, totalExt_model);
        }
        System.out.println("Total number of sig extensions: " + totExt);
        System.out.println("Total number of enum sig extensions: " + totEnumExt);
        System.out.println("Total number of sig extensions that are not one sigs: " + totExtNonOne);
        System.out.println("Total number of sig extensions that are one sigs: " + totExtOne);
    }
}
