package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class VerifyPlayerEligibility implements Serializable {

    private String playerId;
    private String playerName;
    private String team;
    private int yellowCards;
    private int redCards;
    private String eligibility;

    public VerifyPlayerEligibility() {
    }

    public VerifyPlayerEligibility(String playerId,
                                   String playerName,
                                   String team,
                                   int yellowCards,
                                   int redCards,
                                   String eligibility) {

        this.playerId = playerId;
        this.playerName = playerName;
        this.team = team;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.eligibility = eligibility;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    @Override
    public String toString() {
        return "VerifyPlayerEligibility{" +
                "playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", team='" + team + '\'' +
                ", yellowCards=" + yellowCards +
                ", redCards=" + redCards +
                ", eligibility='" + eligibility + '\'' +
                '}';
    }
}
