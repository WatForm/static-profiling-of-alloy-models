package com.alloyprofiling.characteristics.multifile.constraints;

import com.alloyprofiling.ALLOYLexer;
import com.alloyprofiling.ALLOYParser;
import com.alloyprofiling.ResultWriter;
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
import java.util.List;
import java.util.Scanner;

//class that counts the number of run and check commands in Alloy models
public class RunCheck {
    //counter for total number of commands (run + check) in all Alloy models
    private static int totCmd = 0;
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
        String fp_run = directoryName + "run.txt";
        //file containing number of check commands per model
        String fp_check = directoryName + "check.txt";
        //file containing total number of command queries per model
        String fp_cmd = directoryName + "cmd.txt";

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
            Files.deleteIfExists(Paths.get(fp_check));
            Files.deleteIfExists(Paths.get(fp_cmd));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //file containing model names
        File models = new File("Results\\models.txt");
        Scanner sc = new Scanner(models);

        //iterating over corpus of models
        while (sc.hasNextLine()) {
            int runCmds_model = 0;
            int checkCmds_model = 0;
            int cmds_model = 0;
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

                        //pattern to find run commands
                        ParseTreePattern p_run = parser.compileParseTreePattern("<name_cmd_opt> run <nameOrBlock> <scope_opt>", ALLOYParser.RULE_cmdDecl);
                        List<ParseTreeMatch> matches_run = p_run.findAll(tree, "//paragraph/*");

                        //pattern to find check commands
                        ParseTreePattern p_check = parser.compileParseTreePattern("<name_cmd_opt> check <nameOrBlock> <scope_opt>", ALLOYParser.RULE_cmdDecl);
                        List<ParseTreeMatch> matches_check = p_check.findAll(tree, "//paragraph/*");

                        System.out.println("Number of run commands in file: " + matches_run.size());
                        System.out.println("Number of check commands in file: " + matches_check.size());
                        System.out.println("Number of commands in file: " + (matches_check.size() + matches_run.size()));

                        //incrementing model counters
                        runCmds_model += matches_run.size();
                        checkCmds_model += matches_check.size();
                        cmds_model += matches_check.size() + matches_run.size();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Model: " + model);
            System.out.println("Number of run commands in model: " + runCmds_model);
            System.out.println("Number of check commands in model: " + checkCmds_model);
            System.out.println("Number of commands in model: " + cmds_model + "\n");

            //writing result file containing the number of run/checks in each file
            ResultWriter.writeResults(fp_run, runCmds_model);
            ResultWriter.writeResults(fp_check, checkCmds_model);
            ResultWriter.writeResults(fp_cmd, cmds_model);

            //incrementing total counters (over all models)
            totCmd += cmds_model;
        }
        System.out.println("Total number of commands: " + totCmd);
    }
}
