package com.rahmatsyah.moviecatalogue.service.api;

import com.rahmatsyah.moviecatalogue.model.RootMovieResponse;
import com.rahmatsyah.moviecatalogue.model.RootTvShowResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static ApiService apiService = new ApiService();
    private static IApiService service;

    private ApiService(){

        Retrofit retrofit = createAdapter();
        service = retrofit.create(IApiService.class);

    }

    public static ApiService getInstance(){
        return apiService;
    }

    private Retrofit createAdapter(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public Call<RootMovieResponse> getMovies(){
        return service.getAllMovie();
    }

    public Call<RootTvShowResponse> getTvShows() {
        return service.getAllTvShow();
    }

    public Call<RootMovieResponse> getMovieSearchResults(String query){
        return service.getMovieSearchResult(query);
    }

    public Call<RootTvShowResponse> getTvShowSearchResults(String query){
        return service.getTvShowSearchResult(query);
    }

    public Call<RootMovieResponse> getTodaysMoviesRelease(String releaseDateGte, String releaseDateIte){
        return service.getTodaysMovieRelease(releaseDateGte,releaseDateIte);
    }

    public Call<RootTvShowResponse> getTodaysTvShowsRelease(String firstAirDateGte, String firstAirDateIte){
        return service.getTodaysTvShowRelease(firstAirDateGte,firstAirDateIte);
    }

}
