package com.example.nearestbarsapp.POJO;

public class Bar {

    private String BarName;

    private String BarDistance;

    public Bar(String barName, String barDistance) {
        BarName = barName;
        BarDistance = barDistance;
    }
    public String getBarName() {
        return BarName;
    }

    public void setBarName(String barName) {
        this.BarName = barName;
    }

    public String getBarDistance() {
        return BarDistance;
    }

    public void setBarDistance(String barDistance) {
        BarDistance = barDistance;
    }
}
