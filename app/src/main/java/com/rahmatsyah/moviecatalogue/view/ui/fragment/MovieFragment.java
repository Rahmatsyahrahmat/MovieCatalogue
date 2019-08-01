package com.rahmatsyah.moviecatalogue.view.ui.fragment;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.presenter.MoviePresenter;
import com.rahmatsyah.moviecatalogue.view.MovieView;
import com.rahmatsyah.moviecatalogue.view.ui.adapter.MovieAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieView.View, SearchView.OnQueryTextListener {

    private final static String MOVIE_KEY = "Movie";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ArrayList<Movie> movies;

    private Context context;
    private Activity activity;

    private MoviePresenter moviePresenter =  new MoviePresenter(this);

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar = view.findViewById(R.id.movieProgress);
        SearchView searchView = view.findViewById(R.id.searchViewMovie);

        SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(this);


        if (savedInstanceState!=null){
            showMovie(savedInstanceState.<Movie>getParcelableArrayList(MOVIE_KEY));
            return;
        }

        if (!isNetworkConnected()){
            Toast.makeText(getContext(),getString(R.string.no_connection),Toast.LENGTH_SHORT).show();
        }else {
            moviePresenter.requestMovie();
        }

    }

    @Override
    public void showMovie(ArrayList<Movie> movies) {
        this.movies = movies;
        MovieAdapter movieAdapter = new MovieAdapter(getContext(),movies);
        recyclerView.setAdapter(movieAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void failureLoadMovie() {
        Toast.makeText(getContext(),getString(R.string.failed_load_movie),Toast.LENGTH_LONG).show();
    }

    @Override
    public void blankSearchResults() {
        Toast.makeText(getContext(),getString(R.string.blank_search_results),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MOVIE_KEY,movies);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = getActivity();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        moviePresenter.searchMovie(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        moviePresenter.searchMovie(s);
        return false;
    }
}
