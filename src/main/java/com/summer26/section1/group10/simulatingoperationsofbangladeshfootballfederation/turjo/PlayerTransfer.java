package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class PlayerTransfer implements Serializable {
    private String playerName;
    private String fromclubname;
    private String toclubname;
    private String transdecission;


    public PlayerTransfer(String playerName, String fromclubname, String toclubname, String transdecission) {
        this.playerName = playerName;
        this.fromclubname = fromclubname;
        this.toclubname = toclubname;
        this.transdecission = transdecission;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getFromclubname() {
        return fromclubname;
    }

    public void setFromclubname(String fromclubname) {
        this.fromclubname = fromclubname;
    }

    public String getToclubname() {
        return toclubname;
    }

    public void setToclubname(String toclubname) {
        this.toclubname = toclubname;
    }

    public String getTransdecission() {
        return transdecission;
    }

    public void setTransdecission(String transdecission) {
        this.transdecission = transdecission;
    }
}
