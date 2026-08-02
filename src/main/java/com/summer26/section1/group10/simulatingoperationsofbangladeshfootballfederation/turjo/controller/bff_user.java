package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import java.time.LocalDate;

public class bff_user {
  String match ,time, vanue ,role;
  LocalDate date;

    public bff_user(String match, String time, String vanue, String role, LocalDate date) {
        this.match = match;
        this.time = time;
        this.vanue = vanue;
        this.role = role;
        this.date = date;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVanue() {
        return vanue;
    }

    public void setVanue(String vanue) {
        this.vanue = vanue;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "bff_user{" +
                "match='" + match + '\'' +
                ", time='" + time + '\'' +
                ", vanue='" + vanue + '\'' +
                ", role='" + role + '\'' +
                ", date=" + date +
                '}';
    }
}
