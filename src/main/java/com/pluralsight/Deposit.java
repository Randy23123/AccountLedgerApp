package com.pluralsight;

import java.time.*;

public class Deposit {
    private LocalDate today = LocalDate.now();
    private LocalTime currentTime = LocalTime.now();
    private String name;
    private String vendor;
    private double amount;

    public Deposit(){
        LocalDate today;
        LocalTime currentTime;
        String name;
        String vendor;
        double amount;
    }

    //getters
    public LocalDate getToday() {
        return this.today;
    }
    public LocalTime getCurrentTime() {
        return this.currentTime;
    }
    public String getName() {
        return this.name;
    }
    public String getVendor() {
        return this.vendor;
    }
    public double getAmount() {
        return this.amount;
    }

    //setters

    public void setToday(LocalDate today) {
        this.today = today;
    }
    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
