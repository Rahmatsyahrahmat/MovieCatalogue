package com.rahmatsyah.favoritemoviecatalogue.presenter;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;

import com.rahmatsyah.favoritemoviecatalogue.model.Movie;
import com.rahmatsyah.favoritemoviecatalogue.view.MovieView;

import java.util.ArrayList;

public class MoviePresenter implements MovieView.Presenter {

    private static final String AUTHORITY = "com.rahmatsyah.moviecatalogue";
    private static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY + "/" + Movie.class.getSimpleName());

    private Context context;
    private MovieView.View view;

    public MoviePresenter(Context context, MovieView.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void requestMovie() {
        @SuppressLint("Recycle") ContentProviderClient contentProviderClient = context.getContentResolver().acquireContentProviderClient(CONTENT_URI);
        try {
            assert contentProviderClient != null;
            Cursor cursor = contentProviderClient.query(CONTENT_URI,new String[]{Movie.ID,Movie.VOTE_AVERAGE,Movie.TITLE,Movie.RELEASE_DATE,Movie.OVERVIEW,Movie.POSTER_PATH},null,null,null,null);
            assert cursor != null;
            if (cursor.getCount()>0)
                view.showMovie(cursorToMovies(cursor));
            else
                view.emptyMovie();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Movie> cursorToMovies(Cursor cursor){
        ArrayList<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext()){
            Movie movie = new Movie(cursor);
            movies.add(movie);
        }
        return movies;
    }
}
