package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;

import java.io.Serial;
import java.io.Serializable;

public class Player extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    private int age;
    private String teamName;
    private String playingPosition;
    private String playerType;
    private String contactNumber;
    private String fitnessStatus;
    private String matchEligibilityStatus;

    public Player(
            int id,
            String name,
            String password,
            String role,
            int age,
            String teamName,
            String playingPosition,
            String playerType,
            String contactNumber,
            String fitnessStatus,
            String matchEligibilityStatus) {

        super(id, name, password, role);

        this.age = age;
        this.teamName = teamName;
        this.playingPosition = playingPosition;
        this.playerType = playerType;
        this.contactNumber = contactNumber;
        this.fitnessStatus = fitnessStatus;
        this.matchEligibilityStatus = matchEligibilityStatus;
    }

    public int getPlayerId() {
        return getId();
    }

    public void setPlayerId(int playerId) {
        setId(playerId);
    }

    public String getFullName() {
        return getName();
    }

    public void setFullName(String fullName) {
        setName(fullName);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPlayingPosition() {
        return playingPosition;
    }

    public void setPlayingPosition(String playingPosition) {
        this.playingPosition = playingPosition;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFitnessStatus() {
        return fitnessStatus;
    }

    public void setFitnessStatus(String fitnessStatus) {
        this.fitnessStatus = fitnessStatus;
    }

    public String getMatchEligibilityStatus() {
        return matchEligibilityStatus;
    }

    public void setMatchEligibilityStatus(String matchEligibilityStatus) {
        this.matchEligibilityStatus = matchEligibilityStatus;
    }

    @Override
    public void updateUser() {
        System.out.println("Player information updated.");
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", role='" + getRole() + '\'' +
                ", age=" + age +
                ", teamName='" + teamName + '\'' +
                ", playingPosition='" + playingPosition + '\'' +
                ", playerType='" + playerType + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", fitnessStatus='" + fitnessStatus + '\'' +
                ", matchEligibilityStatus='" + matchEligibilityStatus + '\'' +
                '}';
    }
}