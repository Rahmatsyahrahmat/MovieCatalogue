package com.rahmatsyah.moviecatalogue.view.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.model.TvShow;
import com.rahmatsyah.moviecatalogue.view.ui.activity.DetailTvShowActivity;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TvShow> tvShows;

    public TvShowAdapter(Context context, ArrayList<TvShow> tvShows) {
        this.context = context;
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_show,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvShow tvShow = tvShows.get(position);
        holder.bind(tvShow);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView background, poster;
        private TextView title;

        private final static String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.itemShowBackground);
            poster = itemView.findViewById(R.id.itemShowPoster);
            title = itemView.findViewById(R.id.itemShowTitle);
            itemView.setOnClickListener(this);
        }

        void bind(TvShow tvShow){
            Glide.with(context).load(BASE_POSTER+tvShow.getPosterPath()).into(background);
            Glide.with(context).load(BASE_POSTER+tvShow.getPosterPath()).into(poster);
            title.setText(tvShow.getName());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.TV_SHOW_EXTRA,tvShows.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }

}
