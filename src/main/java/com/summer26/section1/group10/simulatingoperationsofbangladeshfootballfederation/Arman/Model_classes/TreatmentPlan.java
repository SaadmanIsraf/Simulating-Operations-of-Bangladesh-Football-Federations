package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serializable;
import java.time.LocalDate;

public class TreatmentPlan implements Serializable {

    private int treatmentId;
    private int playerId;
    private int medicalOfficerId;
    private String prescribedMedicines;
    private int physiotherapySessionsRequired;
    private int restDurationDays;
    private String dietaryInstructions;
    private LocalDate followUpDate;
    private boolean active;

    // Default Constructor
    public TreatmentPlan() {
    }

    // Parameterized Constructor
    public TreatmentPlan(int treatmentId,
                         int playerId,
                         int medicalOfficerId,
                         String prescribedMedicines,
                         int physiotherapySessionsRequired,
                         int restDurationDays,
                         String dietaryInstructions,
                         LocalDate followUpDate,
                         boolean active) {

        this.treatmentId = treatmentId;
        this.playerId = playerId;
        this.medicalOfficerId = medicalOfficerId;
        this.prescribedMedicines = prescribedMedicines;
        this.physiotherapySessionsRequired = physiotherapySessionsRequired;
        this.restDurationDays = restDurationDays;
        this.dietaryInstructions = dietaryInstructions;
        this.followUpDate = followUpDate;
        this.active = active;
    }

    // Getters

    public int getTreatmentId() {
        return treatmentId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getMedicalOfficerId() {
        return medicalOfficerId;
    }

    public String getPrescribedMedicines() {
        return prescribedMedicines;
    }

    public int getPhysiotherapySessionsRequired() {
        return physiotherapySessionsRequired;
    }

    public int getRestDurationDays() {
        return restDurationDays;
    }

    public String getDietaryInstructions() {
        return dietaryInstructions;
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public boolean isActive() {
        return active;
    }

    // Setters

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setMedicalOfficerId(int medicalOfficerId) {
        this.medicalOfficerId = medicalOfficerId;
    }

    public void setPrescribedMedicines(String prescribedMedicines) {
        this.prescribedMedicines = prescribedMedicines;
    }

    public void setPhysiotherapySessionsRequired(int physiotherapySessionsRequired) {
        this.physiotherapySessionsRequired = physiotherapySessionsRequired;
    }

    public void setRestDurationDays(int restDurationDays) {
        this.restDurationDays = restDurationDays;
    }

    public void setDietaryInstructions(String dietaryInstructions) {
        this.dietaryInstructions = dietaryInstructions;
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TreatmentPlan{" +
                "treatmentId=" + treatmentId +
                ", playerId=" + playerId +
                ", medicalOfficerId=" + medicalOfficerId +
                ", prescribedMedicines='" + prescribedMedicines + '\'' +
                ", physiotherapySessionsRequired=" + physiotherapySessionsRequired +
                ", restDurationDays=" + restDurationDays +
                ", dietaryInstructions='" + dietaryInstructions + '\'' +
                ", followUpDate=" + followUpDate +
                ", active=" + active +
                '}';
    }
}