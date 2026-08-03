package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

public class TeamRanking {
    private String rank;
    private String teamname;
    private String wins;
    private String draw;
    private String losses;
    private String points;

    public TeamRanking(String rank, String teamname, String wins, String draw, String losses, String points) {
        this.rank = rank;
        this.teamname = teamname;
        this.wins = wins;
        this.draw = draw;
        this.losses = losses;
        this.points = points;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}
