package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class ReplacementRef implements Serializable {
    private String matchid;
    private String currentreferee;
    private String reason;
    private String priority;
    private String details;

    public ReplacementRef(String matchid, String currentreferee, String reason, String priority, String details) {
        this.matchid = matchid;
        this.currentreferee = currentreferee;
        this.reason = reason;
        this.priority = priority;
        this.details = details;
    }

    public String getMatchid() {
        return matchid;
    }

    public void setMatchid(String matchid) {
        this.matchid = matchid;
    }

    public String getCurrentreferee() {
        return currentreferee;
    }

    public void setCurrentreferee(String currentreferee) {
        this.currentreferee = currentreferee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
