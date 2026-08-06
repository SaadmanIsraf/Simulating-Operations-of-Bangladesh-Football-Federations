package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serializable;
import java.time.LocalDate;

public class InjuryReport implements Serializable {

    private int reportId;
    private int playerId;
    private String injuryType;
    private LocalDate injuryDate;
    private String affectedBodyPart;
    private String severity;
    private String fitnessStatus;
    private String additionalNotes;
    private boolean active;

    public InjuryReport() {
    }

    public InjuryReport(
            int reportId,
            int playerId,
            String injuryType,
            LocalDate injuryDate,
            String affectedBodyPart,
            String severity,
            String fitnessStatus,
            String additionalNotes,
            boolean active) {

        this.reportId = reportId;
        this.playerId = playerId;
        this.injuryType = injuryType;
        this.injuryDate = injuryDate;
        this.affectedBodyPart = affectedBodyPart;
        this.severity = severity;
        this.fitnessStatus = fitnessStatus;
        this.additionalNotes = additionalNotes;
        this.active = active;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getInjuryType() {
        return injuryType;
    }

    public void setInjuryType(String injuryType) {
        this.injuryType = injuryType;
    }

    public LocalDate getInjuryDate() {
        return injuryDate;
    }

    public void setInjuryDate(LocalDate injuryDate) {
        this.injuryDate = injuryDate;
    }

    public String getAffectedBodyPart() {
        return affectedBodyPart;
    }

    public void setAffectedBodyPart(String affectedBodyPart) {
        this.affectedBodyPart = affectedBodyPart;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getFitnessStatus() {
        return fitnessStatus;
    }

    public void setFitnessStatus(String fitnessStatus) {
        this.fitnessStatus = fitnessStatus;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "InjuryReport{" +
                "reportId=" + reportId +
                ", playerId=" + playerId +
                ", injuryType='" + injuryType + '\'' +
                ", injuryDate=" + injuryDate +
                ", affectedBodyPart='" + affectedBodyPart + '\'' +
                ", severity='" + severity + '\'' +
                ", fitnessStatus='" + fitnessStatus + '\'' +
                ", additionalNotes='" + additionalNotes + '\'' +
                ", active=" + active +
                '}';
    }
}