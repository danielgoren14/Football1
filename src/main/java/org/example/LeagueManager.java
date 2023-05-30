package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

public class LeagueManager {
    public List<Match> matches = new ArrayList<>();
    public List<Team> teamList;
    public List<Match> possibleMatches = new ArrayList<>();
    private Map<List<Team>, List<Team>> matching;

    public LeagueManager() {
        List<String> data = FileHandler.readFile2();
        teamList = data
                .stream()
                .map(s -> new Team(s))
                .collect(Collectors.toList());
    }

    public List<Match> findMatchesByTeam(int teamId) {
        return this.matches
                .stream()
                .filter(match -> match.didTeamPlayGame(teamId))
                .collect(Collectors.toList());

        // working
    }

    public List<Team> findTopScoringTeams(int n) {
        Map<Team, Long> temp = this.matches
                .stream()
                .map(match -> match.getGoals())
                .flatMap(List::stream)
                .map(Goal::getScorer)
                .map(player -> findPlayerTeam(player))
                .collect(Collectors.groupingBy(Function.identity(), counting()));

        return temp.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();

        //working ( if team did not score it will now count )
    }


    public List<Player> findPlayersWithAtLeastNGoals(int n) {
        Map<Player, Long> temp = this.matches
                .stream()
                .map(match -> match.getGoals())
                .flatMap(List::stream)
                .map(goal -> goal.getScorer())
                .collect(Collectors.groupingBy(player -> player, counting()));

        return temp
                .keySet()
                .stream()
                .filter(key -> temp.get(key) >= n)
                .collect(Collectors.toList());
    }

    //    private Team getTeamByPosition(int position) {
////        String pointPosition = this.data.stream().map(id->id.substring(0,1))
////                .filter(s1 -> s1.equals(position)).toString();
////        return teamList.stream().filter(team -> team.equals(pointPosition)).findAny().get();
////        List<Player> scorerList = this.matches.stream()
////                .map(match -> match.getGoals())
////                .flatMap(List::stream)
////                .map(Goal::getScorer)
////                .toList();
////        Map<Team,Long> goalsAmountForEachTeam = goalsAmountForEachTeam();
////        return goalsAmountForEachTeam
////                .keySet().stream()
////                .sorted()
////
////                .toList().get(position);
//    }
    private Map<Team, Long> goalsAmountForEachTeam() {
        return this.matches
                .stream()
                .map(match -> match.getGoals())
                .flatMap(List::stream)
                .map(Goal::getScorer)
                .map(player -> findPlayerTeam(player))
                .collect(Collectors.groupingBy(Function.identity(), counting()));
    }

//    private Map<Integer, Integer> getTopScorers(int n){
//
//    }


    public Player getPlayerById(int id) {
        return teamList
                .stream()
                .flatMap(team -> team.getPlayerList().stream())
                .filter(player -> player.getId() == id)
                .findFirst().orElse(null);
    }

    public Team findPlayerTeam(Player player) {
        return this.teamList
                .stream()
                .filter(team -> team.getPlayerList().contains(player))//{1,4,2,6,3},3
                .findFirst()
                .get();
    }

    public static List<Player> createPlayerList() {
        return Stream.generate(Player::new).limit(15).collect(Collectors.toList());
    }

    public List<Match> generatePossibleMatches() {

        possibleMatches = teamList.stream()
                .flatMap(team1 -> teamList
                        .stream()
                        .filter(team2 -> team1 != team2 && team2.getId() > team1.getId())
                        .map(team2 -> new Match(Utils.getNewMatchId(), team1, team2, generateGoalList(team1, team2))))
                .toList();

        return possibleMatches;
    }



   /* public List<Integer> generateMatchDay() {
    Random random = new Random();
    List<Integer>lol=generatePossibleMatches().stream().map(match->match.getId()).sorted(number->number.intValue(random.nextInt(1,46))).collect(Collectors.toList());
        System.out.println(lol);
    return lol;
    }*/


    public List<Match> generateMatchDay11() {
        List<Team> playedTeams = new ArrayList<>();
        List output = new ArrayList<>();

        //     while (output.size() != 5) {
        output = possibleMatches
                .stream()
                .skip(10)
                .filter(match -> {
                    // Check if the home team and away team have already played.
                    if (!playedTeams.contains(match.getHomeTeam()) && !playedTeams.contains(match.getAwayTeam())) {

                        // Add the home team and away team to the played teams set.
                        playedTeams.add(match.getHomeTeam());
                        playedTeams.add(match.getAwayTeam());

                        // Return true to indicate that the match should be included in the output.
                        return true;
                    } else {
                        // Return false to indicate that the match should not be included in the output.
                        return false;
                    }
                })
                .limit(5)
                .collect(Collectors.toList());
        //      }
        possibleMatches = possibleMatches
                .stream()
                .filter(Predicate.not(output::contains))
                .collect(Collectors.toList());
        System.out.println(possibleMatches.size());
        return output;
    }


/*
        List<Team> aaa = new ArrayList<>();

        possibleMatches.stream()
                .forEach(match -> {
                    if (aaa.contains(match.getAwayTeam()) && aaa.contains(match.getHomeTeam())) {
                        aaa.addAll(match.getTeams());
                    }
                });
        matching.put(teamList, aaa);
        List<Match> LOL = matching
                .entrySet()
                .stream()
                .map(listListEntry -> matches.
                        stream()
                        .map(Match::getTeams).flatMap(List::stream))
                .filter(mach -> matches.equals(matching.
                        entrySet()
                        .stream()
                        .map(list -> list.getValue()))).filter(match -> match.map())
                .toList();

//        System.out.println(newGames.size());


  /*      List<Match> needToRemove = index
                .stream().
                map(num -> possibleMatches.get(num))
                .collect(Collectors.toList());

        possibleMatches = possibleMatches
                .stream()
                .filter(Predicate.not(needToRemove::contains))
                .collect(Collectors.toList());
*/
//
//        System.out.println(possibleMatches.size());
//
//
//        return;
//    }

    public boolean nameName(Team a, Team b) {
        long count = possibleMatches
                .stream()
                .filter(match -> match.didTeamPlayGame(a.getId()) && match.didTeamPlayGame(b.getId()))
                .count();

        return count > 1;
    }

    public List<Goal> generateGoalList(Team team1, Team team2) {
        List<Player> playerList = team1.getPlayerList();
        playerList.addAll(team2.getPlayerList());

        Random random = new Random();
        return Stream
                .generate(() -> new Goal(Utils.getNewGoalId(), random.nextInt(90), playerList.get(random.nextInt(playerList.size()))
                )).limit(random.nextInt(5))
                .collect(Collectors.toList());
    }

    public List<Match> getMatches() {
        return this.matches;
    }

}
