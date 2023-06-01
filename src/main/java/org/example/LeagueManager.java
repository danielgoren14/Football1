package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class LeagueManager {
    public List<Match> matches = new ArrayList<>();
    public List<Team> teamList;
    public List<Match> possibleMatches = new ArrayList<>();
    private Map<List<Team>, List<Team>> matching;

    public LeagueManager() {
        List<String> data = FileHandler.readFile();
        teamList = data
                .stream()
                .map(Team::new)
                .collect(toList());
    }

    public List<Match> findMatchesByTeam(int teamId) {
        return this.matches
                .stream()
                .filter(match -> match.didTeamPlayGame(teamId))
                .collect(toList());
        // working
    }

    public List<Team> findTopScoringTeams(int n) {
        Map<Team, Long> temp = this.matches
                .stream()
                .map(match -> match.getGoals())
                .flatMap(List::stream)
                .map(Goal::getScorer)
                .map(player -> findPlayerTeam(player))
                .collect(groupingBy(Function.identity(), counting()));

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
                .map(Match::getGoals)
                .flatMap(List::stream)
                .map(Goal::getScorer)
                .collect(groupingBy(player -> player, counting()));

        return temp
                .keySet()
                .stream()
                .filter(key -> temp.get(key) >= n)
                .collect(toList());
        //working
    }

        private Team getTeamByPosition(int position) {
        String pointPosition = matches
                .stream()
                .map(match -> match.getGoals())
                .filter(s1 -> s1.equals(position))
                .toString();
        return teamList.stream()
                .filter(team -> team.equals(pointPosition))
                .findAny()
                .get();
//        List<Player> scorerList = this.matches.stream()
//                .map(match -> match.getGoals())
//                .flatMap(List::stream)
//                .map(Goal::getScorer)
//                .toList();
//        Map<Team,Long> goalsAmountForEachTeam = goalsAmountForEachTeam();
//        return goalsAmountForEachTeam
//                .keySet().stream()
//                .sorted()
//                .toList().get(position);
}
    public Map<Team, Long> goalsAmountForEachTeam() {
        return this.matches
                .stream()
                .map(Match::getGoals)
                .flatMap(List::stream)
                .map(Goal::getScorer)
                .map(this::findPlayerTeam)
                .collect(groupingBy(Function.identity(), counting()));
    }

//    private Map<Integer, Integer> getTopScorers(int n){
//    }


    public Player getPlayerById(int id) {
        return teamList
                .stream()
                .flatMap(team -> team.getPlayerList().stream())
                .filter(player -> player.getId() == id)
                .findFirst().orElse(null);
    }

    public Team findPlayerTeam(Player player) {

        Team team1 = this.teamList
                .stream()
                .filter(team -> team.getPlayerList().contains(player)).findFirst().get();
        return team1;
    }

    public static List<Player> createPlayerList() {
        List<Player>  temp = new ArrayList<>();
        temp = Stream.generate(Player::new).limit(15).collect(toList());
        return temp;
    }
    public void addPointsForTeams (Match currentMatch) {
        Team homeTeam = currentMatch.getHomeTeam();
        Team awayTeam = currentMatch.getAwayTeam();
        Long goalsForHomeTeam = currentMatch.getGoals().stream()
                .map(Goal::getScorer)
                .filter(player -> findPlayerTeam(player) == homeTeam)
                .count();
        Long goalsForAwayTeam = currentMatch.getGoals().stream()
                .map(Goal::getScorer)
                .filter(player -> findPlayerTeam(player) == awayTeam)
                .count();
        if (goalsForHomeTeam > goalsForAwayTeam) {
            homeTeam.addPoints(3);
        } else if (goalsForHomeTeam == goalsForAwayTeam) {
            homeTeam.addPoints(1);
            awayTeam.addPoints(1);
        } else {
            awayTeam.addPoints(3);
        }
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


    public List<Match> generateMatchDay() {
        int size = 2;
        List<Match> output = new ArrayList<>();
        if (possibleMatches.size() == 25) {
            size = 0;
        } else if (possibleMatches.size() == 20) {
            size = 2;
        } else if (possibleMatches.size() == 10) {
            size = 1;
        } else if (possibleMatches.size() == 5) {
            size = 0;
        } else if (possibleMatches.size() == 0) {
            return null;
        }
        while (output.size() != 5) {
            output.clear();
            List<Team> playedTeams = new ArrayList<>();
            output = possibleMatches
                    .stream()
                    .skip(size)
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
                    .collect(toList());
            if (output.size() != 5) {
                List<Match> newLIst = new ArrayList<>(possibleMatches);
                Collections.reverse(newLIst);
                possibleMatches = List.copyOf(newLIst);
            }
        }
        possibleMatches = possibleMatches
                .stream()
                .filter(Predicate.not(output::contains))
                .collect(toList());
        matches.addAll(output);
        return output;
    }

    public List<Goal> generateGoalList (Team team1, Team team2) {
        List<Player> playerList = new ArrayList<>(team1.getPlayerList());
        playerList.addAll(team2.getPlayerList());

        Random random = new Random();
        return Stream
                .generate(() -> new Goal(Utils.getNewGoalId(), random.nextInt(11), playerList.get(random.nextInt(playerList.size()))
                )).limit(random.nextInt(5))
                .collect(toList());
    }

}
