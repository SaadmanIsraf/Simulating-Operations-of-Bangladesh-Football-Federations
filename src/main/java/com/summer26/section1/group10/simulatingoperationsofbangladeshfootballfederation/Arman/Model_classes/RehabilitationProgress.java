package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serializable;
import java.time.LocalDate;

public class RehabilitationProgress implements Serializable {

    private int progressId;
    private int playerId;
    private LocalDate progressDate;
    private double recoveryPercentage;
    private int physiotherapySessionsCompleted;
    private String physicalConditionNotes;
    private String updatedFitnessStatus;

    public RehabilitationProgress() {
    }

    public RehabilitationProgress(
            int progressId,
            int playerId,
            LocalDate progressDate,
            double recoveryPercentage,
            int physiotherapySessionsCompleted,
            String physicalConditionNotes,
            String updatedFitnessStatus) {

        this.progressId = progressId;
        this.playerId = playerId;
        this.progressDate = progressDate;
        this.recoveryPercentage = recoveryPercentage;
        this.physiotherapySessionsCompleted = physiotherapySessionsCompleted;
        this.physicalConditionNotes = physicalConditionNotes;
        this.updatedFitnessStatus = updatedFitnessStatus;
    }

    public int getProgressId() {
        return progressId;
    }

    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public LocalDate getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(LocalDate progressDate) {
        this.progressDate = progressDate;
    }

    public double getRecoveryPercentage() {
        return recoveryPercentage;
    }

    public void setRecoveryPercentage(double recoveryPercentage) {
        this.recoveryPercentage = recoveryPercentage;
    }

    public int getPhysiotherapySessionsCompleted() {
        return physiotherapySessionsCompleted;
    }

    public void setPhysiotherapySessionsCompleted(int physiotherapySessionsCompleted) {
        this.physiotherapySessionsCompleted = physiotherapySessionsCompleted;
    }

    public String getPhysicalConditionNotes() {
        return physicalConditionNotes;
    }

    public void setPhysicalConditionNotes(String physicalConditionNotes) {
        this.physicalConditionNotes = physicalConditionNotes;
    }

    public String getUpdatedFitnessStatus() {
        return updatedFitnessStatus;
    }

    public void setUpdatedFitnessStatus(String updatedFitnessStatus) {
        this.updatedFitnessStatus = updatedFitnessStatus;
    }

    @Override
    public String toString() {
        return "RehabilitationProgress{" +
                "progressId=" + progressId +
                ", playerId=" + playerId +
                ", progressDate=" + progressDate +
                ", recoveryPercentage=" + recoveryPercentage +
                ", physiotherapySessionsCompleted=" + physiotherapySessionsCompleted +
                ", physicalConditionNotes='" + physicalConditionNotes + '\'' +
                ", updatedFitnessStatus='" + updatedFitnessStatus + '\'' +
                '}';
    }
}