package com.rahmatsyah.favoritemoviecatalogue.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {

    private long id;

    private double voteAverage;

    private String name;

    private String firstAirDate;

    private String overview;

    private String posterPath;

    public final static String ID = "id";
    public final static String VOTE_AVERAGE = "vote_average";
    public final static String NAME = "name";
    public final static String FIRST_AIR_DATE = "first_air_date";
    public final static String OVERVIEW = "overview";
    public final static String POSTER_PATH = "poster_path";

    private TvShow(Parcel in) {
        id = in.readLong();
        voteAverage = in.readDouble();
        name = in.readString();
        firstAirDate = in.readString();
        overview = in.readString();
        posterPath = in.readString();
    }

    public TvShow(Cursor cursor){
        setId(cursor.getLong(cursor.getColumnIndex(ID)));
        setVoteAverage(cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
        setName(cursor.getString(cursor.getColumnIndex(NAME)));
        setFirstAirDate(cursor.getString(cursor.getColumnIndex(FIRST_AIR_DATE)));
        setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
        setPosterPath(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public void setId(long id) {
        this.id = id;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public long getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getName() {
        return name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
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
        parcel.writeString(name);
        parcel.writeString(firstAirDate);
        parcel.writeString(overview);
        parcel.writeString(posterPath);
    }

}
