package com.example.nearestbarsapp.models;

import android.location.Location;

import androidx.annotation.NonNull;

public class BarItem implements Comparable<BarItem> {

    private String name;
    private double distance;
    private Location location;

    public BarItem(String barName, Location location, double barDistance) {
        name = barName;
        this.location = location;
        distance = barDistance;
    }
    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public int compareTo(@NonNull BarItem barItem) {
        return barItem.distance > this.distance ? -1 : 1;
    }
}
