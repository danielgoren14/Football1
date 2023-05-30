package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LeagueManager leagueManager = new LeagueManager();
//        List<Goal> goals = new ArrayList<>();
//        goals.add(new Goal(Utils.getNewGoalId(), 5, leagueManager.getPlayerById(1)));
//        goals.add(new Goal(Utils.getNewGoalId(), 10, leagueManager.getPlayerById(2)));
//        goals.add(new Goal(Utils.getNewGoalId(), 15, leagueManager.getPlayerById(3)));
//        leagueManager.matches.add(new Match(Utils.getNewMatchId(), leagueManager.teamList.get(0), leagueManager.teamList.get(1), goals));
//
//
//        List<Goal> goals1 = new ArrayList<>();
//        goals1.add(new Goal(Utils.getNewGoalId(), 5, leagueManager.getPlayerById(4)));
//        goals1.add(new Goal(Utils.getNewGoalId(), 10, leagueManager.getPlayerById(5)));
//        goals1.add(new Goal(Utils.getNewGoalId(), 15, leagueManager.getPlayerById(6)));
//        leagueManager.matches.add(new Match(Utils.getNewMatchId(), leagueManager.teamList.get(4), leagueManager.teamList.get(0), goals1));
//
//        List<Goal> goals2 = new ArrayList<>();
//        goals2.add(new Goal(Utils.getNewGoalId(), 5, leagueManager.getPlayerById(1)));
//        goals2.add(new Goal(Utils.getNewGoalId(), 10, leagueManager.getPlayerById(2)));
//        goals2.add(new Goal(Utils.getNewGoalId(), 15, leagueManager.getPlayerById(9)));
//        leagueManager.matches.add(new Match(Utils.getNewMatchId(), leagueManager.teamList.get(1), leagueManager.teamList.get(0), goals2));

        //    System.out.println(leagueManager.findPlayersWithAtLeastNGoals(2));

        System.out.println(leagueManager.generatePossibleMatches());

   /*     List<Match> newLIst = new ArrayList<>(leagueManager.possibleMatches);
        Collections.reverse(newLIst);
        leagueManager.possibleMatches= List.copyOf(newLIst);
*/
        System.out.println(leagueManager.generateMatchDay11());
        System.out.println(leagueManager.possibleMatches);
        System.out.println(leagueManager.generateMatchDay11());
        System.out.println(leagueManager.possibleMatches);
        System.out.println(leagueManager.generateMatchDay11());
        System.out.println(leagueManager.possibleMatches);
        System.out.println(leagueManager.generateMatchDay11());
        System.out.println(leagueManager.possibleMatches);
        System.out.println(leagueManager.generateMatchDay11());
        System.out.println(leagueManager.possibleMatches);
        System.out.println(leagueManager.generateMatchDay11());
        System.out.println(leagueManager.possibleMatches);
        System.out.println(leagueManager.generateMatchDay11());
        System.out.println(leagueManager.possibleMatches);
        System.out.println(leagueManager.generateMatchDay11());
        System.out.println(leagueManager.possibleMatches);
        System.out.println(leagueManager.generateMatchDay11());
       System.out.println(leagueManager.possibleMatches);
       System.out.println(leagueManager.generateMatchDay11());
       System.out.println(leagueManager.possibleMatches);
       System.out.println(leagueManager.generateMatchDay11());

    }
}