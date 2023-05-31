package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Match {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private List<Goal> goals;

    public Match(int id, Team homeTeam, Team awayTeam, List<Goal> goals) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.goals = goals;
    }

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

    public boolean didTeamPlayGame(int id){
        return this.awayTeam.sameID(id) || this.homeTeam.sameID(id);
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id +
                ", homeTeam=" + homeTeam.getName() +
                ", awayTeam=" + awayTeam.getName() +
                "}\n";
    }


    public  List<Team> getTeams(){
        return List.of(this.homeTeam , this.awayTeam);
    }



}
