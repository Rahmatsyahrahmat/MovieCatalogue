package com.rahmatsyah.moviecatalogue.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Movie")
public class Movie implements Parcelable{

    @PrimaryKey
    @SerializedName(ID)
    @ColumnInfo(name = ID)
    private long id;

    @Expose
    @SerializedName(VOTE_AVERAGE)
    @ColumnInfo(name = VOTE_AVERAGE)
    private double voteAverage;

    @SerializedName(TITLE)
    @ColumnInfo(name = TITLE)
    private String title;

    @SerializedName(RELEASE_DATE)
    @ColumnInfo(name = RELEASE_DATE)
    private String releaseDate;

    @SerializedName(OVERVIEW)
    @ColumnInfo(name = OVERVIEW)
    private String overview;

    @SerializedName(POSTER_PATH)
    @ColumnInfo(name = POSTER_PATH)
    private String posterPath;

    private final static String ID = "id";
    private final static String VOTE_AVERAGE = "vote_average";
    private final static String TITLE = "title";
    private final static String RELEASE_DATE = "release_date";
    private final static String OVERVIEW = "overview";
    private final static String POSTER_PATH = "poster_path";

    private Movie(Parcel in) {
        id = in.readLong();
        voteAverage = in.readDouble();
        title = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        posterPath = in.readString();
    }


    public Movie(){}


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

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    public ContentValues toContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,getId());
        contentValues.put(VOTE_AVERAGE,getVoteAverage());
        contentValues.put(TITLE,getTitle());
        contentValues.put(RELEASE_DATE,getReleaseDate());
        contentValues.put(OVERVIEW,getOverview());
        contentValues.put(POSTER_PATH,getPosterPath());
        return contentValues;
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
