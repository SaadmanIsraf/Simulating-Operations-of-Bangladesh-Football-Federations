package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class PlayerTransfer implements Serializable {

    private String playerName;
    private String fromClub;
    private String toClub;
    private String decision;

    // Default Constructor
    public PlayerTransfer() {
    }

    // Parameterized Constructor
    public PlayerTransfer(String playerName,
                          String fromClub,
                          String toClub,
                          String decision) {

        this.playerName = playerName;
        this.fromClub = fromClub;
        this.toClub = toClub;
        this.decision = decision;
    }

    // Getters

    public String getPlayerName() {
        return playerName;
    }

    public String getFromClub() {
        return fromClub;
    }

    public String getToClub() {
        return toClub;
    }

    public String getDecision() {
        return decision;
    }

    // Setters

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setFromClub(String fromClub) {
        this.fromClub = fromClub;
    }

    public void setToClub(String toClub) {
        this.toClub = toClub;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    @Override
    public String toString() {
        return "PlayerTransfer{" +
                "playerName='" + playerName + '\'' +
                ", fromClub='" + fromClub + '\'' +
                ", toClub='" + toClub + '\'' +
                ", decision='" + decision + '\'' +
                '}';
    }
}