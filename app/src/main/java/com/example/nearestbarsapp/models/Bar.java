package com.example.nearestbarsapp.models;

public class Bar {

    private String BarName;

    public Bar(String barName) {
        BarName = barName;
    }

    public String getBarName() {
        return BarName;
    }

    public void setBarName(String barName) {
        this.BarName = barName;
    }
}
