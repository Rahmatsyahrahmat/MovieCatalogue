package com.rahmatsyah.favoritemoviecatalogue.view;

import com.rahmatsyah.favoritemoviecatalogue.model.Movie;

import java.util.ArrayList;

public interface MovieView {
    interface View{
        void showMovie(ArrayList<Movie> movies);
        void emptyMovie();
    }
    interface Presenter{
        void requestMovie();
    }
}
