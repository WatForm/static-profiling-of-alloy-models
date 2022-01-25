package com.alloyprofiling.characteristics.multifile.constraints;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.FunctionRetriever;
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

public class Functions {
    //class attribute containing the total number of functions across all models
    private static int totFuncDecls = 0;
    //class attribute for total number of functions uses across all models
    private static int totFuncUses = 0;
    //class attribute containing the total number of models that contain function declarations
    private static int totFuncModels = 0;
    //class attribute containing the total number of function uses in commands
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
        System.out.println("Counting function decls/uses in Alloy models in " + path);


        //file containing the number of function declarations in each model
        String fp_functions = directoryName + "functionDecls.txt";
        //file containing the number of function calls for each model
        String fp_funUseCmds = directoryName + "functionUsesCmds.txt";
        String fp_funUseExprs = directoryName + "functionUsesExprs.txt";

        /*
        //file containing the number of function declarations in each model
        String fp_functions = directoryName + "functionDecls_expert.txt";
        //file containing the number of function calls for each model
        String fp_funUseCmds = directoryName + "functionUsesCmds_expert.txt";
        String fp_funUseExprs = directoryName + "functionUsesExprs_expert.txt";
         */


        //deleting results file if it already exists
        try {
            Files.deleteIfExists(Paths.get(fp_funUseCmds));
            Files.deleteIfExists(Paths.get(fp_funUseExprs));
            Files.deleteIfExists(Paths.get(fp_functions));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int funcDecls_model = 0;
            // list containing all function names in the model
            List<String> functions_model = new ArrayList<>();
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

                        //XPath string to get function declaration parse trees
                        Collection<ParseTree> funcTrees =  XPath.findAll(tree, "//funDecl", parser);

                        //using FunctionRetriever to get a list of function names in a model
                        List<String> functions = FunctionRetriever.getFunctions(parser, tree);

                        //adding function in each file to model function list
                        functions_model.addAll(functions);

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

                        System.out.println("Number of function declarations in file: " + funcTrees.size());

                        //incrementing model counter for function declarations
                        funcDecls_model += funcTrees.size();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            //filter checks if the collected names in commands are functions
            List<String> funUses_cmds = cmdNames_model.stream().filter(functions_model::contains).collect(Collectors.toList());

            //filter checks if the collected names in expressions are functions
            List<String> funUses_exprs = exprNames_model.stream().
                    filter(functions_model::contains).collect(Collectors.toList());

            int funcUses_model = funUses_cmds.size() + funUses_exprs.size();

            System.out.println("Model: " + model);
            System.out.println("Number of function declarations in model: " + funcDecls_model);
            System.out.println("Function uses in model: " + funcUses_model);
            System.out.println("Function uses in commands in model: " + funUses_cmds.size());
            System.out.println("Function uses in formulas in model: " + funUses_exprs.size() + "\n");

            //incrementing total counters (over all models)
            totFuncDecls += funcDecls_model;
            totFuncUses += funcUses_model;
            totUsesCmds += funUses_cmds.size();
            totUsesExprs += funUses_exprs.size();

            if (funcDecls_model > 0)
                totFuncModels++;

            //writing result file containing the number of functions in each model
            ResultWriter.writeResults(fp_functions, funcDecls_model);
            //writing result file containing the number of function uses in each model
            ResultWriter.writeResults(fp_funUseCmds, funUses_cmds.size());
            ResultWriter.writeResults(fp_funUseExprs, funUses_exprs.size());


        }
        System.out.println("Total number of functions: " + totFuncDecls);
        System.out.println("Total function use: " + totFuncUses);
        System.out.println("Total function uses in commands: " + totUsesCmds);
        System.out.println("Total function uses in formulas: " + totUsesExprs);
        System.out.println("Total number of models with functions: " + totFuncModels);
    }
}
