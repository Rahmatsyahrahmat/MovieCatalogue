package com.rahmatsyah.favoritemoviecatalogue.view.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.rahmatsyah.favoritemoviecatalogue.R;
import com.rahmatsyah.favoritemoviecatalogue.model.Movie;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String MOVIE_EXTRA = "movie";
    private static final String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ImageView poster = findViewById(R.id.ivPosterMovieDetail);
        TextView title = findViewById(R.id.tvTitleMovieDetail),
                voteAverage = findViewById(R.id.tvVoteAverageMovieDetail),
                relaeaseDate = findViewById(R.id.tvReleaseDateMovieDetail),
                overview = findViewById(R.id.tvOverviewMovieDetail);

        Movie movie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        assert movie != null;
        Glide.with(this).load(BASE_POSTER+ movie.getPosterPath()).into(poster);
        title.setText(movie.getTitle());
        voteAverage.setText(String.valueOf(movie.getVoteAverage()));
        relaeaseDate.setText(movie.getReleaseDate());
        overview.setText(movie.getOverview());

    }



}
