package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class RecordFouls implements Serializable {
    private String matchbetween;
    private String Playername;
    private String minute;
    private String eventtype;
    private String cardType;
    private String foulType;
    private String notes;

    public RecordFouls(String matchbetween, String playername, String minute, String eventtype, String cardType, String foulType, String notes) {
        this.matchbetween = matchbetween;
        Playername = playername;
        this.minute = minute;
        this.eventtype = eventtype;
        this.cardType = cardType;
        this.foulType = foulType;
        this.notes = notes;
    }

    public String getMatchbetween() {
        return matchbetween;
    }

    public void setMatchbetween(String matchbetween) {
        this.matchbetween = matchbetween;
    }

    public String getPlayername() {
        return Playername;
    }

    public void setPlayername(String playername) {
        Playername = playername;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getFoulType() {
        return foulType;
    }

    public void setFoulType(String foulType) {
        this.foulType = foulType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
