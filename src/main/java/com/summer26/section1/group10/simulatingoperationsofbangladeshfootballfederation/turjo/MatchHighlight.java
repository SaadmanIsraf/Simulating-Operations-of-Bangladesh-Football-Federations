package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class MatchHighlight implements Serializable {

    private String matchId;
    private String title;
    private String videoLink;
    private String duration;
    private String highlightType;
    private String publishStatus;
    private String description;

    // Default Constructor
    public MatchHighlight() {
    }

    // Parameterized Constructor
    public MatchHighlight(String matchId,
                          String title,
                          String videoLink,
                          String duration,
                          String highlightType,
                          String publishStatus,
                          String description) {

        this.matchId = matchId;
        this.title = title;
        this.videoLink = videoLink;
        this.duration = duration;
        this.highlightType = highlightType;
        this.publishStatus = publishStatus;
        this.description = description;
    }

    // Getters

    public String getMatchId() {
        return matchId;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String getDuration() {
        return duration;
    }

    public String getHighlightType() {
        return highlightType;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public String getDescription() {
        return description;
    }

    // Setters

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setHighlightType(String highlightType) {
        this.highlightType = highlightType;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MatchHighlight{" +
                "matchId='" + matchId + '\'' +
                ", title='" + title + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", duration='" + duration + '\'' +
                ", highlightType='" + highlightType + '\'' +
                ", publishStatus='" + publishStatus + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}