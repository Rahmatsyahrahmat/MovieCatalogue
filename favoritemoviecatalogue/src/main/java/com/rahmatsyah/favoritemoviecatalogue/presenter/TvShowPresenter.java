package com.rahmatsyah.favoritemoviecatalogue.presenter;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;

import com.rahmatsyah.favoritemoviecatalogue.model.TvShow;
import com.rahmatsyah.favoritemoviecatalogue.view.TvShowView;

import java.util.ArrayList;

public class TvShowPresenter implements TvShowView.Presenter {

    private static final String AUTHORITY = "com.rahmatsyah.moviecatalogue";
    private static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY + "/" + TvShow.class.getSimpleName());

    private Context context;
    private TvShowView.View view;

    public TvShowPresenter(Context context, TvShowView.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void requestTvShow() {
        @SuppressLint("Recycle") ContentProviderClient contentProviderClient = context.getContentResolver().acquireContentProviderClient(CONTENT_URI);
        try {
            assert contentProviderClient != null;
            Cursor cursor = contentProviderClient.query(CONTENT_URI,new String[]{TvShow.ID,TvShow.VOTE_AVERAGE,TvShow.NAME,TvShow.FIRST_AIR_DATE,TvShow.OVERVIEW,TvShow.POSTER_PATH},null,null,null,null);
            assert cursor != null;
            if (cursor.getCount()>0)
                view.showTvShow(cursorToMovies(cursor));
            else
                view.emptyTvShow();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<TvShow> cursorToMovies(Cursor cursor){
        ArrayList<TvShow> tvShows = new ArrayList<>();
        while (cursor.moveToNext()){
            TvShow movie = new TvShow(cursor);
            tvShows.add(movie);
        }
        return tvShows;
    }
}
