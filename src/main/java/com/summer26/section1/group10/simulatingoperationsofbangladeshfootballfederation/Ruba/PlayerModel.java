package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

public class PlayerModel {

    private String playerName;
    private String team;
    private String position;
    private String fitnessStatus;

    public PlayerModel(String playerName, String team, String position, String fitnessStatus) {
        this.playerName = playerName;
        this.team = team;
        this.position = position;
        this.fitnessStatus = fitnessStatus;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }

    public String getFitnessStatus() {
        return fitnessStatus;
    }
}