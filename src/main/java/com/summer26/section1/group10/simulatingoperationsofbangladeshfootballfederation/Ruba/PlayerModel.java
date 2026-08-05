package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

public class PlayerModel {

    private String playerName;
    private String team;
    private int goals;
    private int appearances;

    public PlayerModel(String playerName,String team,int goals,int appearances){
        this.playerName=playerName;
        this.team=team;
        this.goals=goals;
        this.appearances=appearances;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getTeam(){
        return team;
    }

    public int getGoals(){
        return goals;
    }

    public int getAppearances(){
        return appearances;
    }
}