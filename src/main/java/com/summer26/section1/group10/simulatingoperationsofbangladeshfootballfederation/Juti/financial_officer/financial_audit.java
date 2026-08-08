package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import java.time.LocalDate;

public class financial_audit {

    private String auditId;
    private String recordType;
    private String recordId;
    private double amount;
    private LocalDate date;
    private String status;

    public financial_audit(String auditId, String recordType, String recordId, double amount, LocalDate date, String status) {
        this.auditId = auditId;
        this.recordType = recordType;
        this.recordId = recordId;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}