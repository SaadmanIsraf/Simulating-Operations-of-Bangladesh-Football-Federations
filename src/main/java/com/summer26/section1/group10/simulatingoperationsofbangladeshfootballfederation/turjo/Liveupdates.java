package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class Liveupdates implements Serializable {
    private String match;
    private String minute;
    private String homescore;
    private String awayscore;
    private String matchstatus;
    private String goalscorer;
    private String assist;
    private String cardtype;
    private String cardplayer;
    private String substitution;
    private String commentary;

    public Liveupdates(String match, String minute, String homescore, String awayscore, String matchstatus, String goalscorer, String assist, String cardtype, String cardplayer, String substitution, String commentary) {
        this.match = match;
        this.minute = minute;
        this.homescore = homescore;
        this.awayscore = awayscore;
        this.matchstatus = matchstatus;
        this.goalscorer = goalscorer;
        this.assist = assist;
        this.cardtype = cardtype;
        this.cardplayer = cardplayer;
        this.substitution = substitution;
        this.commentary = commentary;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHomescore() {
        return homescore;
    }

    public void setHomescore(String homescore) {
        this.homescore = homescore;
    }

    public String getAwayscore() {
        return awayscore;
    }

    public void setAwayscore(String awayscore) {
        this.awayscore = awayscore;
    }

    public String getMatchstatus() {
        return matchstatus;
    }

    public void setMatchstatus(String matchstatus) {
        this.matchstatus = matchstatus;
    }

    public String getGoalscorer() {
        return goalscorer;
    }

    public void setGoalscorer(String goalscorer) {
        this.goalscorer = goalscorer;
    }

    public String getAssist() {
        return assist;
    }

    public void setAssist(String assist) {
        this.assist = assist;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardplayer() {
        return cardplayer;
    }

    public void setCardplayer(String cardplayer) {
        this.cardplayer = cardplayer;
    }

    public String getSubstitution() {
        return substitution;
    }

    public void setSubstitution(String substitution) {
        this.substitution = substitution;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getScore() {
        return homescore + " - " + awayscore;
    }
}