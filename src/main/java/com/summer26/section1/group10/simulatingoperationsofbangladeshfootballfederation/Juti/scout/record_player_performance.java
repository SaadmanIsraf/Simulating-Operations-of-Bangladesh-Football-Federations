package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.scout;

public class record_player_performance {
    private String name;
    private int playerID;
    private int matchID;
    private float rating;
    private int assists;
    private int goals;
    private String performanceNote;

    public record_player_performance(String name, int playerID, int matchID, float rating, int assists, int goals, String performanceNote) {
        this.name = name;
        this.playerID = playerID;
        this.matchID = matchID;
        this.rating = rating;
        this.assists = assists;
        this.goals = goals;
        this.performanceNote = performanceNote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public String getPerformanceNote() {
        return performanceNote;
    }

    public void setPerformanceNote(String performanceNote) {
        this.performanceNote = performanceNote;
    }

    @Override
    public String toString() {
        return "record_player_performance{" +
                "name='" + name + '\'' +
                ", playerID=" + playerID +
                ", matchID=" + matchID +
                ", rating=" + rating +
                ", assists=" + assists +
                ", goals=" + goals +
                ", performanceNote='" + performanceNote + '\'' +
                '}';
    }

}
