package com.rahmatsyah.moviecatalogue.view.ui.activity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.service.local.LocalMovieService;
import com.rahmatsyah.moviecatalogue.view.ui.widget.FavoriteWidget;


public class DetailMovieActivity extends AppCompatActivity {

    public static final String MOVIE_EXTRA = "movie";
    private static final String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ImageView poster = findViewById(R.id.ivPosterMovieDetail);
        TextView title = findViewById(R.id.tvTitleMovieDetail),
                voteAverage = findViewById(R.id.tvVoteAverageMovieDetail),
                relaeaseDate = findViewById(R.id.tvReleaseDateMovieDetail),
                overview = findViewById(R.id.tvOverviewMovieDetail);

        movie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        assert movie != null;
        Glide.with(this).load(BASE_POSTER+movie.getPosterPath()).into(poster);
        title.setText(movie.getTitle());
        voteAverage.setText(String.valueOf(movie.getVoteAverage()));
        relaeaseDate.setText(movie.getReleaseDate());
        overview.setText(movie.getOverview());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favorite_menu,menu);

        if (LocalMovieService.getInstance(this).isAlready(movie.getId())){
            menu.getItem(0).setIcon(R.drawable.ic_favorite_white_24dp);
        }
        else {
            menu.getItem(0).setIcon(R.drawable.ic_favorite_border_24dp);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (LocalMovieService.getInstance(this).isAlready(movie.getId())){
            LocalMovieService.getInstance(this).delete(movie);
            Toast.makeText(this,R.string.deleted_fovorit_movie,Toast.LENGTH_SHORT).show();
            item.setIcon(R.drawable.ic_favorite_border_24dp);
        }
        else {
            LocalMovieService.getInstance(this).insert(movie);
            Toast.makeText(this,R.string.inserted_fovorit_movie,Toast.LENGTH_SHORT).show();
            item.setIcon(R.drawable.ic_favorite_white_24dp);
        }

        Intent intent = new Intent(this, FavoriteWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplicationContext(),FavoriteWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);

        return true;
    }

}
