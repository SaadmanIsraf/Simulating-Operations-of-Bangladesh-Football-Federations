package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;
import java.time.LocalDate;

public class ManageMatch implements Serializable {

    private String matchId;
    private String competition;
    private String homeTeam;
    private String awayTeam;
    private LocalDate matchDate;
    private String matchTime;
    private String stadium;
    private String matchStatus;

    // Default Constructor
    public ManageMatch() {
    }

    // Parameterized Constructor
    public ManageMatch(String matchId,
                       String competition,
                       String homeTeam,
                       String awayTeam,
                       LocalDate matchDate,
                       String matchTime,
                       String stadium,
                       String matchStatus) {

        this.matchId = matchId;
        this.competition = competition;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.stadium = stadium;
        this.matchStatus = matchStatus;
    }

    // Getters

    public String getMatchId() {
        return matchId;
    }

    public String getCompetition() {
        return competition;
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

    public String getMatchTime() {
        return matchTime;
    }

    public String getStadium() {
        return stadium;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    // Setters

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
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

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    @Override
    public String toString() {
        return "ManageMatch{" +
                "matchId='" + matchId + '\'' +
                ", competition='" + competition + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", matchDate=" + matchDate +
                ", matchTime='" + matchTime + '\'' +
                ", stadium='" + stadium + '\'' +
                ", matchStatus='" + matchStatus + '\'' +
                '}';
    }
}