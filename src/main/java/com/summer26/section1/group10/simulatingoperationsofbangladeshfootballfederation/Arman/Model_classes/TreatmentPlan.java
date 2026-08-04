package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class TreatmentPlan implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int treatmentId;
    private int playerId;
    private int medicalOfficerId;
    private String prescribedMedicines;
    private int physiotherapySessionsRequired;
    private int restDurationDays;
    private String dietaryInstruction;
    private LocalDate followUpDate;
    private boolean active;

    public TreatmentPlan(
            int treatmentId,
            int playerId,
            int medicalOfficerId,
            String prescribedMedicines,
            int physiotherapySessionsRequired,
            int restDurationDays,
            String dietaryInstruction,
            LocalDate followUpDate,
            boolean active) {

        this.treatmentId = treatmentId;
        this.playerId = playerId;
        this.medicalOfficerId = medicalOfficerId;
        this.prescribedMedicines = prescribedMedicines;
        this.physiotherapySessionsRequired =
                physiotherapySessionsRequired;
        this.restDurationDays = restDurationDays;
        this.dietaryInstruction = dietaryInstruction;
        this.followUpDate = followUpDate;
        this.active = active;
    }

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

    public String getDietaryInstruction() {
        return dietaryInstruction;
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setMedicalOfficerId(int medicalOfficerId) {
        this.medicalOfficerId = medicalOfficerId;
    }

    public void setPrescribedMedicines(
            String prescribedMedicines) {

        this.prescribedMedicines = prescribedMedicines;
    }

    public void setPhysiotherapySessionsRequired(
            int physiotherapySessionsRequired) {

        this.physiotherapySessionsRequired =
                physiotherapySessionsRequired;
    }

    public void setRestDurationDays(int restDurationDays) {
        this.restDurationDays = restDurationDays;
    }

    public void setDietaryInstruction(
            String dietaryInstruction) {

        this.dietaryInstruction = dietaryInstruction;
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
                ", prescribedMedicines='" +
                prescribedMedicines + '\'' +
                ", physiotherapySessionsRequired=" +
                physiotherapySessionsRequired +
                ", restDurationDays=" +
                restDurationDays +
                ", dietaryInstruction='" +
                dietaryInstruction + '\'' +
                ", followUpDate=" +
                followUpDate +
                ", active=" +
                active +
                '}';
    }
}