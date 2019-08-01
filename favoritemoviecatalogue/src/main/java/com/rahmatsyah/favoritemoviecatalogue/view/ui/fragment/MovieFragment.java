package com.rahmatsyah.favoritemoviecatalogue.view.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmatsyah.favoritemoviecatalogue.R;
import com.rahmatsyah.favoritemoviecatalogue.model.Movie;
import com.rahmatsyah.favoritemoviecatalogue.presenter.MoviePresenter;
import com.rahmatsyah.favoritemoviecatalogue.view.MovieView;
import com.rahmatsyah.favoritemoviecatalogue.view.ui.adapter.MovieAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieView.View {


    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private TextView emptyMovies;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.movieList);
        progressBar = view.findViewById(R.id.movieProgress);
        emptyMovies = view.findViewById(R.id.emptyMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieAdapter = new MovieAdapter(getContext());
        recyclerView.setAdapter(movieAdapter);

        MoviePresenter moviePresenter = new MoviePresenter(getContext(),this);
        moviePresenter.requestMovie();

    }


    @Override
    public void showMovie(ArrayList<Movie> movies) {
        movieAdapter.setListNotes(movies);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void emptyMovie() {
        emptyMovies.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
