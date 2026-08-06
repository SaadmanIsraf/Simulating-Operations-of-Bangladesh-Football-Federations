package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class recordfoulscards implements Serializable {

    private String matchName;
    private String playerName;
    private String foulType;
    private String cardType;
    private String minute;

    public recordfoulscards(String matchName, String playerName, String foulType, String cardType, String minute) {
        this.matchName = matchName;
        this.playerName = playerName;
        this.foulType = foulType;
        this.cardType = cardType;
        this.minute = minute;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getFoulType() {
        return foulType;
    }

    public void setFoulType(String foulType) {
        this.foulType = foulType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
}