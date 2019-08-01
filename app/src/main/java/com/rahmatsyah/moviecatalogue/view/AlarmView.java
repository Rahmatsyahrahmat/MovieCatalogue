package com.rahmatsyah.moviecatalogue.view;

import android.content.Context;

import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.model.TvShow;

import java.util.ArrayList;

public interface AlarmView {
    interface View{
        void setMovies(Context context, ArrayList<Movie> movies, int notifId);
        void setTvShow(Context context,ArrayList<TvShow> tvShows, int notifId);
    }
    interface Presenter{
        void requestMovies(String date);
        void requestTvShows(String date);
    }
}
