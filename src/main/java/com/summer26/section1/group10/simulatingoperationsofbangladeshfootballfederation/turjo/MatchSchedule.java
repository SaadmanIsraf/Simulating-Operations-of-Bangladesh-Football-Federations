package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class MatchSchedule implements Serializable {

    private String matchId;
    private String homeTeam;
    private String awayTeam;
    private String matchDate;
    private String matchTime;
    private String venue;

    // Default Constructor
    public MatchSchedule() {
    }

    // Parameterized Constructor
    public MatchSchedule(String matchId,
                         String homeTeam,
                         String awayTeam,
                         String matchDate,
                         String matchTime,
                         String venue) {

        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.venue = venue;
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

    public String getMatchDate() {
        return matchDate;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public String getVenue() {
        return venue;
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

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "MatchSchedule{" +
                "matchId='" + matchId + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", matchDate='" + matchDate + '\'' +
                ", matchTime='" + matchTime + '\'' +
                ", venue='" + venue + '\'' +
                '}';
    }
}
