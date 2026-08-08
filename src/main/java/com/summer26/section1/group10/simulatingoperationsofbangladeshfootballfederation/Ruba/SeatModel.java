package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import java.io.Serial;
import java.io.Serializable;

public class SeatModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String match;
    private int totalSeats;
    private int availableSeats;

    public SeatModel(String match, int totalSeats, int availableSeats) {
        this.match = match;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public String getMatch() {
        return match;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}