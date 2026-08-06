package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import java.time.LocalDate;

public class record_income {
    private String recordID;
    private String incomeID;
    private String record_income_source;
    private String record_income_source_type;
    private double record_income_amount;
    private LocalDate record_income_date;
    private String record_description;

    public record_income(String recordID, String incomeID, String record_income_source, String record_income_source_type, double record_income_amount, LocalDate record_income_date, String record_description) {
        this.recordID = recordID;
        this.incomeID = incomeID;
        this.record_income_source = record_income_source;
        this.record_income_source_type = record_income_source_type;
        this.record_income_amount = record_income_amount;
        this.record_income_date = record_income_date;
        this.record_description = record_description;
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(String incomeID) {
        this.incomeID = incomeID;
    }

    public String getRecord_income_source() {
        return record_income_source;
    }

    public void setRecord_income_source(String record_income_source) {
        this.record_income_source = record_income_source;
    }

    public String getRecord_income_source_type() {
        return record_income_source_type;
    }

    public void setRecord_income_source_type(String record_income_source_type) {
        this.record_income_source_type = record_income_source_type;
    }

    public double getRecord_income_amount() {
        return record_income_amount;
    }

    public void setRecord_income_amount(double record_income_amount) {
        this.record_income_amount = record_income_amount;
    }

    public LocalDate getRecord_income_date() {
        return record_income_date;
    }

    public void setRecord_income_date(LocalDate record_income_date) {
        this.record_income_date = record_income_date;
    }

    public String getRecord_description() {
        return record_description;
    }

    public void setRecord_description(String record_description) {
        this.record_description = record_description;
    }

    @Override
    public String toString() {
        return "record_income{" +
                "recordID='" + recordID + '\'' +
                ", incomeID='" + incomeID + '\'' +
                ", record_income_source='" + record_income_source + '\'' +
                ", record_income_source_type='" + record_income_source_type + '\'' +
                ", record_income_amount=" + record_income_amount +
                ", record_income_date=" + record_income_date +
                ", record_description='" + record_description + '\'' +
                '}';
    }
}