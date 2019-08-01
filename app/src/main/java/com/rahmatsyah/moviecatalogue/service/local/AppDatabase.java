package com.rahmatsyah.moviecatalogue.service.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.model.TvShow;
import com.rahmatsyah.moviecatalogue.service.local.dao.MovieDAO;
import com.rahmatsyah.moviecatalogue.service.local.dao.TvShowDAO;

@Database(entities = {Movie.class, TvShow.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public abstract MovieDAO getMovieDAO();
    public abstract TvShowDAO getTvShowDAO();
    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context, AppDatabase.class, "ShowDatabase").allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }
}
