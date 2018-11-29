package com.banjara.dixitjain.filmistan.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class MovieVideo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<MovieVideoResult> results = null;

    public Integer getId() {
        return id;
    }

    public List<MovieVideoResult> getResults() {
        return results;
    }


}
