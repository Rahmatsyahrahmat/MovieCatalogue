package com.rahmatsyah.moviecatalogue.view.ui.fragment;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.model.TvShow;
import com.rahmatsyah.moviecatalogue.service.local.LocalTvShowService;
import com.rahmatsyah.moviecatalogue.view.ui.adapter.TvShowAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private TextView emptyMessage;

    private Context context;
    private Activity activity;


    public FavoriteTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.favoriteTvShowList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyMessage = view.findViewById(R.id.emptyFavoriteTvShow);
        SearchView searchView = view.findViewById(R.id.searchViewFavoriteTvShow);
        ProgressBar progressBar = view.findViewById(R.id.favoriteTvShowProgress);

        SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(this);

        if (LocalTvShowService.getInstance(getContext()).getAllTvShow().isEmpty()){
            emptyMessage.setVisibility(View.VISIBLE);
        }
        else {
            TvShowAdapter tvShowAdapter = new TvShowAdapter(getContext(), LocalTvShowService.getInstance(getContext()).getAllTvShow());
            recyclerView.setAdapter(tvShowAdapter);
        }
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (LocalTvShowService.getInstance(getContext()).getAllTvShow().isEmpty()){
            emptyMessage.setVisibility(View.VISIBLE);
        }
        TvShowAdapter tvShowAdapter = new TvShowAdapter(getContext(), LocalTvShowService.getInstance(getContext()).getAllTvShow());
        recyclerView.setAdapter(tvShowAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = getActivity();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (LocalTvShowService.getInstance(getContext()).getAllTvShow().isEmpty()){
            emptyMessage.setVisibility(View.VISIBLE);
            return false;
        }
        ArrayList<TvShow> tvShows;
        if (s.isEmpty()){
            tvShows = LocalTvShowService.getInstance(context).getAllTvShow();
        }
        else {
            tvShows = LocalTvShowService.getInstance(context).searchTvShowByName(s);
        }
        TvShowAdapter tvShowAdapter = new TvShowAdapter(getContext(), tvShows);
        recyclerView.setAdapter(tvShowAdapter);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (LocalTvShowService.getInstance(getContext()).getAllTvShow().isEmpty()){
            emptyMessage.setVisibility(View.VISIBLE);
            return false;
        }
        ArrayList<TvShow> tvShows;
        if (s.isEmpty()){
            tvShows = LocalTvShowService.getInstance(context).getAllTvShow();
        }
        else {
            tvShows = LocalTvShowService.getInstance(context).searchTvShowByName(s);
        }
        TvShowAdapter tvShowAdapter = new TvShowAdapter(getContext(), tvShows);
        recyclerView.setAdapter(tvShowAdapter);
        return false;
    }
}

