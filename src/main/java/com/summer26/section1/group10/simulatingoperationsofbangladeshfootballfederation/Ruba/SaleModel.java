package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import java.time.LocalDate;

public class SaleModel {

    private LocalDate date;
    private String match;
    private int ticketsSold;
    private double revenue;

    public SaleModel(LocalDate date, String match, int ticketsSold, double revenue) {
        this.date = date;
        this.match = match;
        this.ticketsSold = ticketsSold;
        this.revenue = revenue;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMatch() {
        return match;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public double getRevenue() {
        return revenue;
    }
}