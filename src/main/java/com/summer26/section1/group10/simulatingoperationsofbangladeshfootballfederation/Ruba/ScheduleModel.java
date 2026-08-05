package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

public class ScheduleModel {

    private String match;
    private String date;
    private String venue;
    private int availableSeats;

    public ScheduleModel(String match, String date, String venue, int availableSeats) {
        this.match = match;
        this.date = date;
        this.venue = venue;
        this.availableSeats = availableSeats;
    }

    public String getMatch() {
        return match;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}