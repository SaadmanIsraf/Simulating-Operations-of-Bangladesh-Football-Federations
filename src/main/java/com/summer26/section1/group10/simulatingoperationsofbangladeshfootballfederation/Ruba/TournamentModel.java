package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

public class TournamentModel {

    private final String tournamentName;
    private final String date;
    private final String venue;
    private final String match;

    public TournamentModel(String tournamentName, String date, String venue, String match) {
        this.tournamentName = tournamentName;
        this.date = date;
        this.venue = venue;
        this.match = match;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getMatch() {
        return match;
    }
}