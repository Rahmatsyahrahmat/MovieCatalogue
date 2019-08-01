package com.rahmatsyah.moviecatalogue.service.local.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rahmatsyah.moviecatalogue.model.Movie;

import java.util.List;

@Dao
public interface MovieDAO {

    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM Movie")
    List<Movie> getAllMovie();

    @Query("SELECT * FROM Movie")
    Cursor getAllMovieCursor();

    @Query("SELECT * FROM Movie WHERE id = :id")
    List<Movie> getMovieById(long id);

    @Query(value = "SELECT * FROM Movie WHERE title LIKE :title ")
    List<Movie> searchMovieByTitle(String title);

}
