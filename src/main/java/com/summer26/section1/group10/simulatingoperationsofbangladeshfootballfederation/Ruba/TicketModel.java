package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import java.io.Serial;
import java.io.Serializable;

public class TicketModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String match;
    private String category;
    private double price;
    private int quantity;

    public TicketModel(String match, String category, double price, int quantity) {
        this.match = match;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getMatch() {
        return match;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}