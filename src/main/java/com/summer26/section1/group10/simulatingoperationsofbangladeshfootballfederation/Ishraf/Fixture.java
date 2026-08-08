package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;
import java.io.Serializable;

public class Fixture implements Serializable{
    private String fixtureId;
    private String homeTeam;
    private String awayTeam;
    private String competitionName;
    private String fixtureDate;
    private String matchRound;

    public Fixture(String fixtureId, String homeTeam, String awayTeam, String competitionName, String fixtureDate, String matchRound) {
        this.fixtureId = fixtureId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.competitionName = competitionName;
        this.fixtureDate = fixtureDate;
        this.matchRound = matchRound;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getFixtureDate() {
        return fixtureDate;
    }

    public String getMatchRound() {
        return matchRound;
    }

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setFixtureDate(String fixtureDate) {
        this.fixtureDate = fixtureDate;
    }

    public void setMatchRound(String matchRound) {
        this.matchRound = matchRound;
    }

    @Override
    public String toString() {
        return "Fixture{" +
                "fixtureId='" + fixtureId + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", competitionName='" + competitionName + '\'' +
                ", fixtureDate='" + fixtureDate + '\'' +
                ", matchRound='" + matchRound + '\'' +
                '}';
    }
}
