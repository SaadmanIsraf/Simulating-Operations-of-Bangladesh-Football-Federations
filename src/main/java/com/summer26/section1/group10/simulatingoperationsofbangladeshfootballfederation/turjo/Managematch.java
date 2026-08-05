package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;
import java.time.LocalDate;

public class Managematch implements Serializable {
    private String matchId;
    private String hometeam;
    private String awayteam;
    private String competition;
    private String stadium;
    private LocalDate matchdate;
    private String matchtime;
    private String officialId;
    private String status;

    public Managematch(String matchId, String hometeam, String awayteam, String competition,
                       String stadium, LocalDate matchdate, String matchtime,
                       String officialId, String status) {
        this.matchId = matchId;
        this.hometeam = hometeam;
        this.awayteam = awayteam;
        this.competition = competition;
        this.stadium = stadium;
        this.matchdate = matchdate;
        this.matchtime = matchtime;
        this.officialId = officialId;
        this.status = status;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getHometeam() {
        return hometeam;
    }

    public void setHometeam(String hometeam) {
        this.hometeam = hometeam;
    }

    public String getAwayteam() {
        return awayteam;
    }

    public void setAwayteam(String awayteam) {
        this.awayteam = awayteam;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
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

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}