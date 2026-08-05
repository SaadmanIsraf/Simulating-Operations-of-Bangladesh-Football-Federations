package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

public class MatchModel {

    private String match;
    private String date;
    private String time;
    private String venue;
    private String score;
    private String tournament;

    public MatchModel(String match, String date, String time, String venue, String score, String tournament) {
        this.match = match;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.score = score;
        this.tournament = tournament;
    }

    public String getMatch() {
        return match;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public String getScore() {
        return score;
    }

    public String getTournament() {
        return tournament;
    }

    @Override
    public String toString() {
        return "MatchModel{" +
                "match='" + match + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", venue='" + venue + '\'' +
                ", score='" + score + '\'' +
                ", tournament='" + tournament + '\'' +
                '}';
    }
}