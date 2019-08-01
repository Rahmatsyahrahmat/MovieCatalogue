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
import com.rahmatsyah.moviecatalogue.model.TvShow;
import com.rahmatsyah.moviecatalogue.presenter.TvShowPresenter;
import com.rahmatsyah.moviecatalogue.view.TvShowView;
import com.rahmatsyah.moviecatalogue.view.ui.adapter.TvShowAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements TvShowView.View, SearchView.OnQueryTextListener {


    private final static String TV_SHOW_KEY = "Tv_Show";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ArrayList<TvShow> tvShows;

    private Context context;
    private Activity activity;

    private TvShowPresenter tvShowPresenter = new TvShowPresenter(this);

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.tvShowList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar = view.findViewById(R.id.tvShowProgress);

        SearchView searchView = view.findViewById(R.id.searchViewTvShow);

        SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(this);


        if (savedInstanceState!=null){
            showTvShow(savedInstanceState.<TvShow>getParcelableArrayList(TV_SHOW_KEY));
            return;
        }

        if (!isNetworkConnected()){
            Toast.makeText(getContext(),getString(R.string.no_connection),Toast.LENGTH_LONG).show();
        }else {
            tvShowPresenter.requestTvShow();
        }
    }

    @Override
    public void showTvShow(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
        TvShowAdapter tvShowAdapter = new TvShowAdapter(getContext(),tvShows);
        recyclerView.setAdapter(tvShowAdapter);
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void failureLoadTvShow() {
        Toast.makeText(getContext(),getString(R.string.failed_load_tv_show),Toast.LENGTH_LONG).show();
    }

    @Override
    public void blankSearchResults() {
        Toast.makeText(getContext(),getString(R.string.blank_search_results),Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TV_SHOW_KEY,tvShows);
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
        activity = getActivity();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        tvShowPresenter.searchTvShow(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        tvShowPresenter.searchTvShow(s);
        return false;
    }
}
