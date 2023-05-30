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

    public Match(int id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.goals = new ArrayList<>();
    }

    public boolean didTeamPlayGame(int id){
        return this.awayTeam.sameID(id) || this.homeTeam.sameID(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Match{");
        sb.append("id=").append(id);
        sb.append(", homeTeam=").append(homeTeam.getName());
        sb.append(", awayTeam=").append(awayTeam.getName());
        sb.append("}\n");
        return sb.toString();
    }


    public  List<Team> getTeams(){
        return List.of(this.homeTeam , this.awayTeam);
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (!Objects.equals(homeTeam, match.awayTeam)) return false;
        if (!Objects.equals(awayTeam, match.homeTeam)) return false;
        if (!Objects.equals(homeTeam, match.homeTeam)) return false;
        return Objects.equals(awayTeam, match.awayTeam);
    }*/

}
