package org.example;

import java.util.List;

public class Team {
    private int id;
    private String name;
    private List<Player> playerList;


    public Team(String line) {
        String[] temp =line.split(",");
        this.id = Integer.parseInt(temp[0]);
        this.name = temp[1];
        this.playerList=LeagueManager.createPlayerList();
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
        return "Team name : " +getName() +" Team ID: " +getId() +" \n";
    }



    public List<Player> getPlayerList() {
        return playerList;
    }


}
