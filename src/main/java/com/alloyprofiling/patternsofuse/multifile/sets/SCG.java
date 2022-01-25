package com.alloyprofiling.patternsofuse.multifile.sets;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ModuleParamsBuilder;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.EnumRetriever;
import com.alloyprofiling.retrievers.RelationRetriever;
import com.alloyprofiling.retrievers.SigRetriever;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

//class that computes the SCG metric for Alloy models
public class SCG {
    /**
     * Creates a map that binds sub-components of signature declarations
     * The sub-components to be extracted are specified using the parameters key and value
     * @param matches list of ParseTreeMatches containing signature declarations
     * @param key name of sub-component of signature declaration to be extracted and used as key (e.g. names, superSet)
     * @param value name of sub-component of signature declaration to be extracted and used as value (e.g. name, names)
     * @return a LinkedHashMap that binds sub-components of signatures declarations
     */
    public static LinkedHashMap<ParseTree, ParseTree> listToMap(List<ParseTreeMatch> matches, String key, String value) {
        LinkedHashMap<ParseTree, ParseTree> map = new LinkedHashMap<>();
        for (ParseTreeMatch  m: matches) {
            map.put(m.get(key), m.get(value));
        }
        return map;
    }

    /**
     * Performs post-processing on a ParseTreeMatch of a signature declaration to extract and return the name of all the
     * signatures declared in the declaration
     * @param match ParseTreeMatch corresponding to a signature declaration
     * @return List of strings representing the names of signatures
     */
    private static List<String> getSigNames(ParseTreeMatch match) {
        //post-processing to pull out all sig names e.g. <mult> sig A, B { } counts as 2 sigs
        List<String> sigNames = new ArrayList<>();
        String[] namesList = match.get("names").getText().split(",");
        Collections.addAll(sigNames, namesList);
        return  sigNames;
    }

    /**
     * Adds all signatures in a model to the SCG and creates edges between signature nodes
     * @param scg Graph representing the SCG
     * @param parser_tree_map map containing instances of ALLOYParser used to parse the files in each model
     */
    private static void addSigs(Graph<String, DefaultEdge> scg,  LinkedHashMap<ALLOYParser, ParseTree> parser_tree_map, List<String>
                                files_with_sub, LinkedHashMap<String, String> sub_param_map) {
        List<ParseTreeMatch> matches_extensions = new ArrayList<>();
        LinkedHashMap<String, List<String>> enumMap = new LinkedHashMap<>();
        List<ParseTreeMatch> matches_subsets = new ArrayList<>();
        List<String> topSigs = new ArrayList<>();
        LinkedHashMap<String, List<String>> relMap = new LinkedHashMap<>();
        List<String> signatures = new ArrayList<>();

        for (var entry : parser_tree_map.entrySet()) {
            ALLOYParser parser = entry.getKey();
            ParseTree tree = entry.getValue();
            //pattern to find extension sigs
            ParseTreePattern p_children = parser.compileParseTreePattern("<priv> <abs_multiplicity> sig <names> extends <name> { <decls> } <block_opt>", ALLOYParser.RULE_sigDecl);
            matches_extensions.addAll(p_children.findAll(tree, "//paragraph/*"));

            // getting map of enums (where the keys are top-level signatures and the values are lists of extensions)
            enumMap.putAll(EnumRetriever.getEnumMap(parser, tree));

            // pattern to find subset sigs
            ParseTreePattern p_subsets = parser.compileParseTreePattern("<priv> <abs_multiplicity> sig <names> in <superSet> { <decls> } <block_opt>", ALLOYParser.RULE_sigDecl);
            matches_subsets.addAll(p_subsets.findAll(tree, "//paragraph/*"));

            // extracting remaining top-level signature sin the model
            topSigs.addAll(SigRetriever.getSigs("top", parser, tree));

            // getting a map that key is the name of a relation and the value is a list containing the signatures that
            // contain the relation
            relMap.putAll(RelationRetriever.getRelSigMap(parser, tree));

            // getting the names of all signatures in the model
            signatures.addAll(SigRetriever.getSigs("all", parser, tree));
        }

        // post-processing to add extensions and parent sigs to SRG
        Map<ParseTree,ParseTree> cpMap = listToMap(matches_extensions, "names", "name");

        //iterating over map of extensions and parents
        for (Map.Entry<ParseTree,ParseTree> entry: cpMap.entrySet()){
            String children = entry.getKey().getText();
            String parent = entry.getValue().getText();

            // adding parent to SCG if it is not already in it
            if (!(scg.containsVertex(parent)))
                scg.addVertex(parent);

            // adding extensions to SCG
            String[] namesList = children.split(",");
            for (String n : namesList) {
                if (!(scg.containsVertex(n)))
                    scg.addVertex(n);
                // adding edges between parent and children
                createEdge(scg, parent, n);
            }
        }

        // iterating over entries in the map
        for (Map.Entry<String,List<String>> entry: enumMap.entrySet()) {

            // extracting top-level parent and enum extensions
            String parent = entry.getKey();
            List<String> enum_exts = entry.getValue();

            // adding parent to SCG if it is not already in it
            if (!(scg.containsVertex(parent)))
                scg.addVertex(parent);

            // adding enum extensions to SCG
            for (String n : enum_exts) {
                if (!(scg.containsVertex(n)))
                    scg.addVertex(n);
                // adding edges between parent and children
                createEdge(scg, parent, n);
            }
        }

        // post-processing to add subsets and parent sigs to SRG
        Map<ParseTree,ParseTree> inMap = listToMap(matches_subsets, "superSet", "names");

        // post-processing to add subset sigs and superset sigs to SRG
        for (Map.Entry<ParseTree,ParseTree> entry: inMap.entrySet()){
            String superset = entry.getKey().getText();
            String subsets = entry.getValue().getText();

            // adding superset(s) to SRG if they are not already in the graph
            String[] supersetList = superset.split("\\+");
            for (String n : supersetList) {
                if (!(scg.containsVertex(n)))
                    scg.addVertex(n);
            }

            // adding subset(s) to SRG
            String[] subsetList = subsets.split(",");
            for (String n : subsetList) {
                if (!(scg.containsVertex(n)))
                    scg.addVertex(n);
            }

            // adding edges between parent and subset sigs
            for (String sub: subsetList) {
                for (String sup: supersetList) {
                    createEdge(scg, sub, sup);
                }
            }
        }

        // post-processing to add remaining top-level sigs to SCG
        for (String name : topSigs) {
            if (!(scg.containsVertex(name)))
                scg.addVertex(name);
        }
        // list containing built-in Alloy types
        List<String> builtInTypes = Arrays.asList("String", "Int", "seq/Int", "univ", "set");

        for (var entry: parser_tree_map.entrySet()) {
            ALLOYParser parser = entry.getKey();
            ParseTree tree = entry.getValue();
            //pattern to find signature declarations
            ParseTreePattern p_sigDecls = parser.compileParseTreePattern(
                    "<priv> <abs_multiplicity> sig <names> <sigExtension> { <decls> } <block_opt>",
                    ALLOYParser.RULE_sigDecl);
            List<ParseTreeMatch> matches_sigDecls = p_sigDecls.findAll(tree, "//paragraph/*");

            // post-processing: iterating over signature declarations
            for (ParseTreeMatch match: matches_sigDecls) {
                // extracting and storing names of signatures
                ArrayList<String> sigDecls = new ArrayList<>();
                String names = match.get("names").getText();
                String[] sigNames = names.split(",");
                Collections.addAll(sigDecls, sigNames);

                //extracting field/relation declarations
                ParseTree decls_tree = match.get("decls");

                // checking if signature declaration has any fields/ relations
                if (!(decls_tree.getText().isBlank())) {
                    List <String> name_decls = new ArrayList<>();
                    List<String> names_used_decls = XPath.findAll(decls_tree, "//expr//name", parser).stream().
                            map(t -> t.getText()).collect(Collectors.toList());
                    //System.out.println("names used in decls: " + names_used_decls);
                    for (String name: names_used_decls) {
                        if (files_with_sub.contains(tree.getText()+sub_param_map.get(name))){
                            String subbed_sig = sub_param_map.get(name);
                            System.out.println("Substitution needed in field declaration: " + name +" -> " + subbed_sig);
                            name_decls.add(subbed_sig);
                        }
                        else
                            name_decls.add(name);
                    }
                    // removing duplicates
                    List<String> names_noDup = new ArrayList<>(
                            new HashSet<>(name_decls));

                    // creating vertices corresponding to the signatures referenced in the field/relation declaration if
                    // they are not already in the SCG
                    for (String name: names_noDup) {
                        if (!(scg.containsVertex(name)) && !(builtInTypes.contains(name)))
                            scg.addVertex(name);
                    }

                    // creating vertices corresponding to the signatures declared if they are not already in the SCG
                    for (String sig: sigDecls) {
                        if (!scg.containsVertex(sig))
                            scg.addVertex(sig);
                    }

                    // creating an edge between the signature declared and the signatures referenced in relations/fields
                    for (String name: names_noDup) {
                        for (String sig: sigDecls ) {
                            if (!(builtInTypes.contains(name)) && !(name.equals(sig)) )
                                createEdge(scg, name, sig);
                        }
                    }
                }
            }
        }

        List<String> relations = new ArrayList<>(relMap.keySet());

        // map of coupled signatures
        LinkedHashMap<String, List<String>> coupledSigs = new LinkedHashMap<>();
        // map of coupled relations
        LinkedHashMap<List<String>, List<String>> coupledRelations = new LinkedHashMap<>();

        for (var entry: parser_tree_map.entrySet()) {
            ALLOYParser parser = entry.getKey();
            ParseTree tree = entry.getValue();
            // getting all signature declaration packages
            ParseTreePattern p = parser.compileParseTreePattern("<priv> <abs_multiplicity> sig <names> <sigExtension> { <decls> } <block>", ALLOYParser.RULE_sigDecl);
            List<ParseTreeMatch> matches_sigPackages = p.findAll(tree, "//paragraph/*");

            //iterating over signature declarations
            for (ParseTreeMatch match: matches_sigPackages) {
                //extracting the "block" and checking if it is empty
                ParseTree block = match.get("block");
                if (!StringUtils.strip( block.getText(), "{}").isBlank()) {
                    // getting the names of signatures in the declaration
                    List<String> sigs = getSigNames(match);

                    // list that contains the names of all relations except those in the signature declaration "match"
                    List<String> otherRelations = new ArrayList<>(relations);

                    // removing each signature's relations to get relations that belong to other signatures
                    List<String> mRelations = XPath.findAll(match.getTree(), "//decls//decl", parser).
                            stream().map(ParseTree::getText).collect(Collectors.toList());
                    List<String> mRelNames = new ArrayList<>();
                    mRelations.stream().forEach(r -> mRelNames.addAll(Arrays.asList(r.split(":")[0].
                            split(","))));
                    otherRelations.removeAll(mRelNames);

                    // list containing the names of all signatures in the model except those declared in "match"
                    List<String> otherSets = new ArrayList<>(signatures);
                    //removing each signature's name to get the names of other signatures
                    otherSets.removeAll(getSigNames(match));

                    // extracting the set (signatures + relations) names used in the body block of a signature declaration
                    List<String> block_sets = XPath.findAll(block, "//expr//name", parser).stream().
                            filter(n -> signatures.contains(n.getText()) || relations.contains(n.getText())).
                            map(ParseTree::getText).collect(Collectors.toList());

                    List<String> remaining_block_sets = XPath.findAll(block, "//expr//name", parser).stream().
                            map(ParseTree::getText).filter(a -> !block_sets.contains(a)).collect(Collectors.toList());

                    for (String set: remaining_block_sets) {
                        if (files_with_sub.contains(tree.getText()+sub_param_map.get(set))){
                            String subbed_sig = sub_param_map.get(set);
                            System.out.println("Substitution needed in signature fact: " + set +" -> " + subbed_sig);
                            block_sets.add(subbed_sig);
                        }
                        else
                            block_sets.add(set);
                    }


                    // if a relation belonging to another signature is referenced in the body of a signature declaration,
                    // the "parent" signatures of the relation and the signatures in the current declaration are coupled
                    otherRelations.forEach(r -> {
                        if (block_sets.contains(r))
                            coupledRelations.put(relMap.get(r), sigs);
                    });

                    // if a signature is referenced in the body of another signature, then this signature is coupled with
                    // the signatures in the current declaration
                    otherSets.forEach(s -> {
                        if (block_sets.contains(s))
                            coupledSigs.put(s, sigs);
                    });

                    //coupledRelations.forEach((key, value) -> System.out.println(key + ":" + value));

                    // creating edges between signatures coupled with a relation reference
                    coupledRelations.forEach((key, value) -> key.forEach( s1 -> value.forEach(s2 -> createEdge(scg, s1, s2))));

                    //coupledSigs.forEach((key, value) -> System.out.println(key + ":" + value));

                    // adding signatures to the SCG if they are not already in it
                    coupledSigs.keySet().forEach(key -> {
                        if (!scg.containsVertex(key))
                            scg.addVertex(key);
                    });

                    // creating edges between signatures coupled with a signature name reference
                    coupledSigs.forEach((key, value) -> value.forEach(s2 -> createEdge(scg, key, s2)));
                }
            }
        }


    }

    /**
     * Creates a bi-directional edge between two distinct vertices v1, v2 (i.e. v1 != v2) in a Graph g
     * @param g Graph representing a SCG
     * @param v1 vertex in the graph
     * @param v2 vertex in the graph
     * @return an updated SCG with the newly created bi-directional edge between v1 and v2
     */
    private static Graph<String,DefaultEdge> createEdge(Graph<String, DefaultEdge> g, String v1, String v2) {
        g.addEdge(v1, v2);
        g.addEdge(v2, v1);
        return g;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\PatternsOfUse\\MultiFile\\Sets\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
        }
        
        String path = "corpus";
        //String path = "expert";
        System.out.println("Generating SCG for Alloy models in " + path);


        String fp_SCG = directoryName + "SCG.txt";
        //String fp_SCG = directoryName + "SCG_expert.txt";

        //deleting results files if they already exists
        try {
            Files.deleteIfExists(Paths.get(fp_SCG));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* map where the key is the string "importing file, imported file" and the value is a list containing the
           parameters to be substituted in the imported file */
        LinkedHashMap<String, List<String>> importingImported_params = ModuleParamsBuilder.buildImportingImportedParamsMap();

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            String model = sc.nextLine();
            String[] files_to_parse = model.split(",");
            // the relevant keys are the ones that are in the model file string
            List<String> relevant_keys = importingImported_params.keySet().stream().
                    filter(k -> model.contains(k)).collect(Collectors.toList());
            // map that relates parameters in an imported file to their substitutions from the importing file
            LinkedHashMap<String, String> sub_param_map = new LinkedHashMap<>();
            // map that relates each parsed file to its parse tree
            LinkedHashMap<ALLOYParser, ParseTree> parser_tree_map = new LinkedHashMap<>();
            // list containing files where a substitution is needed
            List<String> files_with_sub = new ArrayList<>();
            for (String file : files_to_parse) {
                Path file_path = Paths.get(file);
                if (Files.isRegularFile(file_path)) {
                    try {
                        String source = Files.readString(file_path);

                        ALLOYLexer lexer = new ALLOYLexer(CharStreams.fromString(source));
                        ALLOYParser parser = new ALLOYParser(new CommonTokenStream(lexer));
                        ParseTree tree = parser.specification();
                         /* mapping each parsed file to its tree (parser and tree for each file in a model are needed for
                           pattern matching) */
                        parser_tree_map.put(parser, tree);

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
                                    sub_param_map.put(p,importingImported_params.get(key).get(index));
                                    files_with_sub.add(tree.getText()+importingImported_params.get(key).get(index));
                                    System.out.println(String.format("Substitute %s for %s",
                                            importingImported_params.get(key).get(index), p));
                                    index++;
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            /*
            Debugging output
            System.out.println(sub_param_map);
            System.out.println(files_with_sub);
            */
            System.out.println("Model: " + model);
            // instantiating SCG
            Graph<String, DefaultEdge> scg = new SimpleDirectedGraph<>(DefaultEdge.class);

            // calling addSigs to add signature vertices and edges to SCG
            addSigs(scg, parser_tree_map, files_with_sub, sub_param_map);

            // creating a list that contains all strongly connected components of the SCG
            StrongConnectivityAlgorithm<String, DefaultEdge> scAlg =
                    new KosarajuStrongConnectivityInspector<>(scg);
            List<Graph<String, DefaultEdge>> stronglyConnectedSubgraphs =
                    scAlg.getStronglyConnectedComponents();

            // prints the strongly connected components
            System.out.println("Strongly connected components:");
            for (int i = 0; i < stronglyConnectedSubgraphs.size(); i++) {
                System.out.println(stronglyConnectedSubgraphs.get(i));
            }
            System.out.println("SCG: " + stronglyConnectedSubgraphs.size() + "\n");


            // writing to result file
            ResultWriter.writeResults(fp_SCG, stronglyConnectedSubgraphs.size());
        }
    }
}
