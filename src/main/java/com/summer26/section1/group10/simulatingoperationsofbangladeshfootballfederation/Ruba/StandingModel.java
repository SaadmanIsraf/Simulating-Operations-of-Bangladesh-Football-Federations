package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

public class StandingModel {

    private int rank;
    private String team;
    private int played;
    private int points;
    private String tournament;

    public StandingModel(int rank, String team, int played, int points, String tournament) {
        this.rank = rank;
        this.team = team;
        this.played = played;
        this.points = points;
        this.tournament = tournament;
    }

    public int getRank() {
        return rank;
    }

    public String getTeam() {
        return team;
    }

    public int getPlayed() {
        return played;
    }

    public int getPoints() {
        return points;
    }

    public String getTournament() {
        return tournament;
    }
}