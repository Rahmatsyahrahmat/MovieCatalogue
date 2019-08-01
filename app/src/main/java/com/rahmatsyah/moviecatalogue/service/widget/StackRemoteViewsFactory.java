package com.rahmatsyah.moviecatalogue.service.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.model.TvShow;
import com.rahmatsyah.moviecatalogue.service.local.LocalMovieService;
import com.rahmatsyah.moviecatalogue.service.local.LocalTvShowService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    private Context context;
    private static ArrayList<Bitmap> bitmaps = new ArrayList<>();

    StackRemoteViewsFactory(Context context){
        this.context = context;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        bitmaps.clear();
        for (Movie movie: LocalMovieService.getInstance(context).getAllMovie()){
            try {
                URL url = new URL(BASE_POSTER+movie.getPosterPath());
                bitmaps.add(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (TvShow tvShow: LocalTvShowService.getInstance(context).getAllTvShow()){
            try {
                URL url = new URL(BASE_POSTER+tvShow.getPosterPath());
                bitmaps.add(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        views.setImageViewBitmap(R.id.widgetPoster,bitmaps.get(i));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
