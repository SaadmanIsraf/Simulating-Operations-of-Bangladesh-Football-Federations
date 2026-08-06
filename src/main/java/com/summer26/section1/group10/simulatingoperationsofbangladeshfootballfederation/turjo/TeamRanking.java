package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class TeamRanking implements Serializable {

    private String teamName;
    private int wins;
    private int draws;
    private int losses;
    private int points;

    // Default Constructor
    public TeamRanking() {
    }

    // Parameterized Constructor
    public TeamRanking(String teamName,
                       int wins,
                       int draws,
                       int losses,
                       int points) {

        this.teamName = teamName;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.points = points;
    }

    // Getters

    public String getTeamName() {
        return teamName;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getPoints() {
        return points;
    }

    // Setters

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "TeamRanking{" +
                "teamName='" + teamName + '\'' +
                ", wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", points=" + points +
                '}';
    }
}