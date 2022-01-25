package com.alloyprofiling.characteristics.multifile.signatures;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.retrievers.SigRetriever;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

//class that count the number of top-level signatures in Alloy models
public class FieldsByKind {
    //class attributes representing the total number of signatures without fields by kind across all models
    private static int totTopSigsNoFields = 0;
    private static int totSubSigsNoFields = 0;
    private static int totExtSigsNoFields = 0;
    private static int totOneExtSigsNoFields = 0;
    private static int totNonOneExtSigsNoFields = 0;
    private static int totOneSigsNoFields = 0;

    //counters for total number of non-one signature extensions and one sig extensions
    private static int totNonOneExtSigs = 0;
    private static int totOneExtSigs = 0;

    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\CharacteristicsOfModels\\MultiFile\\Signatures\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (!directory.exists()){
            directory.mkdirs();
        }

        //repository of models
        String path = "corpus";
        //String path = "expert";
        System.out.println("Counting fields by kind in Alloy models in " + path);

        // file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int topSigsNoFields_model = 0;
            int subSigsNoFields_model = 0;
            int extSigsNoFields_model = 0;
            int oneExtSigsNoFields_model = 0;
            int nonOneExtSigsNoFields_model = 0;
            int oneSigsNoFields_model = 0;

            int nonOneExtSigs_model = 0;
            int oneExtSigs_model = 0;
            String model = sc.nextLine();
            String[] files_to_parse = model.split(",");
            for (String file: files_to_parse) {
                Path  file_path = Paths.get(file);
                if (Files.isRegularFile(file_path)) {
                    try {
                        String source = Files.readString(file_path);

                        ALLOYLexer lexer = new ALLOYLexer(CharStreams.fromString(source));
                        ALLOYParser parser = new ALLOYParser(new CommonTokenStream(lexer));
                        ParseTree tree = parser.specification();

                        System.out.println("File: " + file_path.toFile());

                        //signatures that do not have fields by kind
                        List<String> topSigsNoFields = SigRetriever.getSigs("topnofields", parser, tree);

                        List<String> subSigsNoFields = SigRetriever.getSigs("subsetsnofields", parser, tree);

                        List<String> extSigsNoFields = SigRetriever.getSigs("extensionsnofields", parser, tree);

                        List<String> oneExtSigsNoFields = SigRetriever.getSigs("oneextensionsnofields", parser, tree);

                        List<String> loneExtSigsNoFields = SigRetriever.getSigs("loneextensionsnofields", parser, tree);

                        List<String> someExtSigsNoFields = SigRetriever.getSigs("someextensionsnofields", parser, tree);

                        List<String> nomultExtSigsNoFields = SigRetriever.getSigs("nomultextensionsnofields", parser, tree);

                        List<String> nonOneExtSigsNoFields = Lists.newArrayList(Iterables.concat(loneExtSigsNoFields,
                                someExtSigsNoFields, nomultExtSigsNoFields));

                        List<String> oneSigsNoFields = SigRetriever.getSigs("onenofields", parser, tree);


                        // extracting one extension sigs to get a tally up used when computing percentage
                        List<String> oneExtSigs = SigRetriever.getSigs("oneextensions", parser, tree);

                        // extracting non-one extension sigs to get a tally up used when computing percentage
                        List<String> loneExtSigs = SigRetriever.getSigs("loneextensions", parser, tree);

                        List<String> someExtSigs = SigRetriever.getSigs("someextensions", parser, tree);

                        List<String> nomultExtSigs = SigRetriever.getSigs("nomultextensions", parser, tree);

                        List<String> nonOneExtSigs = Lists.newArrayList(Iterables.concat(loneExtSigs,
                                someExtSigs, nomultExtSigs));

                        //incrementing counters for model
                        topSigsNoFields_model += topSigsNoFields.size();
                        subSigsNoFields_model += subSigsNoFields.size();
                        extSigsNoFields_model += extSigsNoFields.size();
                        oneExtSigsNoFields_model += oneExtSigsNoFields.size();
                        nonOneExtSigsNoFields_model += nonOneExtSigsNoFields.size();
                        oneSigsNoFields_model += oneSigsNoFields.size();

                        nonOneExtSigs_model += nonOneExtSigs.size();
                        oneExtSigs_model += oneExtSigs.size();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);

            System.out.println("Top-level signatures without fields: " + topSigsNoFields_model);
            System.out.println("Subset signatures without fields: " + subSigsNoFields_model);
            System.out.println("Extension signatures without fields: " + extSigsNoFields_model);
            System.out.println("One extension signatures without fields: " + oneExtSigsNoFields_model);
            System.out.println("Non-one extension signatures without fields: " + nonOneExtSigsNoFields_model);
            System.out.println("One signatures without fields: " + oneSigsNoFields_model);
            System.out.println("Non-one extension signatures: " + nonOneExtSigs_model);
            System.out.println("One extension signatures: " + oneExtSigs_model + "\n");

            //incrementing counters for total numbers across all models
            totTopSigsNoFields += topSigsNoFields_model;
            totSubSigsNoFields += subSigsNoFields_model;
            totExtSigsNoFields += extSigsNoFields_model;
            totOneExtSigsNoFields += oneExtSigsNoFields_model;
            totNonOneExtSigsNoFields += nonOneExtSigsNoFields_model;
            totOneSigsNoFields += oneSigsNoFields_model;
            totNonOneExtSigs += nonOneExtSigs_model;
            totOneExtSigs += oneExtSigs_model;

        }
        System.out.println("Total number of top-level signatures without fields: " + totTopSigsNoFields);
        System.out.println("Total number of subset signatures without fields: " + totSubSigsNoFields);
        System.out.println("Total number of extension signatures without fields: " + totExtSigsNoFields);
        System.out.println("Total number of one extension signatures without fields: " + totOneExtSigsNoFields);
        System.out.println("Total number of non-one extension signatures without fields: " + totNonOneExtSigsNoFields);
        System.out.println("Total number of one signatures without fields: " + totOneSigsNoFields);

        System.out.println();
        System.out.println("Total number of non-one extension sigs: " + totNonOneExtSigs);
        System.out.println("Total number of one extension sigs: " + totOneExtSigs);
    }
}
