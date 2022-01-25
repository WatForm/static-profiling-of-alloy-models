package com.alloyprofiling.patternsofuse.multifile.sets;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ModuleParamsBuilder;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.EnumRetriever;
import com.alloyprofiling.retrievers.SigRetriever;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

//class that count the number of signatures in Alloy models
public class NonOneSigExtsInFormulas {
    //counter for total number of signatures (across all models)
    private static int totNonOneExtExprs = 0;
    private static int totSigExprs = 0;

    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\PatternsOfUse\\MultiFile\\Sets\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (!directory.exists()){
            directory.mkdirs();
        }

        //repository of models
        String path = "corpus";
        System.out.println("Counting signature declarations in Alloy models in " + path);

        //file containing the signature count for each model
        String fp_nonOneExtExprs = directoryName + "nonOneExtExprs_multifile.txt";

        //deleting results file if it already exists
        try {
            Files.deleteIfExists(Paths.get(fp_nonOneExtExprs));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // pass the path to the file as a parameter
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        /* map where the key is the string "importing file, imported file" and the value is a list containing the
         the parameters to be substituted in the imported file */
        LinkedHashMap<String, List<String>> importingImported_params = ModuleParamsBuilder.buildImportingImportedParamsMap();

        while (sc.hasNextLine()) {
            String model = sc.nextLine();
            String[] files_to_parse = model.split(",");
            List<String> modelSigs = new ArrayList<>();
            List<String> modelOneSigExts = new ArrayList<>();
            List<String> modelNamesInExprs = new ArrayList<>();
            List<String> modelNonOneExtExprs = new ArrayList<>();

            // map that relates each parsed file to its parse tree
            LinkedHashMap<ALLOYParser, ParseTree> parser_tree_map = new LinkedHashMap<>();
            // the relevant keys are the ones that are in the model file string
            List<String> relevant_keys = importingImported_params.keySet().stream().
                    filter(k -> model.contains(k)).collect(Collectors.toList());
            // map that relates parameters in an imported file to their substitutions from the importing file
            LinkedHashMap<String, String> param_sub_map = new LinkedHashMap<>();
            // list containing files where a substitution is needed
            List<String> files_with_sub = new ArrayList<>();

            for (String file: files_to_parse) {
                Path  file_path = Paths.get(file);
                if (Files.isRegularFile(file_path)) {
                    try {
                        String source = Files.readString(file_path);

                        ALLOYLexer lexer = new ALLOYLexer(CharStreams.fromString(source));
                        ALLOYParser parser = new ALLOYParser(new CommonTokenStream(lexer));
                        ParseTree tree = parser.specification();

                        System.out.println("File: " + file_path.toFile());

                        //iterating over relevant keys if any
                        for (String key: relevant_keys) {
                            String imported_file = key.split(",")[1];
                            if (file.equals(imported_file)) {
                                System.out.println("Relevant key: " + key + " " + importingImported_params.get(key));

                                // getting parameters from module declarations
                                List<String> params = XPath.findAll(tree,"//param_module/name", parser)
                                        .stream().map(ParseTree::getText).collect(Collectors.toList());

                                // mapping each parameter to its substitution
                                int index = 0;
                                for(String p: params) {
                                    param_sub_map.put(p,importingImported_params.get(key).get(index));
                                    files_with_sub.add(tree.getText()+importingImported_params.get(key).get(index));
                                    index++;
                                }
                                param_sub_map.forEach((p1, p2) -> System.out.println("substitute " + p2 + " for " + p1));
                            }
                        }

                        //getting all signature names in the model using SigRetriever
                        List<String> allSigs = SigRetriever.getSigs("all", parser, tree);
                        //getting all enum names in the model using EnumRetriever
                        List<String> enums = EnumRetriever.getEnums("all", parser, tree);
                        //getting all one sig extensions in the model using SigRetriever
                        List<String> oneSigExts = SigRetriever.getSigs("oneextensions", parser, tree);

                        //extracting all signature names in formulas
                        List<String> namesInExprs = XPath.findAll(tree,"//expr//name", parser)
                                .stream().map(ParseTree::getText).collect(Collectors.toList());

                        List<String> namesInExprsSub = new ArrayList<>();
                        for (String name: namesInExprs) {
                            if (param_sub_map.containsKey(name))
                                namesInExprsSub.add(param_sub_map.get(name));
                            else
                                namesInExprsSub.add(name);
                        }

                        modelSigs.addAll(allSigs);
                        modelSigs.addAll(enums);
                        modelNamesInExprs.addAll(namesInExprsSub);
                        modelOneSigExts.addAll(oneSigExts);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            List<String> modelSigsInExprs = modelNamesInExprs.stream().filter(n -> modelSigs.contains(n))
                            .collect(Collectors.toList());

            totSigExprs += modelSigsInExprs.size();

            modelNonOneExtExprs.addAll(modelSigsInExprs.stream().filter(n -> !modelOneSigExts.contains(n)).
                    collect(Collectors.toList()));
            //incrementing counter (across all models)
            totNonOneExtExprs += modelNonOneExtExprs.size();
            System.out.println("Model: " + model);
            System.out.println("Number of signatures: " + modelNonOneExtExprs.size() + " " + modelNonOneExtExprs + "\n");

            //writing to result file containing total number of signatures in each model
            ResultWriter.writeResults(fp_nonOneExtExprs, modelNonOneExtExprs.size());
        }
        System.out.println("Total signatures used in formulas: " + totSigExprs);
        System.out.println("Total non-one sig extensions used in formulas: " + totNonOneExtExprs);
    }
}
