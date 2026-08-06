package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class UserAccount implements Serializable {

    private String username;
    private String role;
    private String status;

    // Default Constructor
    public UserAccount() {
    }

    // Parameterized Constructor
    public UserAccount(String username,
                       String role,
                       String status) {

        this.username = username;
        this.role = role;
        this.status = status;
    }

    // Getters

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    // Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
