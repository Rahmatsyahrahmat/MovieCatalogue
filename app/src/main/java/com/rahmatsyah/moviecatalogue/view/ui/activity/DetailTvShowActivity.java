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
import com.rahmatsyah.moviecatalogue.model.TvShow;
import com.rahmatsyah.moviecatalogue.service.local.LocalTvShowService;
import com.rahmatsyah.moviecatalogue.view.ui.widget.FavoriteWidget;


public class DetailTvShowActivity extends AppCompatActivity {

    public static final String TV_SHOW_EXTRA = "tv_show";
    private static final String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    private TvShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        ImageView poster = findViewById(R.id.ivPosterTvShowDetail);
        TextView name = findViewById(R.id.tvNameTvShowDetail),
                voteAverage = findViewById(R.id.tvVoteAverageTvShowDetail),
                firstAirDate = findViewById(R.id.tvFirstAirDateTvShowDetail),
                overview = findViewById(R.id.tvOverviewTvShowDetail);

        tvShow = getIntent().getParcelableExtra(TV_SHOW_EXTRA);
        assert tvShow != null;
        Glide.with(this).load(BASE_POSTER+tvShow.getPosterPath()).into(poster);
        name.setText(tvShow.getName());
        voteAverage.setText(String.valueOf(tvShow.getVoteAverage()));
        firstAirDate.setText(tvShow.getFirstAirDate());
        overview.setText(tvShow.getOverview());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favorite_menu,menu);

        if (LocalTvShowService.getInstance(this).isAlready(tvShow.getId())){
            menu.getItem(0).setIcon(R.drawable.ic_favorite_white_24dp);
        }
        else {
            menu.getItem(0).setIcon(R.drawable.ic_favorite_border_24dp);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (LocalTvShowService.getInstance(this).isAlready(tvShow.getId())){
            LocalTvShowService.getInstance(this).delete(tvShow);
            Toast.makeText(this,R.string.deleted_fovorit_tv_show,Toast.LENGTH_SHORT).show();
            item.setIcon(R.drawable.ic_favorite_border_24dp);
        }
        else {
            LocalTvShowService.getInstance(this).insert(tvShow);
            Toast.makeText(this,R.string.inserted_fovorit_tv_show,Toast.LENGTH_SHORT).show();
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
