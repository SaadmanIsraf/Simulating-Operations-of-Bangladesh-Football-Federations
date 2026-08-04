package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.model;

import java.io.Serializable;

public class MatchReport implements Serializable {

    private int id;
    private String matchBetween;
    private String score;
    private String goalScorers;
    private int cards;
    private String status;
    private String summary;

    public MatchReport() {
    }

    public MatchReport(String matchBetween, String score, String goalScorers,
                       int cards, String status, String summary) {
        this.matchBetween = matchBetween;
        this.score = score;
        this.goalScorers = goalScorers;
        this.cards = cards;
        this.status = status;
        this.summary = summary;
    }

    public MatchReport(int id, String matchBetween, String score, String goalScorers,
                       int cards, String status, String summary) {
        this.id = id;
        this.matchBetween = matchBetween;
        this.score = score;
        this.goalScorers = goalScorers;
        this.cards = cards;
        this.status = status;
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatchBetween() {
        return matchBetween;
    }

    public void setMatchBetween(String matchBetween) {
        this.matchBetween = matchBetween;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGoalScorers() {
        return goalScorers;
    }

    public void setGoalScorers(String goalScorers) {
        this.goalScorers = goalScorers;
    }

    public int getCards() {
        return cards;
    }

    public void setCards(int cards) {
        this.cards = cards;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "MatchReport{" +
                "id=" + id +
                ", matchBetween='" + matchBetween + '\'' +
                ", score='" + score + '\'' +
                ", goalScorers='" + goalScorers + '\'' +
                ", cards=" + cards +
                ", status='" + status + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}