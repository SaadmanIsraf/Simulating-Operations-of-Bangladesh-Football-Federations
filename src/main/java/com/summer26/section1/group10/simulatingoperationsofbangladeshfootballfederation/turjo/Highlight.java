package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class Highlight implements Serializable {
    private String highlighttitle;
    private String match;
    private String competition;
    private String highlighttype;
    private String duration;
    private String videolink;
    private String publishstatus;
    private String description;
    private String keymoments;

    public Highlight(String highlighttitle, String match, String competition, String highlighttype, String duration, String videolink, String publishstatus, String description, String keymoments) {
        this.highlighttitle = highlighttitle;
        this.match = match;
        this.competition = competition;
        this.highlighttype = highlighttype;
        this.duration = duration;
        this.videolink = videolink;
        this.publishstatus = publishstatus;
        this.description = description;
        this.keymoments = keymoments;
    }

    public String getHighlighttitle() {
        return highlighttitle;
    }

    public void setHighlighttitle(String highlighttitle) {
        this.highlighttitle = highlighttitle;
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

    public String getHighlighttype() {
        return highlighttype;
    }

    public void setHighlighttype(String highlighttype) {
        this.highlighttype = highlighttype;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    public String getPublishstatus() {
        return publishstatus;
    }

    public void setPublishstatus(String publishstatus) {
        this.publishstatus = publishstatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeymoments() {
        return keymoments;
    }

    public void setKeymoments(String keymoments) {
        this.keymoments = keymoments;
    }
}