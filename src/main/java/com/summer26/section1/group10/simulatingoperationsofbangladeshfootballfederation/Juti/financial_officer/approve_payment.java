
package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

public class approve_payment {

    private String requestID;
    private String payeeName;
    private String remarks;

    public approve_payment(String requestID, String payeeName, String remarks) {
        this.requestID = requestID;
        this.payeeName = payeeName;
        this.remarks = remarks;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "approve_payment{" +
                "requestID='" + requestID + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}


