package com.rahmatsyah.moviecatalogue.service.local;

import android.content.Context;
import android.net.Uri;

import androidx.room.Room;

import com.rahmatsyah.moviecatalogue.model.TvShow;
import com.rahmatsyah.moviecatalogue.service.local.dao.TvShowDAO;

import java.util.ArrayList;

public class LocalTvShowService {

    private static TvShowDAO tvShowDAO;
    private Context context;

    private static final String AUTHORITY = "com.rahmatsyah.moviecatalogue";
    private static final String SCHEME = "content";
    private static final Uri CONTENT_URI = new Uri.Builder()
            .scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TvShow.class.getSimpleName())
            .build();

    private LocalTvShowService(Context context){
        this.context = context;
        AppDatabase database = Room.databaseBuilder(context,AppDatabase.class,"ShowDatabase").allowMainThreadQueries().build();
        tvShowDAO = database.getTvShowDAO();
    }

    public static LocalTvShowService getInstance(Context context){
        return new LocalTvShowService(context);
    }

    public void insert(TvShow tvShow){
        tvShowDAO.insert(tvShow);
        context.getContentResolver().insert(CONTENT_URI,tvShow.toContentValues());
    }

    public void delete(TvShow tvShow){
        tvShowDAO.delete(tvShow);
        context.getContentResolver().delete(CONTENT_URI,"id=?",new String[]{String.valueOf(tvShow.getId())});
    }

    public ArrayList<TvShow> searchTvShowByName(String name) {
        return (ArrayList<TvShow>) tvShowDAO.searchTvShowByName("%"+name+"%");
    }

    public ArrayList<TvShow> getAllTvShow(){
        return (ArrayList<TvShow>) tvShowDAO.getAllTvShow();
    }
    public boolean isAlready(long id){
        return !tvShowDAO.getMoviewById(id).isEmpty();
    }

}
