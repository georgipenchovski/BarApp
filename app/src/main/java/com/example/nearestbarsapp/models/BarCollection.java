package com.example.nearestbarsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BarCollection {
    @SerializedName("results")
    @Expose
    private List<BarModel> results = new ArrayList<BarModel>();

    public List<BarModel> getResults() {
        return results;
    }

    public void setResults(List<BarModel> results) {
        this.results = results;
    }
}
