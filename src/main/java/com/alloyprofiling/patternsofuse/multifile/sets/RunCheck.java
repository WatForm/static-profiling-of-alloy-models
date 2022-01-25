package com.alloyprofiling.patternsofuse.multifile.sets;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
import com.alloyprofiling.retrievers.RelationRetriever;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//class that counts the number of run and check commands in Alloy models
public class RunCheck {
    //counter for total number of commands (run + check) in all Alloy models
    private static int totRunConstr = 0;
    public static void main(String[] args) throws FileNotFoundException {
        //results directory path
        String directoryName = "Results\\CharacteristicsOfModels\\MultiFile\\Constraints\\";

        //creating directory if it doesn't exist
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
        }

        String path = "corpus";
        //String path = "expert";
        System.out.println("Counting run vs. check for Alloy models in " + path);


        //file containing number of run commands per model
        String fp_run = directoryName + "runConst.txt";

        /*
        //file containing number of run commands per model
        String fp_run = directoryName + "run_expert.txt";
        //file containing number of check commands per model
        String fp_check = directoryName + "check_expert.txt";
        //file containing total number of command queries per model
        String fp_cmd = directoryName + "cmd_expert.txt"; */


        //deleting results file if it already exists
        try {
            Files.deleteIfExists(Paths.get(fp_run));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int runCmds_model = 0;
            int cmds_model = 0;
            String model = sc.nextLine();
            List<String> fields_model = new ArrayList<>();
            List<ParseTreeMatch> matches_model = new ArrayList<>();
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

                        //getting all fields using RelationRetriever
                        List<String> fields = RelationRetriever.getRelations(parser, tree);

                        //adding fields in file to model fields list
                        fields_model.addAll(fields);

                        //pattern to find run commands
                        ParseTreePattern p_run = parser.compileParseTreePattern("<name_cmd_opt> run { some <expr> } <scope_opt>", ALLOYParser.RULE_cmdDecl);
                        List<ParseTreeMatch> matches_run = p_run.findAll(tree, "//paragraph/*");

                        matches_model.addAll(matches_run);

                        //pattern to find check commands
                        //ParseTreePattern p_check = parser.compileParseTreePattern("<name_cmd_opt> check <nameOrBlock> <scope_opt>", ALLOYParser.RULE_cmdDecl);
                       // List<ParseTreeMatch> matches_check = p_check.findAll(tree, "//paragraph/*");

                        System.out.println("Number of run commands in file: " + matches_run.size());


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            List<ParseTreeMatch> matches = matches_model.stream().filter(m ->
                    fields_model.contains(m.get("expr").getText())).collect(Collectors.toList());

            List<String> matches_str = matches.stream().map(m -> m.getTree().getText()).collect(Collectors.toList());

            System.out.println("Number of run commands with constraint in model: " + matches.size() + " " + matches_str);

            //writing result file containing the number of run/checks in each file
            ResultWriter.writeResults(fp_run, runCmds_model);

            //incrementing total counters (over all models)
            totRunConstr += matches.size();
        }
        System.out.println("Total number of run commands with constraint: " + totRunConstr);
    }
}
