package com.rahmatsyah.moviecatalogue.service.api;

import com.rahmatsyah.moviecatalogue.BuildConfig;
import com.rahmatsyah.moviecatalogue.model.RootMovieResponse;
import com.rahmatsyah.moviecatalogue.model.RootTvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiService {

    @GET("discover/movie?api_key="+ BuildConfig.ApiKey)
    Call<RootMovieResponse> getAllMovie();
    @GET("discover/tv?api_key="+BuildConfig.ApiKey)
    Call<RootTvShowResponse> getAllTvShow();
    @GET("search/movie?api_key="+BuildConfig.ApiKey)
    Call<RootMovieResponse> getMovieSearchResult(@Query("query") String query);
    @GET("search/tv?api_key="+BuildConfig.ApiKey)
    Call<RootTvShowResponse> getTvShowSearchResult(@Query("query") String query);
    @GET("discover/movie?api_key="+ BuildConfig.ApiKey)
    Call<RootMovieResponse> getTodaysMovieRelease(@Query("primary_release_date.gte") String releaseDateGte, @Query("primary_release_date.lte") String releaseDateIte);
    @GET("discover/tv?api_key="+ BuildConfig.ApiKey)
    Call<RootTvShowResponse> getTodaysTvShowRelease(@Query("first_air_date.gte") String firstAirDateGte, @Query("first_air_date.lte") String firstAirDateIte);

}
