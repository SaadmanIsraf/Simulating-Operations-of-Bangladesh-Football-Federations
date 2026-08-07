package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import java.time.LocalDate;

public class financial_audit {
    private String auditId;
    private String recordType;
    private String recordId;
    private LocalDate auditYear,auditMonth;
    private String status,amount;

    public financial_audit(String auditId, String recordType, String recordId, LocalDate auditYear, LocalDate auditMonth, String status, String amount) {
        this.auditId = auditId;
        this.recordType = recordType;
        this.recordId = recordId;
        this.auditYear = auditYear;
        this.auditMonth = auditMonth;
        this.status = status;
        this.amount = amount;
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

    public LocalDate getAuditYear() {
        return auditYear;
    }

    public void setAuditYear(LocalDate auditYear) {
        this.auditYear = auditYear;
    }

    public LocalDate getAuditMonth() {
        return auditMonth;
    }

    public void setAuditMonth(LocalDate auditMonth) {
        this.auditMonth = auditMonth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "financial_audit{" +
                "auditId='" + auditId + '\'' +
                ", recordType='" + recordType + '\'' +
                ", recordId='" + recordId + '\'' +
                ", auditYear=" + auditYear +
                ", auditMonth=" + auditMonth +
                ", status='" + status + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
