package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;
import java.time.LocalDate;

public class MatchSchedule implements Serializable {

    private String matchId;
    private String homeTeam;
    private String awayTeam;
    private String venue;
    private LocalDate date;
    private String time;

    public MatchSchedule() {
    }

    public MatchSchedule(String matchId,
                         String homeTeam,
                         String awayTeam,
                         String venue,
                         LocalDate date,
                         String time) {

        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.venue = venue;
        this.date = date;
        this.time = time;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MatchSchedule{" +
                "matchId='" + matchId + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", venue='" + venue + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                '}';
    }
}