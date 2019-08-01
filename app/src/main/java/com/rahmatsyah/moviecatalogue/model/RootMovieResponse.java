package com.rahmatsyah.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootMovieResponse {

    @SerializedName("results")
    private ArrayList<Movie> results;

    public RootMovieResponse(ArrayList<Movie> results) {
        this.results = results;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

}
