package com.rahmatsyah.moviecatalogue.view.ui.fragment;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.service.local.LocalMovieService;
import com.rahmatsyah.moviecatalogue.view.ui.adapter.MovieAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private TextView emptyMessage;

    private Context context;
    private Activity activity;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.favoriteMovieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyMessage = view.findViewById(R.id.emptyFavoriteMovie);
        ProgressBar progressBar = view.findViewById(R.id.favoriteMovieProgress);
        SearchView searchView = view.findViewById(R.id.searchViewFavoriteMovie);

        SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(this);

        if (LocalMovieService.getInstance(getContext()).getAllMovie().isEmpty()){
            emptyMessage.setVisibility(View.VISIBLE);
        }
        else {
            MovieAdapter movieAdapter = new MovieAdapter(getContext(), LocalMovieService.getInstance(getContext()).getAllMovie());
            recyclerView.setAdapter(movieAdapter);
        }
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (LocalMovieService.getInstance(getContext()).getAllMovie().isEmpty()){
            emptyMessage.setVisibility(View.VISIBLE);
        }
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), LocalMovieService.getInstance(getContext()).getAllMovie());
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = getActivity();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (LocalMovieService.getInstance(getContext()).getAllMovie().isEmpty()){
            emptyMessage.setVisibility(View.VISIBLE);
            return false;
        }
        ArrayList<Movie> movies;
        if (s.isEmpty()){
            movies = LocalMovieService.getInstance(context).getAllMovie();
        }
        else {
            movies = LocalMovieService.getInstance(context).searchMovieByTitle(s);
        }
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movies);
        recyclerView.setAdapter(movieAdapter);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (LocalMovieService.getInstance(getContext()).getAllMovie().isEmpty()){
            emptyMessage.setVisibility(View.VISIBLE);
            return false;
        }
        ArrayList<Movie> movies;
        if (s.isEmpty()){
            movies = LocalMovieService.getInstance(context).getAllMovie();
        }
        else {
            movies = LocalMovieService.getInstance(context).searchMovieByTitle(s);
        }
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movies);
        recyclerView.setAdapter(movieAdapter);
        return false;
    }


}
