package com.rahmatsyah.moviecatalogue.presenter;

import androidx.annotation.NonNull;

import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.model.RootMovieResponse;
import com.rahmatsyah.moviecatalogue.service.api.ApiService;
import com.rahmatsyah.moviecatalogue.view.MovieView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements MovieView.Presenter {

    private MovieView.View view;

    public MoviePresenter(MovieView.View view) {
        this.view = view;
    }

    @Override
    public void requestMovie() {
        ApiService.getInstance().getMovies().enqueue(new Callback<RootMovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootMovieResponse> call, @NonNull Response<RootMovieResponse> response) {
                if (response.body() != null) {
                    view.showMovie(response.body().getResults());
                }
                else {
                    view.failureLoadMovie();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RootMovieResponse> call, @NonNull Throwable t) {
                view.failureLoadMovie();
            }
        });
    }

    @Override
    public void searchMovie(String query) {
        if (query.isEmpty()){
            requestMovie();
            return;
        }
        ApiService.getInstance().getMovieSearchResults(query).enqueue(new Callback<RootMovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootMovieResponse> call,@NonNull Response<RootMovieResponse> response) {
                if (response.body() != null) {
                    ArrayList<Movie> movies = response.body().getResults();
                    if (movies.isEmpty()){
                        view.showMovie(movies);
                        view.blankSearchResults();
                    }
                    else {
                        view.showMovie(movies);
                    }
                }
                else {
                    view.failureLoadMovie();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RootMovieResponse> call,@NonNull Throwable t) {
                view.failureLoadMovie();
            }
        });
    }

}
