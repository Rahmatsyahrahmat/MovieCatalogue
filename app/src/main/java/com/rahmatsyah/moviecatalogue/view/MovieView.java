package com.rahmatsyah.moviecatalogue.view;

import com.rahmatsyah.moviecatalogue.model.Movie;

import java.util.ArrayList;

public interface MovieView {

    interface View{
        void showMovie(ArrayList<Movie> movies);
        void failureLoadMovie();
        void blankSearchResults();
    }
    interface Presenter{
        void requestMovie();
        void searchMovie(String query);
    }

}
