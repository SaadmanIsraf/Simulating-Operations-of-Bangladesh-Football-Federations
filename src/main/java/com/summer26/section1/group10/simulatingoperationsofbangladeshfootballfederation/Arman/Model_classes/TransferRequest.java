package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class TransferRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int requestId;
    private int playerId;
    private String playerName;
    private String currentTeam;
    private String requestedTeam;
    private String requestMessage;
    private LocalDate requestDate;
    private String status;

    public TransferRequest() {
    }

    public TransferRequest(
            int requestId,
            int playerId,
            String playerName,
            String currentTeam,
            String requestedTeam,
            String requestMessage,
            LocalDate requestDate,
            String status) {

        this.requestId = requestId;
        this.playerId = playerId;
        this.playerName = playerName;
        this.currentTeam = currentTeam;
        this.requestedTeam = requestedTeam;
        this.requestMessage = requestMessage;
        this.requestDate = requestDate;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(String currentTeam) {
        this.currentTeam = currentTeam;
    }

    public String getRequestedTeam() {
        return requestedTeam;
    }

    public void setRequestedTeam(String requestedTeam) {
        this.requestedTeam = requestedTeam;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "requestId=" + requestId +
                ", playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", currentTeam='" + currentTeam + '\'' +
                ", requestedTeam='" + requestedTeam + '\'' +
                ", requestMessage='" + requestMessage + '\'' +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                '}';
    }
}