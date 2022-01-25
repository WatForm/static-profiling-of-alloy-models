package com.alloyprofiling.patternsofuse.multifile.integers;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
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
import java.util.stream.Collectors;

//class that counts number of integer fields and constants in Alloy models
public class IntegerUse {
    //total counter for integer constants across all models
    private static int totConst = 0;
    //total counter for integer atoms in field declarations across all models
    private static int totIntFields = 0;

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
        System.out.println("Counting uses of Int in Alloy models in " + path);



        //file containing number of integer constants per model
        String fp_integerConst = directoryName + "intConst.txt";
        //file containing number of integers in field declarations per model
        String fp_integerFields = directoryName + "intFields.txt";

        /*
        //file containing number of integer constants per model
        String fp_integerConst = directoryName + "intConst_expert.txt";
        //file containing number of integers in field declarations per model
        String fp_integerFields = directoryName + "intFields_expert.txt"; */

        //deleting results files if they already exist
        try {
            Files.deleteIfExists(Paths.get(fp_integerConst));
            Files.deleteIfExists(Paths.get(fp_integerFields));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int intConsts_model = 0;
            int intFields_model = 0;
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

                        //XPath hierarchy path to find num constants in expressions
                        List<String> intConsts =  XPath.findAll(tree, "//expr/constant/num", parser).stream().
                                map(ParseTree::getText).distinct().collect(Collectors.toList());

                        //collecting all integer atoms used in relations
                        Collection<ParseTree> declNames =  XPath.findAll(tree, "//sigDecl//decls/decl/expr/name", parser);
                        List<String> intFields = declNames.stream().map(ParseTree::getText).
                                filter(d -> d.equalsIgnoreCase("int")).collect(Collectors.toList());

                        System.out.println("Integer Constants in File: " + intConsts.size());
                        System.out.println("Integer Fields in File: " + intFields.size());

                        //incrementing model counters
                        intConsts_model += intConsts.size();
                        intFields_model += intFields.size();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Integer Constants in Model: " + intConsts_model);
            System.out.println("Integer Fields in Model: " + intFields_model + "\n");

            //incrementing total counters over all models
            totConst += intConsts_model;
            totIntFields += intFields_model;

            ResultWriter.writeResults(fp_integerConst, intConsts_model);
            ResultWriter.writeResults(fp_integerFields, intFields_model);
        }
        System.out.println("Total int constants: " + totConst);
        System.out.println("Total integers in decls (fields): " + totIntFields);
    }
}
