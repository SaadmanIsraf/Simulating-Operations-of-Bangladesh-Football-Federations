package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;

import java.io.Serializable;

public class Player implements Serializable {

    private int age;
    private String teamName;
    private String playingPosition;
    private String contactNumber;
    private String fitnessStatus;
    private String matchEligibilityStatus;

    public Player(int age, String teamName, String playingPosition, String contactNumber, String fitnessStatus, String matchEligibilityStatus) {
        this.age = age;
        this.teamName = teamName;
        this.playingPosition = playingPosition;
        this.contactNumber = contactNumber;
        this.fitnessStatus = fitnessStatus;
        this.matchEligibilityStatus = matchEligibilityStatus;
    }
}