package com.alloyprofiling.characteristics.multifile.constraints;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.PredicateRetriever;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Predicates {
    //class attribute containing the total number of predicates across all models
    private static int totPredDecls = 0;
    //class attribute containing the total number of predicate uses across all models
    private static int totPredUses = 0;
    //class attribute containing the total number of models that contain predicate declarations
    private static int totPredModels = 0;
    //class attribute containing the total number of predicate uses in commands
    private static int totUsesCmds = 0;
    //class attribute containing the total number of function uses in formulas
    private static int totUsesExprs = 0;

    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\CharacteristicsOfModels\\MultiFile\\Constraints\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
        }

        //repository of models
        String path = "corpus";
        //String path = "expert";
        System.out.println("Counting predicate decls/uses in Alloy models in " + path);


        //file containing the number of predicate declarations in each model
        String fp_predicates = directoryName + "predicateDecls.txt";
        //file containing the number of predicate calls in commands in each model
        String fp_predUseCmds = directoryName + "predicateUsesCmds.txt";
        //file containing the number of predicate calls in formulas in each model
        String fp_predUseExprs = directoryName + "predicateUsesExprs.txt";

        /*

        //file containing the number of predicate declarations in each model
        String fp_predicates = directoryName + "predicateDecls_expert.txt";
        //file containing the number of predicate calls in commands in each model
        String fp_predUseCmds = directoryName + "predicateUsesCmds_expert.txt";
        //file containing the number of predicate calls in formulas in each model
        String fp_predUseExprs = directoryName + "predicateUsesExprs_expert.txt"; */

        //deleting result files if they already exist
        try {
            Files.deleteIfExists(Paths.get(fp_predUseCmds));
            Files.deleteIfExists(Paths.get(fp_predUseExprs));
            Files.deleteIfExists(Paths.get(fp_predicates));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int predDecls_model = 0;
            // list containing all predicate names in the model
            List<String> predicates_model = new ArrayList<>();
            // list containing all names used in commands in the model
            List<String> cmdNames_model = new ArrayList<>();
            // list containing all names used in expressions in the model
            List<String> exprNames_model = new ArrayList<>();
            String model = sc.nextLine();
            String[] files_to_parse = model.split(",");
            for (String file : files_to_parse) {
                Path file_path = Paths.get(file);
                if (Files.isRegularFile(file_path)) {
                    try {
                        String source = Files.readString(file_path);

                        ALLOYLexer lexer = new ALLOYLexer(CharStreams.fromString(source));
                        ALLOYParser parser = new ALLOYParser(new CommonTokenStream(lexer));
                        ParseTree tree = parser.specification();

                        System.out.println("File: " + file_path.toFile());

                        //XPath string to extract predicate declaration parse trees
                        Collection<ParseTree> predTrees = XPath.findAll(tree, "//predDecl", parser);

                        //getting all predicate names from the model using PredicateRetriever
                        List<String> predicates = PredicateRetriever.getPredicates(parser, tree);

                        //adding predicates in each file to model predicate list
                        predicates_model.addAll(predicates);

                        //XPath hierarchy path to find names under commands
                        List<String> names_cmd = XPath.findAll(tree, "//cmdDecl/nameOrBlock/name",parser).stream()
                                .map(ParseTree::getText).collect(Collectors.toList());

                        //adding names used in commands to model list
                        cmdNames_model.addAll(names_cmd);

                        //pattern to find names in all expressions
                        List<String> names_expr =  XPath.findAll(tree, "//expr//name", parser).stream()
                                .map(ParseTree::getText).collect(Collectors.toList());

                        //adding names used in expressions to model list
                        exprNames_model.addAll(names_expr);

                        System.out.println("Number of predicate declarations in file: " + predTrees.size());

                        //incrementing model counters
                        predDecls_model += predTrees.size();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            //filter checks if the collected names are predicates
            List<String> predUses_cmds = cmdNames_model.stream().filter(predicates_model::contains).collect(Collectors.toList());

            //filter checks if the collected names are predicates
            List<String> predUses_expr = exprNames_model.stream().
                    filter(predicates_model::contains).collect(Collectors.toList());

            //total number of predicate uses in a file:
            //predicate calls in commands + predicate calls in expressions
            int predUses_model = predUses_cmds.size() + predUses_expr.size();

            System.out.println("Model: " + model);
            System.out.println("Number of predicate declarations in model: " + predDecls_model);
            System.out.println("Predicate uses in model: " + predUses_model);
            System.out.println("Predicate uses in commands in model: " + predUses_cmds.size());
            System.out.println("Predicate uses in formulas in model: " + predUses_expr.size() + "\n");

            //incrementing total counters (over all models)
            totPredDecls += predDecls_model;
            totPredUses += predUses_model;
            totUsesCmds += predUses_cmds.size();
            totUsesExprs += predUses_expr.size();

            if (predDecls_model > 0)
                totPredModels++;

            //writing result file containing the number of predicates in each model
            ResultWriter.writeResults(fp_predicates, predDecls_model);
            //writing result file containing the number of predicate uses in each model
            ResultWriter.writeResults(fp_predUseCmds, predUses_cmds.size());
            ResultWriter.writeResults(fp_predUseExprs, predUses_expr.size());
        }
        System.out.println("Total number of predicates: " + totPredDecls);
        System.out.println("Total predicate use: " + totPredUses);
        System.out.println("Total predicate uses in commands: " + totUsesCmds);
        System.out.println("Total predicate uses in formulas: " + totUsesExprs);
        System.out.println("Total models that have predicates: " + totPredModels);
    }
}
