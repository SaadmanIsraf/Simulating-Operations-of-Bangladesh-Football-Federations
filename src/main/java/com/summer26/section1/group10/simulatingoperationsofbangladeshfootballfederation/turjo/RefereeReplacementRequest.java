package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class RefereeReplacementRequest implements Serializable {

    private String matchId;
    private String currentReferee;
    private String replacementReason;
    private String priority;
    private String requestStatus;

    // Default Constructor
    public RefereeReplacementRequest() {
    }

    // Parameterized Constructor
    public RefereeReplacementRequest(String matchId,
                                     String currentReferee,
                                     String replacementReason,
                                     String priority,
                                     String requestStatus) {

        this.matchId = matchId;
        this.currentReferee = currentReferee;
        this.replacementReason = replacementReason;
        this.priority = priority;
        this.requestStatus = requestStatus;
    }

    // Getters

    public String getMatchId() {
        return matchId;
    }

    public String getCurrentReferee() {
        return currentReferee;
    }

    public String getReplacementReason() {
        return replacementReason;
    }

    public String getPriority() {
        return priority;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    // Setters

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setCurrentReferee(String currentReferee) {
        this.currentReferee = currentReferee;
    }

    public void setReplacementReason(String replacementReason) {
        this.replacementReason = replacementReason;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "RefereeReplacementRequest{" +
                "matchId='" + matchId + '\'' +
                ", currentReferee='" + currentReferee + '\'' +
                ", replacementReason='" + replacementReason + '\'' +
                ", priority='" + priority + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                '}';
    }
}
