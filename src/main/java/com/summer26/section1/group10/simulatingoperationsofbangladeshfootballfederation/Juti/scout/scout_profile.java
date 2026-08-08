package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.scout;

public class scout_profile {
    private String  password;
    private String scoutID;
    private String scoutName;

    public scout_profile(String password, String scoutID, String scoutName) {
        this.password = password;
        this.scoutID = scoutID;
        this.scoutName = scoutName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScoutID() {
        return scoutID;
    }

    public void setScoutID(String scoutID) {
        this.scoutID = scoutID;
    }

    public String getScoutName() {
        return scoutName;
    }

    public void setScoutName(String scoutName) {
        this.scoutName = scoutName;
    }

    @Override
    public String toString() {
        return "scout_profile{" +
                "password='" + password + '\'' +
                ", scoutID='" + scoutID + '\'' +
                ", scoutName='" + scoutName + '\'' +
                '}';
    }
}
