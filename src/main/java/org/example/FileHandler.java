package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class FileHandler {
    public static final String PATH_TO_DATA_FILE = "src/main/java/org/example/Assets/Teams.csv";

  /*  public static List<String> readFile1() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_DATA_FILE));
            List<String> teamData = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                teamData.add(line);
            }
            teamData.remove(0);
            bufferedReader.close();
            return teamData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/


    public static List<String> readFile2() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_DATA_FILE));
            return bufferedReader
                    .lines()
                    .skip(1)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<String> readFile() {
        try {
            return Files.lines(Paths.get(FileHandler.PATH_TO_DATA_FILE)).skip(1).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
