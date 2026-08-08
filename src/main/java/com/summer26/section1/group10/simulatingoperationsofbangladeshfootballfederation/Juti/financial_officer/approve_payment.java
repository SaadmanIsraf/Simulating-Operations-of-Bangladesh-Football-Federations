package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import java.time.LocalDate;

public class approve_payment {
    private String paymentId;
    private String paymentType;
    private String referenceId;
    private double amount;
    private LocalDate date;
    private String status;

    public approve_payment(String paymentId, String paymentType, String referenceId, double amount, LocalDate date, String status) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.referenceId = referenceId;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
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

    @Override
    public String toString() {
        return "approve_payment{" +
                "paymentId='" + paymentId + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
