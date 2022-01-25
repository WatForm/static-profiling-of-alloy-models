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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//class that counts the number of integer fields that can be replaced by an ordering
public class IntegerOrdering {
    //counter for the total number of integer fields that can be replaced by an ordering
    private static int totIntOrd = 0;
    //counter for the total number of integer fields that CANNOT be replaced by an ordering
    private static int totIntNoOrd = 0;
    //counter for the total number of integer fields
    private  static int totFields = 0;
    //counter for the integer fields that are not classified (i.e. declared but not used).
    private static int totNotClassified = 0;

    private static  List<String> scaleDuplicates(List<String> fields, List<String> duplicates) {
        List <String> scaledFields = fields.stream().collect(Collectors.toList());
        for (String f: fields) {
            if (duplicates.contains(f)) {
                for (int i=0; i<Collections.frequency(duplicates, f) - 1; i++) {
                    scaledFields.add(f);
                }
            }
        }
        return scaledFields;
    }


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
        System.out.println("Counting integer uses that can/cannot be turned into ordering in Alloy models in " + path);

        //file containing number of int fields that can be turned into an ordering
        String fp_intOrdering = directoryName + "intOrdering.txt";
        //file containing number of int fields that cannot be turned into an ordering
        String fp_intNoOrdering = directoryName + "intNoOrdering.txt";

        //file containing number of int fields used with relational operators exclusively (with #)
        String fp_intFieldRelSC = directoryName + "intFieldRelSC.txt";
        //file containing number of int fields used with relational operators exclusively (no #)
        String fp_intFieldRelNoSC = directoryName + "intFieldRelNoSC.txt";
        //file containing number of int fields used with numeric operators
        String fp_intFieldNum = directoryName + "intFieldNum.txt";
        //file containing number of unused int fields
        String fp_intFieldUnused = directoryName + "intFieldUnused.txt";

        /*
        //file containing number of int fields that can be turned into an ordering
        String fp_intOrdering = directoryName + "intOrdering_expert.txt";
        //file containing number of int fields that cannot be turned into an ordering
        String fp_intNoOrdering = directoryName + "intNoOrdering_expert.txt";


        //file containing number of int fields used with relational operators exclusively (with #)
        String fp_intFieldRelSC = directoryName + "intFieldRelSC_expert.txt";
        //file containing number of int fields used with relational operators exclusively (no #)
        String fp_intFieldRelNoSC = directoryName + "intFieldRelNoSC_expert.txt";
        //file containing number of int fields used with numeric operators
        String fp_intFieldNum = directoryName + "intFieldNum_expert.txt";
        //file containing number of unused int fields
        String fp_intFieldUnused = directoryName + "intFieldUnused_expert.txt"; */


        //deleting result files if they already exist
        try {
            Files.deleteIfExists(Paths.get(fp_intOrdering));
            Files.deleteIfExists(Paths.get(fp_intNoOrdering));

            Files.deleteIfExists(Paths.get(fp_intFieldRelSC));
            Files.deleteIfExists(Paths.get(fp_intFieldRelNoSC));
            Files.deleteIfExists(Paths.get(fp_intFieldNum));
            Files.deleteIfExists(Paths.get(fp_intFieldUnused));
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
            List<String> matches_num_model = new ArrayList<>();
            List<String> matches_rel_model = new ArrayList<>();
            List<String> intFields_model = new ArrayList<>();
            List<String> duplicates = new ArrayList<>();
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

                        //pattern to find field declarations with Int
                        ParseTreePattern p_intField_uppercase = parser.compileParseTreePattern("<priv> <disjoint> <names> : <disj> Int <comma_opt>", ALLOYParser.RULE_decl);
                        ParseTreePattern p_intField_lowercase = parser.compileParseTreePattern("<priv> <disjoint> <names> : <disj> int <comma_opt>", ALLOYParser.RULE_decl);

                        List<ParseTreeMatch> matches_intField_uppercase = p_intField_uppercase.findAll(tree, "//sigDecl//decls/*");
                        List<ParseTreeMatch> matches_intField_lowercase = p_intField_lowercase.findAll(tree, "//sigDecl//decls/*");

                        List<ParseTreeMatch> matches_intField = Stream.concat(matches_intField_lowercase.stream(), matches_intField_uppercase.stream()).
                                collect(Collectors.toList());

                        //post-processing to pull out all field names i.e. f1,f2: Int counts as 2
                        List<String> intFields = new ArrayList<>();
                        List<ParseTree> nameTrees = matches_intField.stream().map(match -> match.get("names")).collect(Collectors.toList());
                        List<String> fields = nameTrees.stream().map(ParseTree::getText).collect(Collectors.toList());

                        for (String names : fields) {
                            String[] namesList = names.split(",");
                            Collections.addAll(intFields, namesList);
                        }

                        //removing duplicates from the integer fields list
                        List<String> intFields_noDup = intFields.stream().distinct()
                                .collect(Collectors.toList());

                        //printing out all integer fields if list contains duplicates
                        if (!(intFields.equals(intFields_noDup))){
                            duplicates.addAll(intFields.stream().filter(f -> Collections.frequency(intFields, f)>1)
                                    .collect(Collectors.toList()));
                            System.out.println("DUPLICATES: " + duplicates);

                        }

                        //adding fine intFields to the model intFields list
                        intFields_model.addAll(intFields);

                        //pattern to find field names used with relational operators
                        ParseTreePattern p_rel = parser.compileParseTreePattern("<expr> <notOp> <rel_operators> <expr>", ALLOYParser.RULE_expr);
                        List<String> matches_rel = p_rel.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //adding matches_rel to the matches_rel model list
                        matches_rel_model.addAll(matches_rel);

                        //pattern to find field names used with an addition operator
                        ParseTreePattern p_add_op = parser.compileParseTreePattern("<expr> <add> <expr>", ALLOYParser.RULE_expr);
                        List<String> matches_add_op = p_add_op.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //pattern to find field names used with a subtraction operator
                        ParseTreePattern p_sub_op = parser.compileParseTreePattern("<expr> <sub> <expr>", ALLOYParser.RULE_expr);
                        List<String> matches_sub_op = p_sub_op.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //pattern to find field names used with Integer functions from the integer module
                        //addition using add (dot form)
                        ParseTreePattern p_add_dot = parser.compileParseTreePattern("<expr>.add[<expr>]", ALLOYParser.RULE_expr);
                        List<String> matches_add_dot = p_add_dot.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //addition using add (box form)
                        ParseTreePattern p_add_box = parser.compileParseTreePattern("add[<exprs>]", ALLOYParser.RULE_expr);
                        List<String> matches_add_box = p_add_box.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //addition using plus (dot form)
                        ParseTreePattern p_plus_dot = parser.compileParseTreePattern("<expr>.plus[<expr>]", ALLOYParser.RULE_expr);
                        List<String> matches_plus_dot = p_plus_dot.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //addition using plus (box form)
                        ParseTreePattern p_plus_box = parser.compileParseTreePattern("plus[<exprs>]", ALLOYParser.RULE_expr);
                        List<String> matches_plus_box = p_plus_box.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //subtraction using sub (dot form)
                        ParseTreePattern p_sub_dot = parser.compileParseTreePattern("<expr>.sub[<expr>]", ALLOYParser.RULE_expr);
                        List<String> matches_sub_dot = p_sub_dot.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //subtraction using sub (box form)
                        ParseTreePattern p_sub_box = parser.compileParseTreePattern("sub[<exprs>]", ALLOYParser.RULE_expr);
                        List<String> matches_sub_box = p_sub_box.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //subtraction using minus (dot form)
                        ParseTreePattern p_minus_dot = parser.compileParseTreePattern("<expr>.minus[<expr>]", ALLOYParser.RULE_expr);
                        List<String> matches_minus_dot = p_minus_dot.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //subtraction using minus (box form)
                        ParseTreePattern p_minus_box = parser.compileParseTreePattern("minus[<exprs>]", ALLOYParser.RULE_expr);
                        List<String> matches_minus_box = p_minus_box.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //multiplication using mult (dot form)
                        ParseTreePattern p_mult_dot = parser.compileParseTreePattern("<expr>.mult[<expr>]", ALLOYParser.RULE_expr);
                        List<String> matches_mult_dot = p_mult_dot.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //multiplication using mult (box form)
                        ParseTreePattern p_mult_box = parser.compileParseTreePattern("mult[<exprs>]", ALLOYParser.RULE_expr);
                        List<String> matches_mult_box = p_mult_box.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //division using div (dot form)
                        ParseTreePattern p_div_dot = parser.compileParseTreePattern("<expr>.div[<expr>]", ALLOYParser.RULE_expr);
                        List<String> matches_div_dot = p_div_dot.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //division using div (box form)
                        ParseTreePattern p_div_box = parser.compileParseTreePattern("div[<exprs>]", ALLOYParser.RULE_expr);
                        List<String> matches_div_box = p_div_box.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //remainder using rem (dot form)
                        ParseTreePattern p_rem_dot = parser.compileParseTreePattern("<expr>.rem[<expr>]", ALLOYParser.RULE_expr);
                        List<String> matches_rem_dot = p_rem_dot.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //remainder using rem (box form)
                        ParseTreePattern p_rem_box = parser.compileParseTreePattern("rem[<exprs>]", ALLOYParser.RULE_expr);
                        List<String> matches_rem_box = p_rem_box.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList());

                        //concatenating all matches
                        List<String> matches_num =  Lists.newArrayList(Iterables.concat(matches_add_op, matches_sub_op,
                                matches_add_box, matches_add_dot, matches_plus_box, matches_plus_dot, matches_sub_box,
                                matches_sub_dot, matches_minus_dot, matches_minus_box, matches_mult_box,
                                matches_mult_dot, matches_div_box, matches_div_dot, matches_rem_box, matches_rem_dot));

                        //adding matches_num to the model matches_num list
                        matches_num_model.addAll(matches_num);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            //list of integer field names used with relational operators exclusively (no #)
            List<String> rel_no_sc_names = new ArrayList<>();
            //list of integer field names used with relational operators exclusively (with #)
            List<String> rel_sc_names = new ArrayList<>();
            //list of integer field names used with numeric operators
            List<String> numNames = new ArrayList<>();

            //classifying integer fields
            for (String field: intFields_model) {
                if (matches_num_model.stream().anyMatch(e->e.contains(field)))
                    numNames.add(field);

                if (matches_rel_model.stream().filter(e->!e.contains("#" + field)).anyMatch(e->e.contains(field)))
                    rel_no_sc_names.add(field);

                if (matches_rel_model.stream().filter(e->e.contains("#" + field)).anyMatch(e->e.contains(field)))
                    rel_sc_names.add(field);
            }

            //printing out integer fields
            System.out.println("Int Fields: " + intFields_model.size() + " " + intFields_model);

            //incrementing total integer field counter
            totFields+= intFields_model.size();

            //list of integer field names that can be turned into an ordering
            List<String> all_rel_names = Stream.concat(rel_sc_names.stream(), rel_no_sc_names.stream()).
                    collect(Collectors.toList());

            List<String> intOrdering = all_rel_names.stream().filter(n -> !(numNames.contains(n))).distinct().
                    collect(Collectors.toList());

            List<String> exc_rel_no_sc = rel_no_sc_names.stream().filter(n -> !(numNames.contains(n))).distinct().
                    collect(Collectors.toList());

            List<String> exc_rel_sc = rel_sc_names.stream().filter(n -> !(numNames.contains(n))).distinct().
                    collect(Collectors.toList());

            //list of integer field names that cannot be turned into an ordering
            List<String> intNoOrdering =  numNames.stream().distinct().collect(Collectors.toList());

            //list of unclassified integer field names
            List<String> unclassified = intFields_model.stream().filter(n-> !(all_rel_names.contains(n)) &&
                    !(numNames.contains(n))).collect(Collectors.toList());

            List<String> exc_rel_no_sc_scaled = scaleDuplicates(exc_rel_no_sc, duplicates);
            List<String> exc_rel_sc_scaled = scaleDuplicates(exc_rel_sc, duplicates);
            List<String> intOrdering_scaled = scaleDuplicates(intOrdering, duplicates);
            List<String> intNoOrdering_scaled = scaleDuplicates(intNoOrdering, duplicates);
            List<String> unclassified_scaled = scaleDuplicates(unclassified, duplicates);
            List<String> numNames_scaled = scaleDuplicates(numNames.stream().distinct().collect(Collectors.toList()), duplicates);

            //printing out unclassified integer fields
            if (!unclassified_scaled.isEmpty()){
                System.out.println("Not classified: " + unclassified_scaled.size() + " " + unclassified_scaled );
                totNotClassified += unclassified_scaled.size();
            }
            System.out.println("Integer fields used exclusively with relational operators (no #): " + exc_rel_no_sc_scaled.size() + " " + exc_rel_no_sc_scaled);
            System.out.println("Integer fields used with exclusively relational operators (with #): " + exc_rel_sc_scaled.size() + " " + exc_rel_sc_scaled);
            System.out.println("Integer fields used with numeric operators: " + numNames_scaled.size() + " " + numNames_scaled);

            System.out.println("Can be made into ordering: " + intOrdering_scaled.size() + " " + intOrdering_scaled);
            System.out.println("Cannot be made into ordering: " + intNoOrdering_scaled.size() + " " + intNoOrdering_scaled);

            //incrementing counters
            totIntOrd += intOrdering_scaled.size();
            totIntNoOrd += intNoOrdering_scaled.size();

            //writing result file containing the number of integer uses that can/cannot be made into an
            //ordering
            ResultWriter.writeResults(fp_intOrdering, intOrdering_scaled.size());
            ResultWriter.writeResults(fp_intNoOrdering, intNoOrdering_scaled.size());

            ResultWriter.writeResults(fp_intFieldRelSC, exc_rel_sc_scaled.size());
            ResultWriter.writeResults(fp_intFieldRelNoSC, exc_rel_no_sc_scaled.size());
            ResultWriter.writeResults(fp_intFieldNum, numNames_scaled.size());
            ResultWriter.writeResults(fp_intFieldUnused, unclassified_scaled.size());
        }
        //printing counters over all files
        System.out.println("Total can be made into ordering: " + totIntOrd);
        System.out.println("Total cannot be made into ordering: " + totIntNoOrd);
        System.out.println("Total Int fields: " + totFields);
        System.out.println("Total unclassified: " + totNotClassified);
    }
}
