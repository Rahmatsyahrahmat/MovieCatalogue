package com.rahmatsyah.moviecatalogue.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "TvShow")
public class TvShow implements Parcelable {

    @PrimaryKey
    @SerializedName(ID)
    @ColumnInfo(name = ID)
    private long id;

    @Expose
    @SerializedName(VOTE_AVERAGE)
    @ColumnInfo(name = VOTE_AVERAGE)
    private double voteAverage;

    @SerializedName(NAME)
    @ColumnInfo(name = NAME)
    private String name;

    @SerializedName(FIRST_AIR_DATE)
    @ColumnInfo(name = FIRST_AIR_DATE)
    private String firstAirDate;

    @SerializedName(OVERVIEW)
    @ColumnInfo(name = OVERVIEW)
    private String overview;

    @SerializedName(POSTER_PATH)
    @ColumnInfo(name = POSTER_PATH)
    private String posterPath;

    private final static String ID = "id";
    private final static String VOTE_AVERAGE = "vote_average";
    private final static String NAME = "name";
    private final static String FIRST_AIR_DATE = "first_air_date";
    private final static String OVERVIEW = "overview";
    private final static String POSTER_PATH = "poster_path";

    private TvShow(Parcel in) {
        id = in.readLong();
        voteAverage = in.readDouble();
        name = in.readString();
        firstAirDate = in.readString();
        overview = in.readString();
        posterPath = in.readString();
    }

    public TvShow(){}

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

    public ContentValues toContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,getId());
        contentValues.put(VOTE_AVERAGE,getVoteAverage());
        contentValues.put(NAME,getName());
        contentValues.put(FIRST_AIR_DATE,getFirstAirDate());
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
        parcel.writeString(name);
        parcel.writeString(firstAirDate);
        parcel.writeString(overview);
        parcel.writeString(posterPath);
    }

}
