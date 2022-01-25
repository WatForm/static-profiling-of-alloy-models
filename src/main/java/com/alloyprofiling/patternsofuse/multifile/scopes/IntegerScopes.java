package com.alloyprofiling.patternsofuse.multifile.scopes;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegerScopes {
    private static int totQuery = 0;
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
        System.out.println("Counting Int/int Scopes in Alloy models in " + path);

        String fp_integerScopes = directoryName + "intScopes.txt";
        String fp_intValues = directoryName + "intScopeNums.txt";

        /*String fp_integerScopes = directoryName + "intScopes_expert.txt";
        String fp_intValues = directoryName + "intScopeNums_expert.txt"; */


        //deleting result files if they already exist
        try {
            Files.deleteIfExists(Paths.get(fp_integerScopes));
            Files.deleteIfExists(Paths.get(fp_intValues));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int cmdInts_model = 0;
            List<Integer> intScopes_model = new ArrayList<>();
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

                        //XPath string to find all command queries
                        Collection<ParseTree> nameTrees =  XPath.findAll(tree,
                                "//typescopes//typescope//name", parser);

                        //Extracting all "Int"/"int" occurrences from command queries (typescope part of them)
                        ParseTreePattern p_int = parser.compileParseTreePattern
                                ("<exactly_opt> <number> int", ALLOYParser.RULE_typescope);
                        List<ParseTreeMatch> matches_int = p_int.findAll(tree, "//typescopes/*");

                        ParseTreePattern p_Int = parser.compileParseTreePattern
                                ("<exactly_opt> <number> Int", ALLOYParser.RULE_typescope);
                        List<ParseTreeMatch> matches_Int = p_Int.findAll(tree, "//typescopes/*");

                        List<ParseTreeMatch> matches = Stream.concat(matches_int.stream(), matches_Int.stream())
                                .collect(Collectors.toList());

                        List<Integer> intScopes = matches.stream().map(m -> Integer.parseInt(m.get("number").getText()))
                                .collect(Collectors.toList());

                        List<String> cmdInts = matches.stream().
                                map(m -> m.getTree().getText())
                                .collect(Collectors.toList());

                        System.out.println("Integer Scopes in File: " + cmdInts.size() + " " +cmdInts);
                        System.out.println("Int Scope Numbers in File: " + intScopes);

                        //incrementing model counter
                        cmdInts_model += cmdInts.size();

                        //adding intScopes in file to model intScopes list
                        intScopes_model.addAll(intScopes);

                        //incrementing query counter over all models
                        totQuery += nameTrees.size() + matches_int.size() - matches_Int.size();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Integer Scopes in Model: " + cmdInts_model);
            System.out.println("Int Scope Numbers in Model: " + intScopes_model + "\n");
            ResultWriter.writeResults(fp_integerScopes, cmdInts_model);

            intScopes_model.forEach(i -> ResultWriter.writeResults(fp_intValues, i));

        }
        System.out.println("Total number of queries: " + totQuery);
    }
}
