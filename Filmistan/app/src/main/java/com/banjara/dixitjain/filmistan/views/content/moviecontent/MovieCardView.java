package com.banjara.dixitjain.filmistan.views.content.moviecontent;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieCardView  extends RecyclerView.Adapter<MovieCardView.MovieHolder>{

    private static  List<Result> result;
    private Context context;

    public MovieCardView(){}
    MovieCardView(List<Result> result, Context context){

        MovieCardView.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View movieView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.activity_music_card_view,viewGroup,false);

        return new MovieCardView.MovieHolder(movieView);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {

        final String imageURl = "https://image.tmdb.org/t/p/w342";

        movieHolder.titleText.setText(result.get(i).getTitle());
        movieHolder.dateText.setText(result.get(i).getReleaseDate());
        System.out.print(imageURl+result.get(i).getPosterPath());
        Picasso.get()
                .load(imageURl + result.get(i).getPosterPath())
                .resize(800,800)
                .placeholder(R.drawable.launcher)
                .into(movieHolder.imageView);

        movieHolder.cardView.setOnClickListener(v -> {

            Intent intent = new Intent(context, MovieTrailer.class);
            intent.putExtra("movieID", result.get(i).getId().toString());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void  addItems(List<Result> resultExtra){

        result.addAll(resultExtra);
    }

    static class MovieHolder extends RecyclerView.ViewHolder {

        ImageView imageView ;
        TextView  titleText, dateText;
        CardView cardView;

        MovieHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.trackImg);
            titleText = itemView.findViewById(R.id.dispCount);
            dateText = itemView.findViewById(R.id.dispCount2);
            cardView = itemView.findViewById(R.id.music_card);
        }
    }
}

