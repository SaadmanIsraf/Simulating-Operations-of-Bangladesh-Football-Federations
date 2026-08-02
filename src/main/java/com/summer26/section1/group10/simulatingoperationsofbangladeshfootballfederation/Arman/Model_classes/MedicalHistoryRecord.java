package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serializable;
import java.time.LocalDate;

public class MedicalHistoryRecord implements Serializable {

    private int playerId;
    private String playerName;
    private String injuryReport;
    private LocalDate date;
    private String treatmentPlan;
    private String matchDayIncident;

    public MedicalHistoryRecord() {
    }

    public MedicalHistoryRecord(int playerId,
                                String playerName,
                                String injuryReport,
                                LocalDate date,
                                String treatmentPlan,
                                String matchDayIncident) {

        this.playerId = playerId;
        this.playerName = playerName;
        this.injuryReport = injuryReport;
        this.date = date;
        this.treatmentPlan = treatmentPlan;
        this.matchDayIncident = matchDayIncident;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getInjuryReport() {
        return injuryReport;
    }

    public void setInjuryReport(String injuryReport) {
        this.injuryReport = injuryReport;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getMatchDayIncident() {
        return matchDayIncident;
    }

    public void setMatchDayIncident(String matchDayIncident) {
        this.matchDayIncident = matchDayIncident;
    }

    @Override
    public String toString() {
        return "MedicalHistoryRecord{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", injuryReport='" + injuryReport + '\'' +
                ", date=" + date +
                ", treatmentPlan='" + treatmentPlan + '\'' +
                ", matchDayIncident='" + matchDayIncident + '\'' +
                '}';
    }
}