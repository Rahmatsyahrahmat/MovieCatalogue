package com.rahmatsyah.moviecatalogue.service.local.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rahmatsyah.moviecatalogue.model.TvShow;

import java.util.List;

@Dao
public interface TvShowDAO {

    @Insert
    void insert(TvShow tvShow);

    @Delete
    void delete(TvShow tvShow);

    @Query("SELECT * FROM TvShow")
    List<TvShow> getAllTvShow();

    @Query("SELECT * FROM TVSHOW")
    Cursor getAllTvShowCursor();

    @Query("SELECT * FROM TvShow WHERE id = :id")
    List<TvShow> getMoviewById(long id);

    @Query(value = "SELECT * FROM TvShow WHERE name LIKE :name ")
    List<TvShow> searchTvShowByName(String name);

}
