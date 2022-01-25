package com.alloyprofiling.analysiscomplexity.multifile.partialtotalfuncs;

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
import java.util.*;
import java.util.stream.Collectors;

public class OverallPartialFuncs {
    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\AnalysisComplexity\\MultiFile\\PartialTotalFunctions\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
        }
        String path = "corpus";
        //String path = "expert";
        System.out.println("Counting partial functions in Alloy models in " + path);

        //file containing the overall (including utility modules) number of partial formulas per model
        String fp_partFuncs = directoryName + "overallPartialFuncs.txt";
        //String fp_partFuncs = directoryName + "overallPartialFuncs_expert.txt";

        //deleting results file if it already exists
        try {
            Files.deleteIfExists(Paths.get(fp_partFuncs));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int partialFuncs_model = 0;
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

                        //XPath path string to extract all occurrences of arrowOp
                        Collection<ParseTree> arrowOpTrees = XPath.findAll(tree, "//arrowOp", parser);

                        //post-processing to pull out partial functions
                        int totPf_count = 0;
                        for(ParseTree t: arrowOpTrees) {
                            int pf_count = 0;
                            String fullDecl = t.getParent().getParent().getText();
                            if(t.getText().equals("->lone") && fullDecl.contains("->lone")){
                                String[] decl_split = fullDecl.split(":");
                                String[] rels_count = decl_split[0].split(",");
                                pf_count += rels_count.length;
                            }
                            totPf_count += pf_count;
                        }

                        //pattern to find total functions of the form sig A {f: one B}
                        ParseTreePattern p_lone = parser.compileParseTreePattern("<names> : lone <name>", ALLOYParser.RULE_decl);
                        List<ParseTreeMatch> matches_lone = p_lone.findAll(tree, "//decls/*");

                        ArrayList<String> allPF_lone = new ArrayList<>();

                        //post-processing to pull out all rel names i.e. f1, f2: B counts as 2 total functions
                        List<ParseTree> allNames_lone = matches_lone.stream().map(match -> match.get("names")).collect(Collectors.toList());
                        List<String> totalFuncs_lone = allNames_lone.stream().map(ParseTree::getText).collect(Collectors.toList());
                        for (String names : totalFuncs_lone) {
                            String[] namesList = names.split(",");
                            Collections.addAll(allPF_lone, namesList);
                        }
                        totPf_count += allPF_lone.size();

                        List<String> partial_funcs = Arrays.asList("isSeq", "seqElems", "int2elem",
                                "elem2int", "inc", "dec", "add","sub","mul", "div", "lastIdx", "afteLastIdx",
                                "idxOf", "lastIdxOf", "at");

                        List<String> var_funcs = Arrays.asList("max", "min", "first", "last");

                        List<String> names = XPath.findAll(tree, "//expr//name", parser).
                                stream().map(ParseTree::getText).collect(Collectors.toList());

                        List<String> pf =  names.stream().filter(n -> partial_funcs.stream().anyMatch(n::equals)).
                                distinct().collect(Collectors.toList());

                        totPf_count += pf.size();


                        for (String f: var_funcs) {
                            ParseTreePattern p = parser.compileParseTreePattern(f + " [<expr>]", ALLOYParser.RULE_expr);
                            List<ParseTreeMatch> matches = p.findAll(tree, "//*");
                            if (!matches.isEmpty())
                                totPf_count++;
                        }

                        System.out.println("Number of partial functions in file: " + totPf_count);

                        //incrementing model counter
                        partialFuncs_model += totPf_count;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Number of partial function in model: " + partialFuncs_model + "\n");

            //writing result file containing the number of partial functions in each model
            ResultWriter.writeResults(fp_partFuncs, partialFuncs_model);
        }
    }
}
