package com.example.calculate_vacation_days.models;

public class Holiday {
    private Integer month;
    private Integer day;
    private String name;

    public Holiday(Integer month, Integer day, String name) {
        this.month = month;
        this.day = day;
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}