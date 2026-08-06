package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class LiveUpdate implements Serializable {

    private String matchId;
    private String minute;
    private String eventType;
    private String playerName;
    private String score;
    private String details;

    // Default Constructor
    public LiveUpdate() {
    }

    // Parameterized Constructor
    public LiveUpdate(String matchId,
                      String minute,
                      String eventType,
                      String playerName,
                      String score,
                      String details) {

        this.matchId = matchId;
        this.minute = minute;
        this.eventType = eventType;
        this.playerName = playerName;
        this.score = score;
        this.details = details;
    }

    // Getters

    public String getMatchId() {
        return matchId;
    }

    public String getMinute() {
        return minute;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getScore() {
        return score;
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

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "LiveUpdate{" +
                "matchId='" + matchId + '\'' +
                ", minute='" + minute + '\'' +
                ", eventType='" + eventType + '\'' +
                ", playerName='" + playerName + '\'' +
                ", score='" + score + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}