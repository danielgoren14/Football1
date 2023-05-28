package org.example;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private List<Goal> goals;

    public int getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public Match(int id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.goals = new ArrayList<>();
    }

    public boolean isPlay(int id){
        return this.awayTeam.sameID(id) || this.homeTeam.sameID(id);
    }


}
