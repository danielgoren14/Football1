package org.example;

import java.util.Random;

public class Player {
    private int id;
    private String firstName;
    private String lastName;
    private Team playerTeam;

    public Player() {
        this.id = Utils.getNewPlayerId();
        this.firstName = Utils.FIRST_NAMES.get(new Random().nextInt(Utils.FIRST_NAMES.size()));
        this.lastName = Utils.LAST_NAMES.get(new Random().nextInt(Utils.LAST_NAMES.size()));
        this.playerTeam=null;
    }



    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public Team getPlayerTeam(){
        return this.playerTeam;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", playerTeam=").append(playerTeam.getName());
        sb.append('}');
        return sb.toString();
    }

    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }
}
