package com.rahmatsyah.favoritemoviecatalogue.view;


import com.rahmatsyah.favoritemoviecatalogue.model.TvShow;

import java.util.ArrayList;

public interface TvShowView {
    interface View{
        void showTvShow(ArrayList<TvShow> tvShows);
        void emptyTvShow();
    }
    interface Presenter{
        void requestTvShow();
    }
}
