package com.pluralsight;

import java.time.*;

public class Deposit {
    private String today;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Deposit(String today, String time, String description, String vendor, double amount) {
        this.today = today;
        this.time = time;
        this.description = description;
        this.vendor =  vendor;
        this.amount = amount;
    }

    //getters
    public String getToday() {
        return this.today;
    }
    public String getTime() {
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

    public void setToday(String today) {
        this.today = today;
    }
    public void setTime(String currentTime) {
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
