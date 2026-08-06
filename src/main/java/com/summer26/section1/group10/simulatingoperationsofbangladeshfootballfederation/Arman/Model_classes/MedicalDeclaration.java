package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serializable;
import java.time.LocalDate;

public class MedicalDeclaration implements Serializable {

    private int declarationId;
    private int playerId;
    private int medicalOfficerId;
    private LocalDate declarationDate;
    private String declarationStatus;
    private String remarks;

    public MedicalDeclaration() {
    }

    public MedicalDeclaration(
            int declarationId,
            int playerId,
            int medicalOfficerId,
            LocalDate declarationDate,
            String declarationStatus,
            String remarks) {

        this.declarationId = declarationId;
        this.playerId = playerId;
        this.medicalOfficerId = medicalOfficerId;
        this.declarationDate = declarationDate;
        this.declarationStatus = declarationStatus;
        this.remarks = remarks;
    }

    public int getDeclarationId() {
        return declarationId;
    }

    public void setDeclarationId(int declarationId) {
        this.declarationId = declarationId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMedicalOfficerId() {
        return medicalOfficerId;
    }

    public void setMedicalOfficerId(int medicalOfficerId) {
        this.medicalOfficerId = medicalOfficerId;
    }

    public LocalDate getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(LocalDate declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getDeclarationStatus() {
        return declarationStatus;
    }

    public void setDeclarationStatus(String declarationStatus) {
        this.declarationStatus = declarationStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "MedicalDeclaration{" +
                "declarationId=" + declarationId +
                ", playerId=" + playerId +
                ", medicalOfficerId=" + medicalOfficerId +
                ", declarationDate=" + declarationDate +
                ", declarationStatus='" + declarationStatus + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}