package com.alloyprofiling.patternsofuse.multifile.modules;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.EnumRetriever;
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

//class that counts the number of models that use each module
public class ModuleUseModels {
    private static int noUserModules = 0 ;
    /**
     * Maps the strings in modules to the integer values in the countList
     * @param modules list of modules
     * @param countList list of integer
     * @return a LinkedHashMap that maps strings to integers
     */
    public static LinkedHashMap<String, Integer> listsToMap(List<String> modules, List<Integer>countList) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        int ct = 0;
        for (String  m: modules) {
            map.put(m, countList.get(ct));
            ct++;
        }
        return map;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\PatternsOfUse\\MultiFile\\Modules\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String path = "corpus";
        //String path = "expert";
        System.out.println("Counting models that use each module " + path);


        //files containing the number of models that open each util module
        String fp_boolean = directoryName + "boolean_mod.txt";
        String fp_graph = directoryName + "graph_mod.txt";
        String fp_naturals = directoryName + "naturals_mod.txt";
        String fp_ordering = directoryName + "ordering_mod.txt";
        String fp_relation = directoryName + "relation_mod.txt";
        String fp_ternary = directoryName + "ternary_mod.txt";
        String fp_time = directoryName + "time_mod.txt";
        String fp_seq = directoryName + "seq_mod.txt";
        String fp_seqrel = directoryName + "seqrel_mod.txt";
        String fp_sequence = directoryName + "sequence_mod.txt";
        String fp_userModules = directoryName + "userModules_mod.txt";

        /*
        //files containing the number of models that open each util module
        String fp_boolean = directoryName + "boolean_mod_expert.txt";
        String fp_graph = directoryName + "graph_mod_expert.txt";
        String fp_naturals = directoryName + "naturals_mod_expert.txt";
        String fp_ordering = directoryName + "ordering_mod_expert.txt";
        String fp_relation = directoryName + "relation_mod_expert.txt";
        String fp_ternary = directoryName + "ternary_mod_expert.txt";
        String fp_time = directoryName + "time_mod_expert.txt";
        String fp_seq = directoryName + "seq_mod_expert.txt";
        String fp_seqrel = directoryName + "seqrel_mod_expert.txt";
        String fp_sequence = directoryName + "sequence_mod_expert.txt";
        String fp_userModules = directoryName + "userModules_mod_expert.txt"; */


        List<String> fileNames = Arrays.asList(fp_boolean, fp_graph, fp_naturals, fp_ordering, fp_relation,
                fp_ternary, fp_time, fp_seq, fp_seqrel, fp_sequence);

        //deleting results files if they already exist
        try {
            for (String filename : fileNames) {
                Files.deleteIfExists(Paths.get(filename));
            }
            Files.deleteIfExists(Paths.get(fp_userModules));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            //model counters for each module
            int boolean_model, graph_model, natural_model, ordering_model,
                    relation_model, ternary_model, time_model, seq_model,
                    seqrel_model, sequence_model, userModules_model;

            boolean_model = graph_model = natural_model = ordering_model =
                    relation_model = ternary_model = time_model = seq_model =
                            seqrel_model = sequence_model = userModules_model = 0;
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

                        //pattern to find open commands
                        ParseTreePattern p = parser.compileParseTreePattern("<priv> open <name> <para_open> <as_name_opt>", ALLOYParser.RULE_open);
                        List<ParseTreeMatch> matches = p.findAll(tree, "//specification/*");


                        //post-processing to extract modules that contain "util"
                        List<ParseTree> allNames = matches.stream().map(match -> match.get("name")).collect(Collectors.toList());
                        List<String> moduleNames = allNames.stream().map(ParseTree::getText).collect(Collectors.toList());
                        List<String> standardModules = moduleNames.stream().filter(n -> n.contains("util")).collect(Collectors.toList());

                        //extracting user-declared modules (i.e. do not contain "util")
                        List<String> userModules = moduleNames.stream().filter(n -> !(n.contains("util"))).collect(Collectors.toList());

                        //counting the occurrences of each module
                        for (String module : standardModules) {
                            if (module.contains("boolean"))
                                boolean_model++;
                            else if (module.contains("graph"))
                                graph_model++;
                            else if (module.contains("natural"))
                                natural_model++;
                            else if (module.contains("ordering"))
                                ordering_model++;
                            else if (module.contains("relation"))
                                relation_model++;
                            else if (module.contains("ternary"))
                                ternary_model++;
                            else if (module.contains("time"))
                                time_model++;
                            else if (module.contains("sequniv"))
                                seq_model++;
                            else if (module.contains("seqrel"))
                                seqrel_model++;
                            else if (module.contains("sequence"))
                                sequence_model++;
                        }

                        //enums implicitly import ordering --> increment ordering_count
                        List<String> topEnums = EnumRetriever.getEnums("top", parser, tree);
                        ordering_model += topEnums.size();

                        //incrementing counter for user-created modules
                        userModules_model += userModules.size();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            //counter for number of models that use each module
            Integer boolean_mod, graph_mod, natural_mod, ordering_mod,
                    relation_mod, ternary_mod, time_mod, seq_mod, seqrel_mod, sequence_mod;

            boolean_mod = graph_mod = natural_mod = ordering_mod =
                    relation_mod = ternary_mod = time_mod = seq_mod = seqrel_mod = sequence_mod = 0;

            List<String> allModules = Arrays.asList("boolean", "graph", "naturals", "ordering",
                    "relation", "ternary", "time", "sequniv", "seqrel", "sequence");
            List<Integer> countList = Arrays.asList(boolean_model, graph_model, natural_model,
                    ordering_model, relation_model, ternary_model, time_model, seq_model,
                    seqrel_model, sequence_model);

            List<Integer> modList = Arrays.asList(boolean_mod, graph_mod, natural_mod,
                    ordering_mod, relation_mod, ternary_mod, time_mod, seq_mod,
                    seqrel_mod, sequence_mod);

            int index = 0;
            //incrementing model counters if model uses any of the standard modules
            for (Integer count : countList) {
                if (count > 0)
                    modList.set(index, 1);
                index++;
            }

            //mapping module names to model counters
            LinkedHashMap<String, Integer> modMap;
            modMap = listsToMap(allModules, modList);

            //printing number of modules used by each model
            for (Map.Entry<String, Integer> entry : modMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            //printing counter for user modules
            System.out.println("User-created modules: " + userModules_model);

            LinkedHashMap<String, Integer> fileModMap;
            fileModMap = listsToMap(fileNames, modList);

            //writing result files containing the number of module uses
            for (Map.Entry<String, Integer> entry : fileModMap.entrySet())
                ResultWriter.writeResults(entry.getKey(), entry.getValue());

            //writing to file containing number of models with user-created modules
            if (userModules_model > 0)
                ResultWriter.writeResults(fp_userModules, 1);
            else
            {
                ResultWriter.writeResults(fp_userModules, 0);
                noUserModules++;
            }
        }
        System.out.println("No user-modules: " + noUserModules);
    }
}
