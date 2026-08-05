package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

public class BookingModel {

    private String bookingId;
    private String match;
    private String customerName;
    private int quantity;
    private String status;

    public BookingModel(String bookingId, String match, String customerName, int quantity, String status) {
        this.bookingId = bookingId;
        this.match = match;
        this.customerName = customerName;
        this.quantity = quantity;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getMatch() {
        return match;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}