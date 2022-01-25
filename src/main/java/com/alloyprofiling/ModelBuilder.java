package com.alloyprofiling;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// class that counts the number of open statements that use each library module in Alloy
public class ModelBuilder {
    // instantiating file graph
    private static  Graph<String, DefaultEdge> fileGraph = new SimpleDirectedGraph<>(DefaultEdge.class);
    private static List<String> all_files = new ArrayList<>();
    private static LinkedHashMap<String, String> module_file_map = new LinkedHashMap<>();
    private static LinkedHashMap<String, List<String>> module_params_map = new LinkedHashMap<>();
    private static int multiFileModels = 0;
    private static int singleFileModels = 0;
    private static int filesWithOpen = 0;

    private static void addFiles(Graph<String, DefaultEdge> g, String mainFile, String importedFile) {
        if (!g.containsVertex(mainFile))
            g.addVertex(mainFile);
        if (!g.containsVertex(importedFile))
            g.addVertex(importedFile);

        g.addEdge(mainFile, importedFile);
    }


    public static void main(String[] args) {
        //results directory path
        String directoryName = "Results\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
        }

        String path = "corpus";
        System.out.println("Creating database of models for Alloy files in " + path);

        //file containing the models and the files that make them up
        String fp_models = directoryName + "models.txt";
        //file containing the importing file, the imported file and the parameters
        //e.g.testmodels\m1.als,testmodels\sub\m2.als,A means m1 opens m2 with A as a parameter
        String fp_module_parms = directoryName + "module-params.txt";

        String fp_span = directoryName + "span.txt";
        String fp_span_multi = directoryName + "span_multi.txt";


        List<String> missing_files = Arrays.asList("corpus\\kjb6vhsmn7u7u64lwjmxfxw6kh34n6io-alloy\\filesystem.als",
                "corpus\\kjb6vhsmn7u7u64lwjmxfxw6kh34n6io-alloy\\mysqlserver.als",
                "corpus\\kjb6vhsmn7u7u64lwjmxfxw6kh34n6io-alloy\\filesystemStateTransitions.als",
                "corpus\\ozfnzpc27ebozmzsnk6lykosmrnjfrxb-LogicFinal\\two_three_tree.als",
                "corpus\\ozfnzpc27ebozmzsnk6lykosmrnjfrxb-LogicFinal\\aa_tree.als");

        //deleting results files if they already exit
        try {
            Files.deleteIfExists(Paths.get(fp_models));
            Files.deleteIfExists(Paths.get(fp_module_parms));
            Files.deleteIfExists(Paths.get(fp_span));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        String source = Files.readString(filePath);

                        ALLOYLexer lexer = new ALLOYLexer(CharStreams.fromString(source));
                        ALLOYParser parser = new ALLOYParser(new CommonTokenStream(lexer));
                        ParseTree tree = parser.specification();

                        String filename = StringUtils.replace(filePath.getFileName().toString(),".als", "");

                        //extracting module name (if any)
                        List<String> modules = XPath.findAll(tree, "//module/name", parser).stream()
                                .map(n -> StringUtils.replace(n.getText(),filename,"")).collect(Collectors.toList());

                        System.out.println("module name: " + modules);

                        if (!modules.isEmpty() && !modules.get(0).contains("\\")) {
                            module_file_map.put(modules.get(0),filePath.toFile().toString());
                        }


                        //pattern to find open commands
                        ParseTreePattern p = parser.compileParseTreePattern("<priv> open <name> <para_open> <as_name_opt>", ALLOYParser.RULE_open);
                        List<ParseTreeMatch> matches = p.findAll(tree, "//specification/*");

                        for (ParseTreeMatch open_match: matches) {
                            List<String> params = XPath.findAll(open_match.getTree(),
                                    "//para_open/names_opt/names/name",parser).stream().map(ParseTree::getText)
                                    .collect(Collectors.toList());
                            String module = open_match.get("name").getText();
                            if (!params.isEmpty()){
                                module_params_map.put(filePath+"*"+module,params);
                            }
                        }

                        //post-processing to extract modules that contain "util"
                        List<ParseTree> allNames = matches.stream().map(match -> match.get("name")).collect(Collectors.toList());
                        List<String> moduleNames = allNames.stream().map(ParseTree::getText).collect(Collectors.toList());

                        //extracting user-declared modules (i.e. do not contain "util")
                        List<String> userModules = moduleNames.stream().filter(n-> !(n.contains("util"))).collect(Collectors.toList());

                        System.out.println(filePath.toFile());
                        all_files.add(filePath.toFile().toString());

                        fileGraph.addVertex(filePath.toFile().toString());

                        for (String importedFile: userModules) {
                            System.out.println("imported (as-is): " + importedFile);

                            String adjusted_impFile;
                            if (module_file_map.containsKey(importedFile)){
                                adjusted_impFile = module_file_map.get(importedFile).replace("/","\\");
                                //System.out.println("H-2");
                            }
                            else if (modules.isEmpty())
                            {
                                //System.out.println("H-1");
                                adjusted_impFile = filePath.getParent() + "\\" + importedFile.replace("/","\\") +".als";
                            }
                            else{
                                //System.out.println("H0 " + modules.get(0) );
                                String sub_dir = filePath.toFile().toString().replace(filename + ".als", "")
                                        .replace(path +"\\", "").replace("\\","/");
                                String[] sub_dir_arr = sub_dir.split("/");
                                String mod_name = modules.get(0);
                                System.out.println("mod_name_before: " + mod_name);
                                for (String sub_str: sub_dir_arr) {
                                    System.out.println("sub str: " + sub_str);
                                    mod_name = mod_name.replace(sub_str +"/" , "") ;
                                }
                                System.out.println("mod_name: " + mod_name);

                                /*
                                if (mod_name.contains(sub_dir) && CharMatcher.is('/').countIn(modules.get(0)) -1 > 1 &&) {
                                    mod_name = modules.get(0).split("/")[0];
                                    System.out.println("if passed");
                                } */

                                boolean prev_dir = false;
                                String importedFile_stripped;
                                if (importedFile.contains(mod_name)) {
                                    //System.out.println("H1");
                                    importedFile_stripped = StringUtils.replace(importedFile, mod_name, "");
                                    System.out.println("mod0: " + mod_name);
                                    System.out.println("stripped: " + importedFile_stripped);
                                    Path stripped_path = Path.of(filePath.getParent() + "\\" + importedFile_stripped + ".als");
                                    if (!Files.exists(stripped_path)) {
                                        prev_dir = true;
                                    }
                                }
                                else {
                                    //System.out.println("H2");
                                    Path importedFile_path = Path.of(filePath.getParent() + "\\" + importedFile + ".als");
                                    if (!Files.exists(importedFile_path)) {
                                        prev_dir = true;
                                    }
                                    String[] importedFile_split = importedFile.split("/");
                                    importedFile_stripped = importedFile_split[importedFile_split.length-1];
                                }
                                Path parent;
                                if (prev_dir){
                                    //System.out.println("H3");
                                    System.out.println(importedFile_stripped);
                                    parent = filePath.getParent();
                                    Path imporedFile_path = Path.of(parent + "\\" + importedFile_stripped + ".als");
                                    if(imporedFile_path.toString().equals(filePath.toString()))
                                        parent = parent.getParent();
                                    while (!Files.exists(imporedFile_path)){
                                        parent = parent.getParent();
                                        imporedFile_path = Path.of(parent + "\\" + importedFile_stripped + ".als");
                                    }

                                }
                                else{
                                    parent = filePath.getParent();
                                    //System.out.println("H4");
                                }

                                if (importedFile_stripped.startsWith("/"))
                                {
                                    adjusted_impFile = parent +
                                            importedFile_stripped.replace("/","\\") + ".als";
                                    //System.out.println("H5");
                                }

                                else{
                                    adjusted_impFile = parent + "\\" +
                                            importedFile_stripped.replace("/","\\") + ".als";
                                    //System.out.println("H6");
                                }

                                System.out.println("adjusted: " + adjusted_impFile);
                            }


                            if (!missing_files.contains(adjusted_impFile)) {
                                addFiles(fileGraph, filePath.toFile().toString(), adjusted_impFile);
                            }

                            if (module_params_map.containsKey(filePath+"*"+importedFile)) {
                                List<String> module_params = new ArrayList<>();
                                module_params.add(filePath.toString());
                                module_params.add(adjusted_impFile);
                                module_params.addAll(module_params_map.get(filePath+"*"+importedFile));
                                ResultWriter.writeModels(fp_module_parms, module_params);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            List<String> roots = fileGraph.vertexSet().stream()
                    .filter(key -> fileGraph.incomingEdgesOf(key).size() == 0)
                    .collect(Collectors.toList());

            List<String> leaves = fileGraph.vertexSet().stream()
                    .filter(key -> fileGraph.outgoingEdgesOf(key).size() == 0)
                    .collect(Collectors.toList());

            //System.out.println("roots: " + roots);
            //System.out.println("leaves: " + leaves);

            AllDirectedPaths<String, DefaultEdge> allDirectedPaths = new AllDirectedPaths<>(fileGraph);

            int count = 0;
            HashSet<String> models_string = new HashSet<>();
            for (String root: roots) {
                for (String leaf: leaves) {
                    List<GraphPath<String, DefaultEdge>> dir_paths = allDirectedPaths.getAllPaths(root, leaf, false, 100);
                    if (!dir_paths.isEmpty()){
                        //System.out.println("One model: " + dir_paths.get(0).getVertexList());
                        ResultWriter.writeModels(fp_models, dir_paths.get(0).getVertexList());
                        models_string.add(dir_paths.get(0).getVertexList().toString());
                        count++;
                        ResultWriter.writeResults(fp_span,dir_paths.get(0).getVertexList().size());
                        if (dir_paths.get(0).getVertexList().size() > 1) {
                            ResultWriter.writeResults(fp_span_multi,dir_paths.get(0).getVertexList().size());
                            multiFileModels++;
                            filesWithOpen += dir_paths.get(0).getVertexList().size() -1;
                        }
                        else
                            singleFileModels++;
                    }
                }
            }
            List<String> extra_nodes = fileGraph.vertexSet().stream().filter(f -> !all_files.contains(f)).
                    collect(Collectors.toList());
            System.out.println("all nodes: " + fileGraph.vertexSet().size());
            System.out.println("total: " + count);
            System.out.println("roots: " + roots.size());
            System.out.println("extra: " + extra_nodes.size() + " " + extra_nodes);
            System.out.println("multi-file models: " + multiFileModels);
            System.out.println("single-file models: " + singleFileModels);
            System.out.println("files with open: " + filesWithOpen);
            System.out.println("Hashset size: " + models_string.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
