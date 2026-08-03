package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;
import java.time.LocalDate;

public class Announcement implements Serializable {
    private String title;
    private String match;
    private String competition;
    private String venue;
    private String sponsor;
    private String place;
    private LocalDate matchdate;
    private String matchtime;
    private String matchtype;
    private String details;

    public Announcement(String title, String match, String competition, String venue, String sponsor, String place, LocalDate matchdate, String matchtime, String matchtype, String details) {
        this.title = title;
        this.match = match;
        this.competition = competition;
        this.venue = venue;
        this.sponsor = sponsor;
        this.place = place;
        this.matchdate = matchdate;
        this.matchtime = matchtime;
        this.matchtype = matchtype;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getMatchdate() {
        return matchdate;
    }

    public void setMatchdate(LocalDate matchdate) {
        this.matchdate = matchdate;
    }

    public String getMatchtime() {
        return matchtime;
    }

    public void setMatchtime(String matchtime) {
        this.matchtime = matchtime;
    }

    public String getMatchtype() {
        return matchtype;
    }

    public void setMatchtype(String matchtype) {
        this.matchtype = matchtype;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}