package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;
import java.time.LocalDate;

public class SubmitMatchReport implements Serializable {

    private String matchId;
    private String homeTeam;
    private String awayTeam;
    private LocalDate matchDate;
    private String finalScore;
    private String goalScorers;
    private int yellowCards;
    private int redCards;
    private String matchSummary;
    private String matchStatus;

    // Default Constructor
    public SubmitMatchReport() {
    }

    // Parameterized Constructor
    public SubmitMatchReport(String matchId,
                             String homeTeam,
                             String awayTeam,
                             LocalDate matchDate,
                             String finalScore,
                             String goalScorers,
                             int yellowCards,
                             int redCards,
                             String matchSummary,
                             String matchStatus) {

        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.finalScore = finalScore;
        this.goalScorers = goalScorers;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.matchSummary = matchSummary;
        this.matchStatus = matchStatus;
    }

    // Getters

    public String getMatchId() {
        return matchId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public String getGoalScorers() {
        return goalScorers;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public String getMatchSummary() {
        return matchSummary;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    // Setters

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public void setGoalScorers(String goalScorers) {
        this.goalScorers = goalScorers;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public void setMatchSummary(String matchSummary) {
        this.matchSummary = matchSummary;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    @Override
    public String toString() {
        return "SubmitMatchReport{" +
                "matchId='" + matchId + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", matchDate=" + matchDate +
                ", finalScore='" + finalScore + '\'' +
                ", goalScorers='" + goalScorers + '\'' +
                ", yellowCards=" + yellowCards +
                ", redCards=" + redCards +
                ", matchSummary='" + matchSummary + '\'' +
                ", matchStatus='" + matchStatus + '\'' +
                '}';
    }
}