package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;
import java.io.Serializable;
import java.time.LocalDate;
public class NationalTeam {
    private String teamId;
    private String teamName;
    private String coachName;
    private String teamCategory;
    private LocalDate startDate;
    private LocalDate endDate;

    public NationalTeam(String teamId, String teamName, String coachName, String teamCategory, LocalDate startDate, LocalDate endDate) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.coachName = coachName;
        this.teamCategory = teamCategory;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCoachName() {
        return coachName;
    }

    public String getTeamCategory() {
        return teamCategory;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public void setTeamCategory(String teamCategory) {
        this.teamCategory = teamCategory;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "NationalTeam{" +
                "teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", coachName='" + coachName + '\'' +
                ", teamCategory='" + teamCategory + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
