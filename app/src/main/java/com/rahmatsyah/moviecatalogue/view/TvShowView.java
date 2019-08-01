package com.rahmatsyah.moviecatalogue.view;

import com.rahmatsyah.moviecatalogue.model.TvShow;

import java.util.ArrayList;

public interface TvShowView {

    interface View{
        void showTvShow(ArrayList<TvShow> tvShows);
        void failureLoadTvShow();
        void blankSearchResults();
    }
    interface Presenter{
        void requestTvShow();
        void searchTvShow(String query);
    }

}
