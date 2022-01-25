package com.alloyprofiling.patternsofuse.multifile.sets;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ModuleParamsBuilder;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.RelationRetriever;
import com.alloyprofiling.retrievers.SigRetriever;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//class that counts the number of sigs used as structures in Alloy models
public class SigsAsStructures {
    //counter for total number of signatures used as structures (across all models)
    private static int totStructures = 0;
    //counter for total number of models with at least one sig used as structures
    private static int totModelsWithStructs = 0;

    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\PatternsOfUse\\MultiFile\\Sets\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String path = "corpus";
        //String path = "expert";

        System.out.println("Counting signatures used as structures in Alloy models in " + path + '\n');

        //file containing the number of signatures used as records per model
        String fp_structures = directoryName + "sigsAsStructs.txt";
        //String fp_structures = directoryName + "sigsAsStructs_expert.txt";

        //deleting results file if it already exists
        try {
            Files.deleteIfExists(Paths.get(fp_structures));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // reading the file containing the models
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        /* map where the key is the string "importing file, imported file" and the value is a list containing the
           parameters to be substituted in the imported file */
        LinkedHashMap<String, List<String>> importingImported_params = ModuleParamsBuilder.buildImportingImportedParamsMap();
        //LinkedHashMap<String, String> importing_imported = ModuleParamsBuilder.buildImportingImportedMap();
        // printing map (for testing only)
        //importingImported_params.forEach((key, val) -> System.out.println(key+": " + val));

        // iterating over models
        while (sc.hasNextLine()) {
            // each line is a model that may have 1 or more comma-separated files
            String model = sc.nextLine();
            // array containing the files to parse
            String[] files_to_parse = model.split(",");
            // map that relates signatures to their fields
            LinkedHashMap<String, List<String>> sigFields_map = new LinkedHashMap<>();
            // list containing all signatures in a model (potentially across multiple files)
            List<String> all_sigs = new ArrayList<>();
            // list containing all signatures used as structures in a model (potentially across multiple files)
            List<String> structures = new ArrayList<>();
            // map that relates each parsed file to its parse tree
            LinkedHashMap<ALLOYParser, ParseTree> parser_tree_map = new LinkedHashMap<>();
            // the relevant keys are the ones that are in the model file string
            List<String> relevant_keys = importingImported_params.keySet().stream().
                    filter(k -> model.contains(k)).collect(Collectors.toList());
            // map that relates parameters in an imported file to their substitutions from the importing file
            LinkedHashMap<String, String> param_sub_map = new LinkedHashMap<>();
            // list containing files where a substitution is needed
            List<String> files_with_sub = new ArrayList<>();

            System.out.println("Model: " + model);

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
                        /* mapping each parsed file to its tree (parser and tree for each file in a model are needed for
                           pattern matching) */
                        parser_tree_map.put(parser, tree);

                        // printing current file
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
                                    param_sub_map.put(importingImported_params.get(key).get(index),p);
                                    files_with_sub.add(tree.getText()+importingImported_params.get(key).get(index));
                                    index++;
                                }
                                param_sub_map.forEach((p1, p2) -> System.out.println("substitute " + p1 + " for " + p2));
                            }
                        }

                        //map between signature names and their correspondent fields
                        sigFields_map.putAll(RelationRetriever.getSigRelMap(parser, tree));

                        //getting all signature names
                        all_sigs.addAll(SigRetriever.getSigs("all", parser, tree));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            //printing each signature and its fields
            sigFields_map.entrySet().forEach(entry -> System.out.println("Signature " +
                    entry.getKey() + " has fields: " + entry.getValue()));

            System.out.println();

            // filtering out signatures that do not have fields (they cannot be structures)
            List<String> sigs_with_fields = all_sigs.stream().filter(s -> sigFields_map.containsKey(s)).
                    collect(Collectors.toList());
            for (String sig : sigs_with_fields) {
                System.out.println("Checking signature: " + sig) ;
                List<String> matches_dot = new ArrayList<>();
                List<String> matches_sigs_expr = new ArrayList<>();
                List<String> matches_sigs_decls_e = new ArrayList<>();
                List<String> matches_boxJ = new ArrayList<>();

                //list containing sig's fields
                List<String> fields = sigFields_map.get(sig);

                for (var entry : parser_tree_map.entrySet()) {
                    ALLOYParser parser = entry.getKey();
                    ParseTree tree = entry.getValue();

                    String tmpSig = sig;
                    if (files_with_sub.contains(tree.getText()+sig)) {
                        System.out.println("SUB NEEDED " + sig + param_sub_map.get(sig));
                        tmpSig = param_sub_map.get(sig);
                    }

                    if (fields != null && !fields.isEmpty()) {
                        //pattern to find dot joins that bind sig and one of its fields
                        ParseTreePattern p_dot = parser.compileParseTreePattern(tmpSig + " <dotOp> <expr>", ALLOYParser.RULE_expr);
                        //excluding dot joins that have transitive closure operators
                        matches_dot.addAll(p_dot.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).filter(s -> fields.stream().anyMatch(s::contains)
                                        && !(s.contains("*") || s.contains("^"))).collect(Collectors.toList()));

                        //pattern to find box joins that bind sig and one of its fields
                        ParseTreePattern p_boxJ = parser.compileParseTreePattern("<expr> [" + tmpSig + "]", ALLOYParser.RULE_expr);
                        matches_boxJ.addAll(p_boxJ.findAll(tree, "//*").stream().
                                map(m -> m.getTree().getText()).filter(s -> fields.stream().anyMatch(s::contains)).
                                collect(Collectors.toList()));

                        //finding all other references to sig in the model
                        ParseTreePattern p_sigs_expr = parser.compileParseTreePattern(tmpSig, ALLOYParser.RULE_name);
                        matches_sigs_expr.addAll(p_sigs_expr.findAll(tree, "//expr/*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList()));

                        //finding all references to sig in quantified expressions in the model
                        ParseTreePattern p_sigs_decls_e = parser.compileParseTreePattern(tmpSig, ALLOYParser.RULE_name);
                        matches_sigs_decls_e.addAll(p_sigs_decls_e.findAll(tree, "//decls_e/decl/expr/*").stream().
                                map(m -> m.getTree().getText()).collect(Collectors.toList()));
                    }
                }


                int other_uses = matches_sigs_expr.size() - matches_sigs_decls_e.size();

                System.out.println("Exprs: " + matches_sigs_expr.size() + matches_sigs_expr);
                System.out.println("Decls_e: " + matches_sigs_decls_e.size() + matches_sigs_decls_e);
                System.out.println("Dot uses: " + matches_dot.size() + matches_dot);
                System.out.println("Box uses: " + matches_boxJ.size() + matches_boxJ);


                //checking if sig is used as structure
                if (!matches_sigs_expr.isEmpty() && other_uses == (matches_dot.size() + matches_boxJ.size()))
                    structures.add(sig);
            }
            //incrementing counters
            if (!structures.isEmpty()) {
                totModelsWithStructs++;
            }
            totStructures += structures.size();
            ResultWriter.writeResults(fp_structures, structures.size());
            System.out.println("Signatures used as structures: " + structures.size() + " " + structures);
            System.out.println();

        }
        //printing total number of signatures used as structures (over all models)
        System.out.println("Total number of signatures used as structures: " + totStructures);
        System.out.println("Total number of models that have at least one structure: " + totModelsWithStructs);
    }
}
