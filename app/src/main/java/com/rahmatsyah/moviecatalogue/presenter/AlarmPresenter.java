package com.rahmatsyah.moviecatalogue.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.rahmatsyah.moviecatalogue.model.RootMovieResponse;
import com.rahmatsyah.moviecatalogue.model.RootTvShowResponse;
import com.rahmatsyah.moviecatalogue.service.api.ApiService;
import com.rahmatsyah.moviecatalogue.view.AlarmView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmPresenter implements AlarmView.Presenter {

    private AlarmView.View view;
    private Context context;
    private int notifId;

    public AlarmPresenter(Context context,AlarmView.View view, int notifId) {
        this.context = context;
        this.view = view;
        this.notifId = notifId;
    }

    @Override
    public void requestMovies(String date) {
        ApiService.getInstance().getTodaysMoviesRelease(date,date).enqueue(new Callback<RootMovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootMovieResponse> call,@NonNull Response<RootMovieResponse> response) {
                assert response.body() != null;
                view.setMovies(context,response.body().getResults(),notifId);
            }

            @Override
            public void onFailure(@NonNull Call<RootMovieResponse> call,@NonNull Throwable t) {

            }
        });
    }

    @Override
    public void requestTvShows(String date) {
        ApiService.getInstance().getTodaysTvShowsRelease(date,date).enqueue(new Callback<RootTvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootTvShowResponse> call,@NonNull Response<RootTvShowResponse> response) {
                assert response.body() != null;
                view.setTvShow(context,response.body().getResults(),notifId);
            }

            @Override
            public void onFailure(@NonNull Call<RootTvShowResponse> call,@NonNull Throwable t) {

            }
        });
    }
}
