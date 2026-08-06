package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class VerifyPlayerEligibility implements Serializable {

    private String playerName;
    private String match;
    private int yellowCards;
    private int redCards;
    private String eligibility;

    // Default Constructor
    public VerifyPlayerEligibility() {
    }

    // Parameterized Constructor
    public VerifyPlayerEligibility(String playerName,
                                   String match,
                                   int yellowCards,
                                   int redCards,
                                   String eligibility) {

        this.playerName = playerName;
        this.match = match;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.eligibility = eligibility;
    }

    // Getters

    public String getPlayerName() {
        return playerName;
    }

    public String getMatch() {
        return match;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public String getEligibility() {
        return eligibility;
    }

    // Setters

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    @Override
    public String toString() {
        return "VerifyPlayerEligibility{" +
                "playerName='" + playerName + '\'' +
                ", match='" + match + '\'' +
                ", yellowCards=" + yellowCards +
                ", redCards=" + redCards +
                ", eligibility='" + eligibility + '\'' +
                '}';
    }
}