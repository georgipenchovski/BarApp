package com.example.nearestbarsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Example {
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    public List<Result> getResults() {
        return results;
    }
    public void setResults(List<Result> results) {
        this.results = results;
    }


}
