package com.rahmatsyah.moviecatalogue.service.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.model.TvShow;
import com.rahmatsyah.moviecatalogue.service.local.AppDatabase;
import com.rahmatsyah.moviecatalogue.service.local.dao.MovieDAO;
import com.rahmatsyah.moviecatalogue.service.local.dao.TvShowDAO;

import java.util.Objects;

public class ShowProvider extends ContentProvider {

    private static final String AUTHORITY = "com.rahmatsyah.moviecatalogue";

    private static final int CODE_MOVIE = 1;
    private static final int CODE_TV_SHOW = 2;

    private static final String TABLE_MOVIE = Movie.class.getSimpleName();
    private static final String TABLE_TV_SHOW = TvShow.class.getSimpleName();

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        uriMatcher.addURI(AUTHORITY,TABLE_MOVIE,CODE_MOVIE);
        uriMatcher.addURI(AUTHORITY,TABLE_TV_SHOW,CODE_TV_SHOW);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        switch (uriMatcher.match(uri)){
            case CODE_MOVIE:
                MovieDAO movieDAO = AppDatabase.getInstance(getContext()).getMovieDAO();
                return movieDAO.getAllMovieCursor();
            case CODE_TV_SHOW:
                TvShowDAO tvShowDAO = AppDatabase.getInstance(getContext()).getTvShowDAO();
                return tvShowDAO.getAllTvShowCursor();
        }
        return null;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri,null);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
