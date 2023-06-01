package org.example;

public class Main {
    public static void main(String[] args) {
        LeagueManager leagueManager = new LeagueManager();


        leagueManager.generatePossibleMatches();
        leagueManager.generateMatchDay();
        leagueManager.generateMatchDay();
        leagueManager.generateMatchDay();
        leagueManager.generateMatchDay();
        leagueManager.generateMatchDay();
        leagueManager.generateMatchDay();
        leagueManager.generateMatchDay();
        leagueManager.generateMatchDay();
        leagueManager.generateMatchDay();

        System.out.println(leagueManager.generatePossibleMatches());
        System.out.println(leagueManager.getTopScorers(10));
        System.out.println(leagueManager.findPlayersWithAtLeastNGoals(3));
    }
}