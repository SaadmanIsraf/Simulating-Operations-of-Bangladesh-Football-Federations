package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import java.time.LocalDate;

public class record_expense {
    private String recordID;
    private String expenseID;
    private String source;
    private String type;
    private double amount;
    private LocalDate date;
    private String description;

    public record_expense(String recordID, String expenseID, String source, String type, double amount, LocalDate date, String description) {
        this.recordID = recordID;
        this.expenseID = expenseID;
        this.source = source;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(String expenseID) {
        this.expenseID = expenseID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "record_expense{" +
                "recordID='" + recordID + '\'' +
                ", expenseID='" + expenseID + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
