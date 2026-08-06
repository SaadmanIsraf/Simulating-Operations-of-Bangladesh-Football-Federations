package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class PersonalStats implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int playerId;
    private String playerName;
    private int goals;
    private int assists;
    private int matchesPlayed;
    private String medicalCondition;
    private LocalDate lastUpdated;

    public PersonalStats() {
    }

    public PersonalStats(
            int playerId,
            String playerName,
            int goals,
            int assists,
            int matchesPlayed,
            String medicalCondition,
            LocalDate lastUpdated) {

        this.playerId = playerId;
        this.playerName = playerName;
        this.goals = goals;
        this.assists = assists;
        this.matchesPlayed = matchesPlayed;
        this.medicalCondition = medicalCondition;
        this.lastUpdated = lastUpdated;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void addStatistics(
            int addedGoals,
            int addedAssists,
            int addedMatchesPlayed) {

        this.goals += addedGoals;
        this.assists += addedAssists;
        this.matchesPlayed += addedMatchesPlayed;
        this.lastUpdated = LocalDate.now();
    }

    @Override
    public String toString() {
        return "PersonalStats{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", goals=" + goals +
                ", assists=" + assists +
                ", matchesPlayed=" + matchesPlayed +
                ", medicalCondition='" + medicalCondition + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}