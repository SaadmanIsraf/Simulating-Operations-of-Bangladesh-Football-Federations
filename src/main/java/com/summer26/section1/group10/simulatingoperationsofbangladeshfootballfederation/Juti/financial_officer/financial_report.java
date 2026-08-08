package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

public class financial_report {

    private String reportID;
    private String reportType;
    private double amount;
    private String date;
    private String status;

    public financial_report(String reportID, String reportType, double amount, String date, String status) {
        this.reportID = reportID;
        this.reportType = reportType;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public String getReportID() {
        return reportID;
    }

    public String getReportType() {
        return reportType;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}
