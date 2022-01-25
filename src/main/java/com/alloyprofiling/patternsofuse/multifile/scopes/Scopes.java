package com.alloyprofiling.patternsofuse.multifile.scopes;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.SigRetriever;
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
import java.util.stream.Stream;

// class that counts the number of commands that fall under each scope category
public class Scopes {
    private static  List<String> getSetScopes(ParseTreeMatch match, String type,  ALLOYParser parser) {

        List<ParseTree> typescopes = match.getAll("typescopes");

        if (typescopes.isEmpty())
            typescopes =  match.getAll("but_typescopes");

        List<ParseTree> setScopes = new ArrayList<>();

        if (type.equals("all"))
            typescopes.forEach(ts -> setScopes.addAll((XPath.findAll(ts, "//typescope", parser))));

        else {
            switch (type) {
                case "exact":
                    typescopes.forEach(ts -> setScopes.addAll((XPath.findAll(ts, "//typescope", parser)).
                            stream().filter(t -> t.getText().contains("exactly")).
                            collect(Collectors.toList())));
                    break;

                case "non-exact":
                    typescopes.forEach(ts -> setScopes.addAll((XPath.findAll(ts, "//typescope", parser)).
                            stream().filter(t -> !(t.getText().contains("exactly"))).
                            collect(Collectors.toList())));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
        }

        return getSigs(setScopes, parser);
    }
    private static List<String> getSigs(List<ParseTree> treeList, ALLOYParser parser) {
        List<String> sigs =  new ArrayList<>();
        for (ParseTree t: treeList) {
            List<String> names = XPath.findAll(t, "//name", parser)
                        .stream().map(ParseTree::getText).collect(Collectors.toList());
            sigs.addAll(names);
        }
        return sigs;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\PatternsOfUse\\MultiFile\\Scopes\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
        }

        String path = "corpus";
        //String path = "expert";
        System.out.println("Categorizing scopes in Alloy models in " + path);


        //files containing the number of run/check commands that fall under each scope category
        String fp_setExact = directoryName + "setExact.txt";
        String fp_setNonExact = directoryName + "setNonExact.txt";
        String fp_modelExact = directoryName +  "modelExact.txt";
        String fp_defaultNonExact = directoryName + "defaultNonExact.txt";
        String fp_derivedExact = directoryName + "derivedExact.txt";
        String fp_derivedNonExact = directoryName + "derivedNonExact.txt";

        /*
        //files containing the number of run/check commands that fall under each scope category
        String fp_setExact = directoryName + "setExact_expert.txt";
        String fp_setNonExact = directoryName + "setNonExact_expert.txt";
        String fp_modelExact = directoryName +  "modelExact_expert.txt";
        String fp_defaultNonExact = directoryName + "defaultNonExact_expert.txt";
        String fp_derivedExact = directoryName + "derivedExact_expert.txt";
        String fp_derivedNonExact = directoryName + "derivedNonExact_expert.txt"; */

        //deleting results files if they already exist
        try {
            List<String> files = Arrays.asList(fp_setExact, fp_setNonExact, fp_modelExact, fp_defaultNonExact,
                    fp_derivedExact, fp_derivedNonExact);
            for (String file: files)
                Files.deleteIfExists(Paths.get(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int setExact_model = 0;
            int setNonExact_model = 0;
            int modelExact_model = 0;
            int defaultNonExact_model = 0;
            int derivedExact_model = 0;
            int derivedNonExact_model = 0;
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

                        //getting all signature names
                        List<String> signatures = SigRetriever.getSigs("all", parser, tree);

                        //DEFAULT NON-EXACT

                        //list containing names of all Default Non-Exact signatures
                        List<String> defaultNonExact = new ArrayList<>();

                        //Pattern to find all run/check command without "for"
                        ParseTreePattern p_defaultNonExact = parser.compileParseTreePattern
                                ("<name_cmd_opt> <run_or_check> <nameOrBlock>",
                                        ALLOYParser.RULE_cmdDecl);
                        List<ParseTreeMatch> matches_defaultNonExact = p_defaultNonExact.findAll(tree, "//paragraph/*");

                        matches_defaultNonExact.forEach(m -> defaultNonExact.addAll(signatures));

                        // SET EXACT AND SET NON-EXACT

                        List<ParseTree> setExact = new ArrayList<>();
                        List<ParseTree> setNonExact = new ArrayList<>();

                        //patterns to command declarations with set scopes
                        ParseTreePattern p_set = parser.compileParseTreePattern
                                ("<name_cmd_opt> <run_or_check> <nameOrBlock> for <typescopes> <expect_digit>",
                                        ALLOYParser.RULE_cmdDecl);

                        ParseTreePattern p_set_num = parser.compileParseTreePattern
                                ("<name_cmd_opt> <run_or_check> <nameOrBlock> for <number> <but_typescopes> <expect_digit>",
                                        ALLOYParser.RULE_cmdDecl);

                        List<ParseTreeMatch> matches_set1 = p_set.findAll(tree, "//paragraph/*");
                        List<ParseTreeMatch> matches_set2 = p_set_num.findAll(tree, "//paragraph/*");

                        //extracting typescopes with the keyword "exactly"
                        matches_set1.forEach(m -> m.getAll("typescopes").
                                forEach(ts -> setExact.addAll((XPath.findAll(ts, "//typescope", parser)).
                                        stream().filter(t -> t.getText().contains("exactly")).
                                        collect(Collectors.toList()))));


                        //extracting but_typescopes with the keyword "exactly"
                        matches_set2.forEach(m -> m.getAll("but_typescopes").
                                forEach(ts -> setExact.addAll((XPath.findAll(ts, "//typescope", parser)).
                                        stream().filter(t -> t.getText().contains("exactly")).
                                        collect(Collectors.toList()))));

                        //finding signatures names with exact scopes
                        List<String> sigs_setExact = getSigs(setExact, parser);

                        //extracting typescopes without the keyword "exactly"
                        matches_set1.forEach(m -> m.getAll("typescopes").
                                forEach(ts -> setNonExact.addAll((XPath.findAll(ts, "//typescope", parser)).
                                        stream().filter(t -> !(t.getText().contains("exactly"))).
                                        collect(Collectors.toList()))));


                        //extracting but_typescopes without the keyword "exactly"
                        matches_set2.forEach(m -> m.getAll("but_typescopes").
                                forEach(ts -> setNonExact.addAll((XPath.findAll(ts, "//typescope", parser)).
                                        stream().filter(t -> !(t.getText().contains("exactly"))).
                                        collect(Collectors.toList()))));

                        //finding signatures names with non-exact scopes
                        List<String> sigs_setNonExact = getSigs(setNonExact, parser);

                        //MODEL EXACT

                        List<String> modelExact = new ArrayList<>();

                        //pattern to find children sig declarations
                        ParseTreePattern p_children = parser.compileParseTreePattern("<priv> <abs_multiplicity> sig <names> extends <name> { <decls> } <block_opt>", ALLOYParser.RULE_sigDecl);
                        List<ParseTreeMatch> matches_children = p_children.findAll(tree, "//paragraph/*");

                        //extracting names of parent (top-level) signatures
                        List<String> topLevelSigs = matches_children.stream().map(m -> m.get("name").getText()).
                                collect(Collectors.toList());

                        //LinkedHashMap containing topLevelSigs and their kids
                        LinkedHashMap<String, List<String>> top_extensions= new LinkedHashMap<>();

                        for (ParseTreeMatch m: matches_children) {
                            String parent_sig = m.get("name").getText();
                            if (top_extensions.containsKey(parent_sig)) {
                                List<String> value = new ArrayList<>(top_extensions.get(parent_sig));
                                List<String> added = Arrays.asList(m.get("names").getText().split(","));
                                value.addAll(added);
                                top_extensions.put(parent_sig, value);
                            }
                            else {
                                top_extensions.put(parent_sig,Arrays.asList(m.get("names").getText().split(",")));
                            }
                        }

                        //getting all signature extension names for each top-level signature
                        List<String> mult = Arrays.asList("one", "lone", "some");
                        for (String topSig: topLevelSigs) {
                            ParseTreePattern p_extensions = parser.compileParseTreePattern("<priv> <abs_multiplicity> sig <names> extends " + topSig + " { <decls> } <block_opt>", ALLOYParser.RULE_sigDecl);
                            List<ParseTreeMatch> matches_extensions = p_extensions.findAll(tree, "//paragraph/*");
                            //checking if they all have a declared multiplicity
                            List<String> extensions_wMult = new ArrayList<>();
                            matches_extensions.forEach( m -> {
                                if (mult.stream().anyMatch(m.getTree().getText()::contains))
                                    extensions_wMult.add(m.getTree().getText());
                            });
                            if (matches_extensions.size() == extensions_wMult.size())
                                modelExact.add(topSig);
                        }

                        // DERIVED EXACT

                        List<String> derivedExact = new ArrayList<>();

                        //getting signatures with set multiplicities
                        List<String> sets_mult = SigRetriever.getSigs("allmult", parser, tree);

                        List<ParseTreeMatch> set_matches = Stream.concat(
                                matches_set1.stream(), matches_set2.stream()).collect(Collectors.toList());
                        for (ParseTreeMatch m: set_matches) {
                            List<String> setExact_m = getSetScopes(m, "exact", parser);
                            List<String> names = XPath.findAll(m.getTree(), "//name", parser).
                                    stream().map(ParseTree::getText).
                                    filter(signatures::contains).collect(Collectors.toList());
                            List<String> exact_topLevel = names.stream().filter(s -> topLevelSigs.contains(s) &&
                                    setExact_m.contains(s)).collect(Collectors.toList());
                            for (String topSig: exact_topLevel) {
                                List<String> extensions =  top_extensions.get(topSig);
                                List<String> ext_no_scope = extensions.stream().filter(e ->
                                        !names.contains(e) && !sets_mult.contains(e)).collect(Collectors.toList());
                                derivedExact.addAll(ext_no_scope);
                            }

                            List<String> top_no_scope = topLevelSigs.stream().filter(s -> !names.contains(s)).
                                    distinct().collect(Collectors.toList());

                            for (String topSig: top_no_scope) {
                                List<String> extensions_top =  top_extensions.get(topSig);
                                List<String> scoped_extensions = extensions_top.stream().
                                        filter(s -> names.stream().anyMatch(extensions_top::contains) && (sets_mult.contains(s) || setExact_m.contains(s))).
                                        collect(Collectors.toList());

                                if (extensions_top.size() == scoped_extensions.size())
                                    derivedExact.add(topSig);
                            }

                        }

                        // DERIVED NON-EXACT

                        List<String> derivedNonExact = new ArrayList<>();

                        for (ParseTreeMatch m: set_matches) {
                            List<String> setNonExact_m = getSetScopes(m, "non-exact", parser);
                            List<String> names = XPath.findAll(m.getTree(), "//name", parser).
                                    stream().map(ParseTree::getText).
                                    filter(signatures::contains).collect(Collectors.toList());
                            List<String> nonExact_topLevel = names.stream().filter(s -> topLevelSigs.contains(s) &&
                                    setNonExact_m.contains(s)).collect(Collectors.toList());
                            for (String topSig: nonExact_topLevel) {
                                List<String> extensions =  top_extensions.get(topSig);
                                List<String> ext_no_scope = extensions.stream().filter(e ->
                                        !names.contains(e) && !sets_mult.contains(e)).collect(Collectors.toList());
                                derivedNonExact.addAll(ext_no_scope);
                            }

                            List<String> top_no_scope = topLevelSigs.stream().filter(s -> !names.contains(s)).
                                    distinct().collect(Collectors.toList());

                            for (String topSig: top_no_scope) {
                                List<String> extensions_top =  top_extensions.get(topSig);
                                List<String> scoped_extensions = extensions_top.stream().
                                        filter(s -> names.stream().anyMatch(extensions_top::contains) &&
                                                (sets_mult.contains(s) || setNonExact_m.contains(s))).
                                        collect(Collectors.toList());

                                if (extensions_top.size() == scoped_extensions.size())
                                    derivedNonExact.add(topSig);
                            }

                        }

                        top_extensions.forEach((key, value) -> System.out.println(key + " " + value));

                        List<String> modelExact_file = modelExact.stream().distinct().collect(Collectors.toList());

                        //Printing out results in file

                        System.out.println("Set Exact in File: " + sigs_setExact.size() + " " + sigs_setExact);
                        System.out.println("Set Non-Exact in File: " + sigs_setNonExact.size() + " " + sigs_setNonExact);
                        System.out.println("Model Exact in File: " + modelExact_file.size() + " " + modelExact_file);
                        System.out.println("Default Non-Exact in File: " + defaultNonExact.size() + " " + defaultNonExact);
                        System.out.println("Derived Exact in File: " + derivedExact.size() + " " + derivedExact);
                        System.out.println("Derived Non-Exact in File: " + derivedNonExact.size() + " " + derivedNonExact);

                        //incrementing model counters
                        setExact_model += sigs_setExact.size();
                        setNonExact_model += sigs_setNonExact.size();
                        modelExact_model += modelExact_file.size();
                        defaultNonExact_model += defaultNonExact.size();
                        derivedExact_model += derivedExact.size();
                        derivedNonExact_model += derivedNonExact.size();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Set Exact in Model: " + setExact_model);
            System.out.println("Set Non-Exact in Model: " +setNonExact_model);
            System.out.println("Model Exact in Model: " + modelExact_model);
            System.out.println("Default Non-Exact in Model: " + defaultNonExact_model);
            System.out.println("Derived Exact in Model: " + derivedExact_model);
            System.out.println("Derived Non-Exact in Model: " + derivedNonExact_model + "\n");

            //writing result files containing the number of uses of each scope category
            ResultWriter.writeResults(fp_setExact, setExact_model);
            ResultWriter.writeResults(fp_setNonExact, setNonExact_model);
            ResultWriter.writeResults(fp_modelExact, modelExact_model);
            ResultWriter.writeResults(fp_defaultNonExact, defaultNonExact_model);
            ResultWriter.writeResults(fp_derivedExact, derivedExact_model);
            ResultWriter.writeResults(fp_derivedNonExact, derivedNonExact_model);
        }
    }
}
