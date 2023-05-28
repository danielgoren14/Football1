package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        List<String> data = fileHandler.readFile();

        LeagueManager leagueManager = new LeagueManager();
    }
}