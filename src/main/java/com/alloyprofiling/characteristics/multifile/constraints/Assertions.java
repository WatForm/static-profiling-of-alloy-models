package com.alloyprofiling.characteristics.multifile.constraints;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.AssertionRetriever;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
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

public class Assertions {
    //class attribute containing the total number of asserts across all models
    private static int totAsserts = 0;
    //class attribute containing the total number of assert uses across all models
    private static int totAssertUses = 0;
    //class attribute containing number of assertions without a name across all models
    private static int totAssertNoName = 0;
    //class attribute containing number of models that have assertions
    private  static  int totAssertModels = 0;


    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\CharacteristicsOfModels\\MultiFile\\Constraints\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //repository of models
        String path = "corpus";
        //String path = "expert";

        System.out.println("Counting assert decls/uses in Alloy models in " + path);

        //file containing the number of assertion declarations in each model
        String fp_assertions = directoryName + "assertDecls.txt";
        //file containing the number of assertion calls in each model
        String fp_assertUses = directoryName + "assertUses.txt";

        /*
        //file containing the number of assertion declarations in each model
        String fp_assertions = directoryName + "assertDecls_expert.txt";
        //file containing the number of assertion calls in each model
        String fp_assertUses = directoryName + "assertUses_expert.txt"; */

        //deleting result files if they already exist
        try {
            Files.deleteIfExists(Paths.get(fp_assertUses));
            Files.deleteIfExists(Paths.get(fp_assertions));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int assertDecls_model = 0;
            int assertNoName_model = 0;
            // list containing all assertion names in the model
            List<String> assertions_model = new ArrayList<>();
            // list containing all names used in commands in the model
            List<String> cmdNames_model = new ArrayList<>();
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

                        ParseTreePattern pattern_noName = parser.compileParseTreePattern(
                                "assert <block>", ALLOYParser.RULE_assertDecl);
                        List<ParseTreeMatch> matches_noName = pattern_noName.findAll(tree, "//paragraph/*");

                        List<String> assertNoNames = matches_noName.stream().map(m -> m.getTree().getText()).
                                collect(Collectors.toList());

                        //XPath string to extract assert declaration parse trees
                        Collection<ParseTree> assertTrees = XPath.findAll(tree, "//assertDecl", parser);

                        //getting all assertion names from the model using AssertionRetriever
                        List<String> assertions = AssertionRetriever.getAssertions(parser, tree);

                        //adding assertions in each file to model assertion list
                        assertions_model.addAll(assertions);

                        //pattern to find assertion names under command declarations
                        List<String> names_cmd = XPath.findAll(tree, "//cmdDecl/nameOrBlock/name",
                                parser).stream().map(ParseTree::getText).collect(Collectors.toList());

                        //adding names used in commands to model list
                        cmdNames_model.addAll(names_cmd);

                        //printing results
                        System.out.println("Number of assertion declarations in file: " + assertTrees.size());
                        System.out.println("Assertions declared without a name in file: " + assertNoNames.size());

                        //incrementing model counters
                        assertDecls_model += assertTrees.size();
                        assertNoName_model += assertNoNames.size();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            //filter checks if the collected names are assertions
            List<String> assertUses = cmdNames_model.stream().
                    filter(assertions_model::contains).collect(Collectors.toList());
            System.out.println("Model: " + model);
            System.out.println("Number of assertion declarations in model: " + assertDecls_model);
            System.out.println("Assertion uses in model: " + assertUses.size());
            System.out.println("Assertions with no name in model: " + assertNoName_model + "\n");

            //incrementing total counters (over all models)
            totAsserts += assertDecls_model;
            totAssertUses += assertUses.size();
            totAssertNoName += assertNoName_model;

            if (assertDecls_model > 0)
                totAssertModels++;

            //writing result file containing the number of assertion declarations in each file
            ResultWriter.writeResults(fp_assertions, assertDecls_model);
            //writing result file containing the number of assertion uses in each file
            ResultWriter.writeResults(fp_assertUses, assertUses.size());

        }
        System.out.println("Total number of assertion declarations: " + totAsserts);
        System.out.println("Total number of assertions uses: " + totAssertUses);
        System.out.println("Total number of assertions declared without a name: " + totAssertNoName);
        System.out.println("Models that use assertions: " + totAssertModels);
    }
}
