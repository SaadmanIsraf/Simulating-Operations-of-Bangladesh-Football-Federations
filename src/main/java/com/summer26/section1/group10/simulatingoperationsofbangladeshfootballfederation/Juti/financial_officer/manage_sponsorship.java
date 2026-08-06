package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import java.time.LocalDate;

public class manage_sponsorship {
    private String sponsorId;
    private String sponsorName;
    private String sponsorshipType;
    private double amount;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

    public manage_sponsorship(String sponsorId, String sponsorName, String sponsorshipType, double amount, String status, LocalDate startDate, LocalDate endDate) {
        this.sponsorId = sponsorId;
        this.sponsorName = sponsorName;
        this.sponsorshipType = sponsorshipType;
        this.amount = amount;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSponsorshipType() {
        return sponsorshipType;
    }

    public void setSponsorshipType(String sponsorshipType) {
        this.sponsorshipType = sponsorshipType;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    @Override
    public String toString() {
        return "manage_sponsorship{" +
                "sponsorId='" + sponsorId + '\'' +
                ", sponsorName='" + sponsorName + '\'' +
                ", sponsorshipType='" + sponsorshipType + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}
