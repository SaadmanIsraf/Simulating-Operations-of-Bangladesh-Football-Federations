package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;
import java.io.Serializable;

public class Sponsorship implements Serializable{
    private String sponsorId;
    private String sponsorName;
    private String sponsorshipAmount;
    private String sponsorshipType;
    private String contractStartDate;
    private String contractEndDate;

    public Sponsorship(String sponsorId, String sponsorName, String sponsorshipAmount, String sponsorshipType, String contractStartDate, String contractEndDate) {
        this.sponsorId = sponsorId;
        this.sponsorName = sponsorName;
        this.sponsorshipAmount = sponsorshipAmount;
        this.sponsorshipType = sponsorshipType;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public String getSponsorshipAmount() {
        return sponsorshipAmount;
    }

    public String getSponsorshipType() {
        return sponsorshipType;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public void setSponsorshipAmount(String sponsorshipAmount) {
        this.sponsorshipAmount = sponsorshipAmount;
    }

    public void setSponsorshipType(String sponsorshipType) {
        this.sponsorshipType = sponsorshipType;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    @Override
    public String toString() {
        return "Sponsorship{" +
                "sponsorId='" + sponsorId + '\'' +
                ", sponsorName='" + sponsorName + '\'' +
                ", sponsorshipAmount='" + sponsorshipAmount + '\'' +
                ", sponsorshipType='" + sponsorshipType + '\'' +
                ", contractStartDate='" + contractStartDate + '\'' +
                ", contractEndDate='" + contractEndDate + '\'' +
                '}';
    }
}
