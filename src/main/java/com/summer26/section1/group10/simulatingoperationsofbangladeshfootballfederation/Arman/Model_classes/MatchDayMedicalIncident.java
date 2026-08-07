package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serializable;
import java.time.LocalDate;

public class MatchDayMedicalIncident implements Serializable {

    private int incidentId;
    private int playerId;
    private LocalDate matchDate;
    private int matchMinute;
    private String incidentType;
    private String severity;
    private String actionTaken;

    public MatchDayMedicalIncident() {
    }

    public MatchDayMedicalIncident(
            int incidentId,
            int playerId,
            LocalDate matchDate,
            String incidentType,
            String severity,
            String actionTaken) {

        this.incidentId = incidentId;
        this.playerId = playerId;
        this.matchDate = matchDate;
        this.incidentType = incidentType;
        this.severity = severity;
        this.actionTaken = actionTaken;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public int getMatchMinute() {
        return matchMinute;
    }

    public void setMatchMinute(int matchMinute) {
        this.matchMinute = matchMinute;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Override
    public String toString() {
        return "MatchDayMedicalIncident{" +
                "incidentId=" + incidentId +
                ", playerId=" + playerId +
                ", matchDate=" + matchDate +
                ", incidentType='" + incidentType + '\'' +
                ", severity='" + severity + '\'' +
                ", actionTaken='" + actionTaken + '\'' +
                '}';
    }
}