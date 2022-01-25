package com.alloyprofiling.characteristics.multifile.constraints;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
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
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Facts {
    private static int totFacts = 0;
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
        System.out.println("Counting number of fact blocks in Alloy models in " + path);

        //file containing the number of extension signatures in each model
        String fp_facts = directoryName + "facts.txt";
        //String fp_facts = directoryName + "facts_expert.txt";

        //deleting results file if it already exists
        try {
            Files.deleteIfExists(Paths.get(fp_facts));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int facts_model = 0;
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

                        //pattern to find signature declarations with a fact block
                        ParseTreePattern p_sigFacts= parser.compileParseTreePattern("<priv> <abs_multiplicity> sig <names> <sigExtension> { <decls> } <block>", ALLOYParser.RULE_sigDecl);
                        List<ParseTreeMatch> matches_sigFacts = p_sigFacts.findAll(tree, "//paragraph/*");

                        //excluding empty fact blocks and accounting for multiple signatures in one declaration
                        int sigFacts = 0;
                        for (ParseTreeMatch m: matches_sigFacts) {
                            String match_str = m.get("block").getText().replaceAll("\\s", "");
                            if (!match_str.equals("{}")) {
                                String[] namesList = m.get("names").getText().split(",");
                                sigFacts += namesList.length;
                            }
                        }

                        //XPath string to get fact block declarations
                        Collection<ParseTree> factTrees =  XPath.findAll(tree, "//factDecl", parser);

                        int factBlocks = sigFacts + factTrees.size();

                        System.out.println("Fact blocks in file: " + factBlocks);

                        facts_model += factBlocks;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            totFacts += facts_model;
            System.out.println("Model: " + model);
            System.out.println("Facts count: " + facts_model + "\n");

            ResultWriter.writeResults(fp_facts, facts_model);
        }
        System.out.println("Total number of fact blocks: " + totFacts);
    }
}
