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

    // Default Constructor
    public RehabilitationProgress() {
    }

    // Parameterized Constructor
    public RehabilitationProgress(int progressId,
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

    // Getters

    public int getProgressId() {
        return progressId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public LocalDate getProgressDate() {
        return progressDate;
    }

    public double getRecoveryPercentage() {
        return recoveryPercentage;
    }

    public int getPhysiotherapySessionsCompleted() {
        return physiotherapySessionsCompleted;
    }

    public String getPhysicalConditionNotes() {
        return physicalConditionNotes;
    }

    public String getUpdatedFitnessStatus() {
        return updatedFitnessStatus;
    }

    // Setters

    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setProgressDate(LocalDate progressDate) {
        this.progressDate = progressDate;
    }

    public void setRecoveryPercentage(double recoveryPercentage) {
        this.recoveryPercentage = recoveryPercentage;
    }

    public void setPhysiotherapySessionsCompleted(int physiotherapySessionsCompleted) {
        this.physiotherapySessionsCompleted = physiotherapySessionsCompleted;
    }

    public void setPhysicalConditionNotes(String physicalConditionNotes) {
        this.physicalConditionNotes = physicalConditionNotes;
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