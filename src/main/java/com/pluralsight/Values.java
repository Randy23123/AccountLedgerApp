package com.pluralsight;

import java.time.*;

public class Values {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Values(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor =  vendor;
        this.amount = amount;
    }

    //getters
    public LocalDate getDate() {
        return this.date;
    }
    public LocalTime getTime() {
        return this.time;
    }
    public String getDescription() {
        return this.description;
    }
    public String getVendor() {
        return this.vendor;
    }
    public double getAmount() {
        return this.amount;
    }

    //setters

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setTime(LocalTime currentTime) {
        this.time = currentTime;
    }
    public void setName(String name) {
        this.description = description;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

}
