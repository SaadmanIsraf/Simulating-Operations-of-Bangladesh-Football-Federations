package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

public class TeamModel {

    private String teamName;
    private String coach;
    private String country;
    private int rank;

    public TeamModel(String teamName, String coach, String country, int rank) {
        this.teamName = teamName;
        this.coach = coach;
        this.country = country;
        this.rank = rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCoach() {
        return coach;
    }

    public String getCountry() {
        return country;
    }

    public int getRank() {
        return rank;
    }
}