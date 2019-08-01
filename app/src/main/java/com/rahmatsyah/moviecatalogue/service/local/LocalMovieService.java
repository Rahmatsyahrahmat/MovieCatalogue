package com.rahmatsyah.moviecatalogue.service.local;

import android.content.Context;
import android.net.Uri;

import androidx.room.Room;

import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.service.local.dao.MovieDAO;

import java.util.ArrayList;

public class LocalMovieService {

    private Context context;

    private static MovieDAO movieDAO;

    private static final String AUTHORITY = "com.rahmatsyah.moviecatalogue";
    private static final String SCHEME = "content";
    private static final Uri CONTENT_URI = new Uri.Builder()
            .scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(Movie.class.getSimpleName())
            .build();

    private LocalMovieService(Context context){
        this.context = context;
        AppDatabase database = Room.databaseBuilder(context,AppDatabase.class,"ShowDatabase").allowMainThreadQueries().build();
        movieDAO = database.getMovieDAO();
    }

    public static LocalMovieService getInstance(Context context){
        return new LocalMovieService(context);
    }

    public void insert(Movie movie){
        movieDAO.insert(movie);
        context.getContentResolver().insert(CONTENT_URI, movie.toContentValues());
    }
    public void delete(Movie movie){
        movieDAO.delete(movie);
        context.getContentResolver().delete(CONTENT_URI,"id=?",new String[]{String.valueOf(movie.getId())});
    }
    public ArrayList<Movie> searchMovieByTitle(String title) {
        return (ArrayList<Movie>) movieDAO.searchMovieByTitle("%"+title+"%");
    }
    public ArrayList<Movie> getAllMovie(){
        return (ArrayList<Movie>) movieDAO.getAllMovie();
    }
    public boolean isAlready(long id){
        return !movieDAO.getMovieById(id).isEmpty();
    }

}
