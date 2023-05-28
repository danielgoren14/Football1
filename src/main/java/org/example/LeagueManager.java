package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeagueManager {
    private List<Match> matches;
    private List<Team> teamList;
    private List<Player> playerList;

    public LeagueManager() {
      List<String> data = FileHandler.readFile();

        teamList = data
                .stream()
                .map(s -> new Team(s))
                .collect(Collectors.toList());
    }

    private List<Match> findMatchesByTeam(int teamId) {
        return this.matches
                .stream()
                .filter(match -> match.isPlay(teamId))
                .collect(Collectors.toList());
    }

    private List<Team> findTopScoringTeams(int n) {


        return this.matches
                .stream()
                .map(match -> match.getGoals())
                .flatMap(List::stream)
                .map(Goal::getScorer)

                .map(Player::getPlayerTeam)
                .limit(n)
                .toList();


    }

    private List<Player> findPlayersWithAtLeastNGoals(int n) {
      /*     HashMap<Match,Goal> goals = this.matches
               .stream().
               collect(Collectors.groupingBy(Match::getGoals))
        return this.matches
                .stream().
                collect(Collectors.groupingBy(Match::getGoals))
  */

        Map<Integer, Long> temp = this.matches
                .stream()
                .map(Match::getGoals)
                .flatMap(List::stream)
                .map(Goal::getScorer)
                .collect(Collectors.groupingBy(Player::getId, Collectors.counting()));

        return temp
                .values()
                .stream()
                .filter(aLong -> aLong >= n)
                .map(aLong -> temp.get(aLong))
                .map(number -> getPlayerById(number.intValue()))
                .collect(Collectors.toList());

    }


    //
//    private Team getTeamByPosition(int position) {
//        return this.teamList.stream()
//    }

//    private Map<Integer, Integer> getTopScorers(int n){
//
//    }


    public Player getPlayerById(int id) {
        return playerList
                .stream()
                .filter(player -> player.getId() == id)
                .findFirst().orElse(null);
    }

    public static List<Player> createPlayerList(){
        return  Stream.generate(Player::new).limit(15).collect(Collectors.toList());
    }
    public void generateMatch(){

    }
}
