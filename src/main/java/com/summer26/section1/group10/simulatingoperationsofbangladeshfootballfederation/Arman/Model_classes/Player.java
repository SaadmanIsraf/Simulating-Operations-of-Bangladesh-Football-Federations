package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;

import java.io.Serializable;

public class Player extends User implements Serializable {

    private int age;
    private String teamName;
    private String playingPosition;
    private String contactNumber;
    private String fitnessStatus;
    private String matchEligibilityStatus;

    public Player(int id,
                  String name,
                  String email,
                  String password,
                  String role,
                  int age,
                  String teamName,
                  String playingPosition,
                  String contactNumber,
                  String fitnessStatus,
                  String matchEligibilityStatus) {

        super(id, name, email, password, role);

        this.age = age;
        this.teamName = teamName;
        this.playingPosition = playingPosition;
        this.contactNumber = contactNumber;
        this.fitnessStatus = fitnessStatus;
        this.matchEligibilityStatus = matchEligibilityStatus;
    }

    public int getPlayerId() {
        return getId();
    }

    public String getFullName() {
        return getName();
    }

    public int getAge() {
        return age;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPlayingPosition() {
        return playingPosition;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getFitnessStatus() {
        return fitnessStatus;
    }

    public String getMatchEligibilityStatus() {
        return matchEligibilityStatus;
    }

    public void setPlayerId(int playerId) {
        setId(playerId);
    }

    public void setFullName(String fullName) {
        setName(fullName);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setPlayingPosition(String playingPosition) {
        this.playingPosition = playingPosition;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setFitnessStatus(String fitnessStatus) {
        this.fitnessStatus = fitnessStatus;
    }

    public void setMatchEligibilityStatus(String matchEligibilityStatus) {
        this.matchEligibilityStatus = matchEligibilityStatus;
    }


    public void changePassword(String newPassword) {
        setPassword(newPassword);
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
                ", email='" + getEmail() + '\'' +
                ", role='" + getRole() + '\'' +
                ", age=" + age +
                ", teamName='" + teamName + '\'' +
                ", playingPosition='" + playingPosition + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", fitnessStatus='" + fitnessStatus + '\'' +
                ", matchEligibilityStatus='" + matchEligibilityStatus + '\'' +
                '}';
    }
}