package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class LogVARReport implements Serializable {

    private String matchId;
    private int minute;
    private String playerName;
    private String incident;
    private String decision;
    private String comments;

    public LogVARReport() {
    }

    public LogVARReport(String matchId,
                        int minute,
                        String playerName,
                        String incident,
                        String decision,
                        String comments) {

        this.matchId = matchId;
        this.minute = minute;
        this.playerName = playerName;
        this.incident = incident;
        this.decision = decision;
        this.comments = comments;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
