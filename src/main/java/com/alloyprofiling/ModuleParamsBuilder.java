package com.alloyprofiling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class ModuleParamsBuilder {
    public static LinkedHashMap<String, List<String>> buildImportingImportedParamsMap() throws FileNotFoundException {
        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        // pass the path to the file as a parameter
        File models = new File("Results\\module-params.txt");
        Scanner sc = new Scanner(models);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] arr = line.split(",");
            map.put(arr[0]+","+arr[1],Arrays.asList(Arrays.copyOfRange(arr, 2, arr.length)));
        }
        return map;
    }

    public static LinkedHashMap<String, String> buildImportingImportedMap() throws FileNotFoundException {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        // pass the path to the file as a parameter
        File models = new File("Results\\module-params.txt");
        Scanner sc = new Scanner(models);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] arr = line.split(",");
            map.put(arr[0],arr[1]);
        }
        return map;
    }
}
