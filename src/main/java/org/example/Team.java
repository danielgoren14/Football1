package org.example;

import java.util.List;

public class Team {
    private  int id;
    private  String name;
    private  List<Player> playerList;
//    private int points;
    //private List<Goal> goalList;

    public Team(String line) {
        String[] temp =line.split(",");
        this.id = Integer.parseInt(temp[0]);
        this.name = temp[1];
        this.playerList=LeagueManager.createPlayerList();
        /*this.playerList
                .stream()
                .forEach(player -> player.setPlayerTeam(this));
        */
    }

    public boolean sameID(int id){
        return this.id == id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Team{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", playerList=").append(playerList);
        sb.append('}');
        return sb.toString();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }


}
