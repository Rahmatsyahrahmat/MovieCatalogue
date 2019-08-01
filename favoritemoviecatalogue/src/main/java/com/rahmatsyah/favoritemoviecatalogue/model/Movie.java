package com.rahmatsyah.favoritemoviecatalogue.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private long id;

    private double voteAverage;

    private String title;

    private String releaseDate;

    private String overview;

    private String posterPath;

    public final static String ID = "id";
    public final static String VOTE_AVERAGE = "vote_average";
    public final static String TITLE = "title";
    public final static String RELEASE_DATE = "release_date";
    public final static String OVERVIEW = "overview";
    public final static String POSTER_PATH = "poster_path";

    private Movie(Parcel in) {
        id = in.readLong();
        voteAverage = in.readDouble();
        title = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        posterPath = in.readString();
    }

    public Movie(Cursor cursor){
        setId(cursor.getLong(cursor.getColumnIndex(ID)));
        setVoteAverage(cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
        setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        setReleaseDate(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
        setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
        setPosterPath(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
    }


    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public void setId(long id) {
        this.id = id;
    }

    private void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    private void setOverview(String overview) {
        this.overview = overview;
    }

    private void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public long getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeDouble(voteAverage);
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeString(overview);
        parcel.writeString(posterPath);
    }
}