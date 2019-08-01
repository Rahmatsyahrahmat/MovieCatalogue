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
import com.rahmatsyah.favoritemoviecatalogue.model.TvShow;
import com.rahmatsyah.favoritemoviecatalogue.presenter.TvShowPresenter;
import com.rahmatsyah.favoritemoviecatalogue.view.TvShowView;
import com.rahmatsyah.favoritemoviecatalogue.view.ui.adapter.TvShowAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements TvShowView.View {

    private TvShowAdapter tvShowAdapter;
    private ProgressBar progressBar;
    private TextView emptyTvShow;

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
        RecyclerView recyclerView = view.findViewById(R.id.tvShowList);
        progressBar = view.findViewById(R.id.tvShowProgress);
        emptyTvShow = view.findViewById(R.id.emptyTvShow);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvShowAdapter = new TvShowAdapter(getContext());
        recyclerView.setAdapter(tvShowAdapter);

        TvShowPresenter tvShowPresenter = new TvShowPresenter(getContext(),this);
        tvShowPresenter.requestTvShow();
    }

    @Override
    public void showTvShow(ArrayList<TvShow> tvShows) {
        tvShowAdapter.setListNotes(tvShows);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void emptyTvShow() {
        emptyTvShow.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
