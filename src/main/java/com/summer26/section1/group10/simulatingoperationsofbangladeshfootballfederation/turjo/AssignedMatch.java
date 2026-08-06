package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;
import java.time.LocalDate;

public class AssignedMatch implements Serializable {

    private String matchId;
    private String homeTeam;
    private String awayTeam;
    private LocalDate matchDate;
    private String matchTime;
    private String venue;
    private String role;

    // Default Constructor
    public AssignedMatch() {
    }

    // Parameterized Constructor
    public AssignedMatch(String matchId,
                         String homeTeam,
                         String awayTeam,
                         LocalDate matchDate,
                         String matchTime,
                         String venue,
                         String role) {

        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.venue = venue;
        this.role = role;
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

    public String getMatchTime() {
        return matchTime;
    }

    public String getVenue() {
        return venue;
    }

    public String getRole() {
        return role;
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

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AssignedMatch{" +
                "matchId='" + matchId + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", matchDate=" + matchDate +
                ", matchTime='" + matchTime + '\'' +
                ", venue='" + venue + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}