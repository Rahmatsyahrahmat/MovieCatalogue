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
import com.rahmatsyah.moviecatalogue.model.Movie;
import com.rahmatsyah.moviecatalogue.view.ui.activity.DetailMovieActivity;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;


    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_show,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
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

        void bind(Movie movie){
            Glide.with(context).load(BASE_POSTER+movie.getPosterPath()).into(background);
            Glide.with(context).load(BASE_POSTER+movie.getPosterPath()).into(poster);
            title.setText(movie.getTitle());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.MOVIE_EXTRA,movies.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }

}
