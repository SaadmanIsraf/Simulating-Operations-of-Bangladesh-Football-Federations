package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class player implements Serializable {
   ;
    private final String playerName;
    private int playerJersey;
    private String position;
    private String injureStatus;
    private String currentClub;
    private String currentValue;

    public player( String playerName, int playerJersey, String position, String injureStatus, String currentClub, String currentValue) {

        this.playerName = playerName;
        this.playerJersey = playerJersey;
        this.position = position;
        this.injureStatus = injureStatus;
        this.currentClub = currentClub;
        this.currentValue = currentValue;
    }


    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerJersey() {
        return playerJersey;
    }

    public void setPlayerJersey(int playerJersey) {
        this.playerJersey = playerJersey;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInjureStatus() {
        return injureStatus;
    }

    public void setInjureStatus(String injureStatus) {
        this.injureStatus = injureStatus;
    }

    public String getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }
}
