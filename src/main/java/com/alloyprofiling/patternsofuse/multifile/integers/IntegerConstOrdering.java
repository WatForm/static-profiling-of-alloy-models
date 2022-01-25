package com.alloyprofiling.patternsofuse.multifile.integers;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
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
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//class that counts the number of integer constants that can be replaced by an ordering
public class IntegerConstOrdering {
    //counter for the total number of integer constants that can be replaced by an ordering
    private static int totIntOrd = 0;
    //counter for the total number of integer constants that CANNOT be replaced by an ordering
    private static int totIntNoOrd = 0;
    //counter for the total number of integer constants
    private static int totConstants = 0;
    private static int totNotClassified = 0;
    private static int totConstNum = 0;


    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\PatternsOfUse\\MultiFile\\Integers\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
        }

        String path = "corpus";
        //String path = "expert";
        System.out.println("Counting integer constants that can/cannot be turned into ordering in Alloy models in " + path);


        //file containing number of int constants that can be turned into an ordering
        String fp_intOrdering = directoryName + "intConstOrdering.txt";
        //file containing number of int constants that cannot be turned into an ordering
        String fp_intNoOrdering = directoryName + "intConstNoOrdering.txt";

        //file containing number of int constants used with relational operators exclusively (with #)
        String fp_intConstRelSC = directoryName + "intConstRelSC.txt";
        //file containing number of int constants used with relational operators exclusively (no #)
        String fp_intConstRelNoSC = directoryName + "intConstRelNoSC.txt";
        //file containing number of int constants used with numeric operators
        String fp_intConstNum = directoryName + "intConstNum.txt";

        /*
        //file containing number of int constants that can be turned into an ordering
        String fp_intOrdering = directoryName + "intConstOrdering_expert.txt";
        //file containing number of int constants that cannot be turned into an ordering
        String fp_intNoOrdering = directoryName + "intConstNoOrdering_expert.txt";

        //file containing number of int constants used with relational operators exclusively (with #)
        String fp_intConstRelSC = directoryName + "intConstRelSC_expert.txt";
        //file containing number of int constants used with relational operators exclusively (no #)
        String fp_intConstRelNoSC = directoryName + "intConstRelNoSC_expert.txt";
        //file containing number of int constants used with numeric operators
        String fp_intConstNum = directoryName + "intConstNum_expert.txt"; */


        //deleting result files if they already exist
        try {
            Files.deleteIfExists(Paths.get(fp_intOrdering));
            Files.deleteIfExists(Paths.get(fp_intNoOrdering));

            Files.deleteIfExists(Paths.get(fp_intConstRelSC));
            Files.deleteIfExists(Paths.get(fp_intConstRelNoSC));
            Files.deleteIfExists(Paths.get(fp_intConstNum));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // reading the file containing the models
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        // iterating over models
        while (sc.hasNextLine()) {
            // each line is a model that may have 1 or more comma-separated files
            String model = sc.nextLine();
            // array containing the files to parse
            String[] files_to_parse = model.split(",");
            List<ParseTreeMatch> matches_rel_model = new ArrayList<>();
            List<String> consts_from_num = new ArrayList<>();
            List<String> intConsts_model = new ArrayList<>();
            // iterating over files to parse
            for (String file : files_to_parse) {
                Path file_path = Paths.get(file);
                if (Files.isRegularFile(file_path)) {
                    try {
                        String source = Files.readString(file_path);

                        // parsing file and generating parse tree
                        ALLOYLexer lexer = new ALLOYLexer(CharStreams.fromString(source));
                        ALLOYParser parser = new ALLOYParser(new CommonTokenStream(lexer));
                        ParseTree tree = parser.specification();

                        // printing current file
                        System.out.println("File: " + file_path.toFile());

                        //XPath string to find num constants in expressions
                        List<String> intConsts =  XPath.findAll(tree, "//expr/constant/num", parser).stream().
                                map(ParseTree::getText).distinct().collect(Collectors.toList());

                        //adding file intConsts to the model intConsts list
                        intConsts_model.addAll(intConsts);

                        //pattern to find expressions with relational operators
                        ParseTreePattern p_rel = parser.compileParseTreePattern( "<expr> <notOp> <rel_operators> <expr>", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_rel = p_rel.findAll(tree, "//*");

                        //adding the file matches_rel to the model matches_rel list
                        matches_rel_model.addAll(matches_rel);

                        //pattern to find expressions with an addition operator
                        ParseTreePattern p_add_op = parser.compileParseTreePattern("<expr> <add> <expr>", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_add_op = p_add_op.findAll(tree, "//*");

                        //pattern to find expressions with a subtraction operator
                        ParseTreePattern p_sub_op = parser.compileParseTreePattern("<expr> <sub> <expr>", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_sub_op = p_sub_op.findAll(tree, "//*");

                        //pattern to find expressions with Integer functions from the integer module
                        //addition using add (dot form)
                        ParseTreePattern p_add_dot = parser.compileParseTreePattern("<expr>.add[<expr>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_add_dot = p_add_dot.findAll(tree, "//*");

                        //addition using add (box form)
                        ParseTreePattern p_add_box = parser.compileParseTreePattern("add[<exprs>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_add_box = p_add_box.findAll(tree, "//*");

                        //addition using plus (dot form)
                        ParseTreePattern p_plus_dot = parser.compileParseTreePattern("<expr>.plus[<expr>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_plus_dot = p_plus_dot.findAll(tree, "//*");

                        //addition using plus (box form)
                        ParseTreePattern p_plus_box = parser.compileParseTreePattern("plus[<exprs>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_plus_box = p_plus_box.findAll(tree, "//*");

                        //subtraction using sub (dot form)
                        ParseTreePattern p_sub_dot = parser.compileParseTreePattern("<expr>.sub[<expr>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_sub_dot = p_sub_dot.findAll(tree, "//*");

                        //subtraction using sub (box form)
                        ParseTreePattern p_sub_box = parser.compileParseTreePattern("sub[<exprs>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_sub_box = p_sub_box.findAll(tree, "//*");

                        //subtraction using minus (dot form)
                        ParseTreePattern p_minus_dot = parser.compileParseTreePattern("<expr>.minus[<expr>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_minus_dot = p_minus_dot.findAll(tree, "//*");

                        //subtraction using minus (box form)
                        ParseTreePattern p_minus_box = parser.compileParseTreePattern("minus[<exprs>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_minus_box = p_minus_box.findAll(tree, "//*");

                        //multiplication using mult (dot form)
                        ParseTreePattern p_mult_dot = parser.compileParseTreePattern("<expr>.mult[<expr>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_mult_dot = p_mult_dot.findAll(tree, "//*");

                        //multiplication using mult (box form)
                        ParseTreePattern p_mult_box = parser.compileParseTreePattern("mult[<exprs>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_mult_box = p_mult_box.findAll(tree, "//*");

                        //division using div (dot form)
                        ParseTreePattern p_div_dot = parser.compileParseTreePattern("<expr>.div[<expr>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_div_dot = p_div_dot.findAll(tree, "//*");

                        //division using div (box form)
                        ParseTreePattern p_div_box = parser.compileParseTreePattern("div[<exprs>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_div_box = p_div_box.findAll(tree, "//*");

                        //remainder using rem (dot form)
                        ParseTreePattern p_rem_dot = parser.compileParseTreePattern("<expr>.rem[<expr>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_rem_dot = p_rem_dot.findAll(tree, "//*");

                        //remainder using rem (box form)
                        ParseTreePattern p_rem_box = parser.compileParseTreePattern("rem[<exprs>]", ALLOYParser.RULE_expr);
                        List<ParseTreeMatch> matches_rem_box = p_rem_box.findAll(tree, "//*");

                        //concatenating all matches
                        List<ParseTreeMatch> matches_num =  Lists.newArrayList(Iterables.concat(matches_add_op, matches_sub_op,
                                matches_add_box, matches_add_dot, matches_plus_box, matches_plus_dot, matches_sub_box,
                                matches_sub_dot, matches_minus_dot, matches_minus_box, matches_mult_box,
                                matches_mult_dot, matches_div_box, matches_div_dot, matches_rem_box, matches_rem_dot));

                        for (ParseTreeMatch m: matches_num) {
                            consts_from_num.addAll(XPath.findAll(m.getTree(), "//constant/num", parser).
                                    stream().map(ParseTree::getText).collect(Collectors.toList()));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Model: " + model);

            //list of integer constants used with relational operators exclusively (no #)
            List<String> relConsts_no_sc = new ArrayList<>();
            //list of integer constants used with relational operators exclusively (with #)
            List<String> relConsts_sc = new ArrayList<>();
            //list of integer constants used with numeric operators
            List<String> numConsts = new ArrayList<>();


            //classifying integer constants
            for (String constant: intConsts_model) {
                if (consts_from_num.contains(constant))
                    numConsts.add(constant);

                if (matches_rel_model.stream().filter(m -> m.getTree().getText().contains("#")).
                        anyMatch(e-> e.getTree().getText().contains(constant)))
                    relConsts_sc.add(constant);

                else if (matches_rel_model.stream().filter(m -> !(m.getTree().getText().contains("#"))).
                        anyMatch(e-> e.getTree().getText().contains(constant)))
                    relConsts_no_sc.add(constant);
            }

            //combining integer constants used with relational operators exclusively
            List<String> relConsts = Stream.concat(relConsts_sc.stream(), relConsts_no_sc.stream())
                    .collect(Collectors.toList());

            //list of unclassified integer constants
            List<String> unclassified = intConsts_model.stream().filter(n-> !(relConsts.contains(n)) &&
                    !(numConsts.contains(n))).collect(Collectors.toList());

            //printing out unclassified integer fields
            if (!unclassified.isEmpty()){
                System.out.println("Not classified: " + unclassified.size() + " " + unclassified);
                totNotClassified += unclassified.size();
            }

            //list of integer constants that can be turned into an ordering
            List<String> intOrdering = relConsts.stream().filter(n -> !(numConsts.contains(n))).distinct().
                    collect(Collectors.toList());

            List<String> exc_rel_sc = relConsts_sc.stream().filter(n -> !(numConsts.contains(n))).distinct().
                    collect(Collectors.toList());

            List<String> exc_rel_no_sc = relConsts_no_sc.stream().filter(n -> !(numConsts.contains(n))).distinct().
                    collect(Collectors.toList());

            //printing out integer constants
            System.out.println("Int Constants: " + intConsts_model.size() + " " + intConsts_model);
            System.out.println("Int Constants used with relational operators exclusively (no #): " + " " + exc_rel_no_sc.size() + " " + exc_rel_no_sc);
            System.out.println("Int Constants used with relational operators exclusively (with #): " + " " + exc_rel_sc.size() + " " + exc_rel_sc);
            System.out.println("Int Constants used with numeric operators: " + " " + numConsts.size());

            //list of integer constants that cannot be turned into an ordering
            List<String> intNoOrdering =  Stream.concat(numConsts.stream(), unclassified.stream())
                    .distinct().collect(Collectors.toList());

            ResultWriter.writeResults(fp_intConstRelSC, exc_rel_sc.size());
            ResultWriter.writeResults(fp_intConstRelNoSC, exc_rel_no_sc.size());
            ResultWriter.writeResults(fp_intConstNum, intNoOrdering.size());

            List<String> intConsts_distinct = intConsts_model.stream().distinct().collect(Collectors.toList());

            if (intOrdering.size() + intNoOrdering.size() != intConsts_distinct.size())
                System.out.println("unequal");

            //incrementing total integer constant counter
            totConstants += intConsts_distinct.size();

            System.out.println("Constants that can be made into an ordering: " + intOrdering.size() + " " + intOrdering);
            System.out.println("Constants that cannot be made into an ordering: " + intNoOrdering.size() + " " + intNoOrdering + "\n");


            //incrementing counters
            totIntOrd += intOrdering.size();
            totIntNoOrd += intNoOrdering.size();
            totConstNum += numConsts.size();

            //writing result file containing the number of integer uses that can/cannot be made into an
            //ordering
            ResultWriter.writeResults(fp_intOrdering, intOrdering.size());
            ResultWriter.writeResults(fp_intNoOrdering, intNoOrdering.size());
        }
        //printing counters over all files
        System.out.println("Total constants that can be made into ordering: " + totIntOrd);
        System.out.println("Total constants that cannot be made into ordering: " + totIntNoOrd);
        System.out.println("Total Int Constants: " + totConstants);
        System.out.println("Total Unclassified Int Constants: " + totNotClassified);
        System.out.println("Total Int constants used with numeric operators: " + totConstNum);

    }
}
