package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;
import java.time.LocalDate;

public class Announcement implements Serializable {

    private String announcementId;
    private String title;
    private String competition;
    private String match;
    private String venue;
    private LocalDate matchDate;
    private String matchTime;
    private String sponsor;
    private String details;
    private String publishStatus;

    // Default Constructor
    public Announcement() {
    }

    // Parameterized Constructor
    public Announcement(String announcementId,
                        String title,
                        String competition,
                        String match,
                        String venue,
                        LocalDate matchDate,
                        String matchTime,
                        String sponsor,
                        String details,
                        String publishStatus) {

        this.announcementId = announcementId;
        this.title = title;
        this.competition = competition;
        this.match = match;
        this.venue = venue;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.sponsor = sponsor;
        this.details = details;
        this.publishStatus = publishStatus;
    }

    // Getters

    public String getAnnouncementId() {
        return announcementId;
    }

    public String getTitle() {
        return title;
    }

    public String getCompetition() {
        return competition;
    }

    public String getMatch() {
        return match;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getDetails() {
        return details;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    // Setters

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId='" + announcementId + '\'' +
                ", title='" + title + '\'' +
                ", competition='" + competition + '\'' +
                ", match='" + match + '\'' +
                ", venue='" + venue + '\'' +
                ", matchDate=" + matchDate +
                ", matchTime='" + matchTime + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", details='" + details + '\'' +
                ", publishStatus='" + publishStatus + '\'' +
                '}';
    }
}