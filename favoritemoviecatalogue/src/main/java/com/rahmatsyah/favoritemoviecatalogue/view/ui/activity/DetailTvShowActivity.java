package com.rahmatsyah.favoritemoviecatalogue.view.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.rahmatsyah.favoritemoviecatalogue.R;
import com.rahmatsyah.favoritemoviecatalogue.model.TvShow;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String TV_SHOW_EXTRA = "tv_show";
    private static final String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        ImageView poster = findViewById(R.id.ivPosterTvShowDetail);
        TextView name = findViewById(R.id.tvNameTvShowDetail),
                voteAverage = findViewById(R.id.tvVoteAverageTvShowDetail),
                firstAirDate = findViewById(R.id.tvFirstAirDateTvShowDetail),
                overview = findViewById(R.id.tvOverviewTvShowDetail);

        TvShow tvShow = getIntent().getParcelableExtra(TV_SHOW_EXTRA);
        assert tvShow != null;
        Glide.with(this).load(BASE_POSTER+ tvShow.getPosterPath()).into(poster);
        name.setText(tvShow.getName());
        voteAverage.setText(String.valueOf(tvShow.getVoteAverage()));
        firstAirDate.setText(tvShow.getFirstAirDate());
        overview.setText(tvShow.getOverview());

    }


}
