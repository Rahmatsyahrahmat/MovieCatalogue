package com.rahmatsyah.moviecatalogue.presenter;

import androidx.annotation.NonNull;

import com.rahmatsyah.moviecatalogue.model.RootTvShowResponse;
import com.rahmatsyah.moviecatalogue.model.TvShow;
import com.rahmatsyah.moviecatalogue.service.api.ApiService;
import com.rahmatsyah.moviecatalogue.view.TvShowView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowPresenter implements TvShowView.Presenter {

    private TvShowView.View view;

    public TvShowPresenter(TvShowView.View tvShowView) {
        view = tvShowView;
    }

    @Override
    public void requestTvShow() {
        ApiService.getInstance().getTvShows().enqueue(new Callback<RootTvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootTvShowResponse> call, @NonNull Response<RootTvShowResponse> response) {
                if (response.body() != null) {
                    view.showTvShow(response.body().getResults());
                }
                else {
                    view.failureLoadTvShow();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RootTvShowResponse> call,@NonNull Throwable t) {
                view.failureLoadTvShow();
            }
        });
    }

    @Override
    public void searchTvShow(String query) {
        if (query.isEmpty()){
            requestTvShow();
            return;
        }
        ApiService.getInstance().getTvShowSearchResults(query).enqueue(new Callback<RootTvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<RootTvShowResponse> call,@NonNull Response<RootTvShowResponse> response) {
                if (response.body() != null) {
                    ArrayList<TvShow> tvShows = response.body().getResults();
                    if (tvShows.isEmpty()){
                        view.showTvShow(tvShows);
                        view.blankSearchResults();
                    }
                    else {
                        view.showTvShow(tvShows);
                    }
                }
                else {
                    view.failureLoadTvShow();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RootTvShowResponse> call,@NonNull Throwable t) {
                view.failureLoadTvShow();
            }
        });
    }

}