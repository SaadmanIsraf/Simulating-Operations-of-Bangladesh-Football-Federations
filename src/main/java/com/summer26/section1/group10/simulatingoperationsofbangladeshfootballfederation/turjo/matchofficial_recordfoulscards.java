package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class matchofficial_recordfoulscards implements Serializable {

    private String match;
    private String playerName;
    private String foulType;
    private String cardType;
    private int minute;

    public matchofficial_recordfoulscards() {
    }

    public matchofficial_recordfoulscards(String match,
                                          String playerName,
                                          String foulType,
                                          String cardType,
                                          int minute) {

        this.match = match;
        this.playerName = playerName;
        this.foulType = foulType;
        this.cardType = cardType;
        this.minute = minute;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
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

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "matchofficial_recordfoulscards{" +
                "match='" + match + '\'' +
                ", playerName='" + playerName + '\'' +
                ", foulType='" + foulType + '\'' +
                ", cardType='" + cardType + '\'' +
                ", minute=" + minute +
                '}';
    }
}