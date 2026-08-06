package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class VARReport implements Serializable {

    private String matchId;
    private String minute;
    private String playerName;
    private String reviewType;
    private String decision;
    private String details;

    // Default Constructor
    public VARReport() {
    }

    // Parameterized Constructor
    public VARReport(String matchId,
                     String minute,
                     String playerName,
                     String reviewType,
                     String decision,
                     String details) {

        this.matchId = matchId;
        this.minute = minute;
        this.playerName = playerName;
        this.reviewType = reviewType;
        this.decision = decision;
        this.details = details;
    }

    // Getters

    public String getMatchId() {
        return matchId;
    }

    public String getMinute() {
        return minute;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getReviewType() {
        return reviewType;
    }

    public String getDecision() {
        return decision;
    }

    public String getDetails() {
        return details;
    }

    // Setters

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "VARReport{" +
                "matchId='" + matchId + '\'' +
                ", minute='" + minute + '\'' +
                ", playerName='" + playerName + '\'' +
                ", reviewType='" + reviewType + '\'' +
                ", decision='" + decision + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}