package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;
import java.time.LocalDate;

public class VARreport implements Serializable {
    private String matchbetween;
    private LocalDate matchday;
    private String minute;
    private String playername;
    private String reviewtype;
    private String vardecision;
    private String finaldecision;
    private String details;

    public VARreport(String matchbetween, LocalDate matchday, String minute, String playername, String reviewtype, String vardecision, String finaldecision, String details) {
        this.matchbetween = matchbetween;
        this.matchday = matchday;
        this.minute = minute;
        this.playername = playername;
        this.reviewtype = reviewtype;
        this.vardecision = vardecision;
        this.finaldecision = finaldecision;
        this.details = details;
    }

    public String getMatchbetween() {
        return matchbetween;
    }

    public void setMatchbetween(String matchbetween) {
        this.matchbetween = matchbetween;
    }

    public LocalDate getMatchday() {
        return matchday;
    }

    public void setMatchday(LocalDate matchday) {
        this.matchday = matchday;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getReviewtype() {
        return reviewtype;
    }

    public void setReviewtype(String reviewtype) {
        this.reviewtype = reviewtype;
    }

    public String getVardecision() {
        return vardecision;
    }

    public void setVardecision(String vardecision) {
        this.vardecision = vardecision;
    }

    public String getFinaldecision() {
        return finaldecision;
    }

    public void setFinaldecision(String finaldecision) {
        this.finaldecision = finaldecision;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}