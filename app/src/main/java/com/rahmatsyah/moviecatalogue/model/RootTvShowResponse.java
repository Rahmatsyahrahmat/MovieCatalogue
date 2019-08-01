package com.rahmatsyah.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootTvShowResponse {

    @SerializedName("results")
    private ArrayList<TvShow> results;

    public RootTvShowResponse(ArrayList<TvShow> results) {
        this.results = results;
    }

    public ArrayList<TvShow> getResults() {
        return results;
    }

}
