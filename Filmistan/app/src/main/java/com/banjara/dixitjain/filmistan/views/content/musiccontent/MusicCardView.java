package com.banjara.dixitjain.filmistan.views.content.musiccontent;

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

import com.banjara.dixitjain.filmistan.model.Artist;
import com.banjara.dixitjain.filmistan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MusicCardView extends RecyclerView.Adapter<MusicCardView.MusicHolder>{

    private List<Artist> musicModelList ;
    private Context context;

    public MusicCardView(){}

    MusicCardView(List<Artist> musicModelList, Context context){

        this.musicModelList = musicModelList;
        this.context = context;

    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View musicLayout =  LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.activity_music_card_view,viewGroup,false);

        return new  MusicCardView.MusicHolder(musicLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder musicHolder, int i) {

        musicHolder.dispCount.setText(musicModelList.get(i).getName());
        Picasso.get()
                .load(musicModelList.get(i).getImage().get(2).getText())
                .placeholder(R.drawable.launcher)
                .into(musicHolder.track);

        musicHolder.cardView.setOnClickListener(v -> {

            Intent intent = new Intent(context, MusicContent.class);
            intent.putExtra("MuiscURL", musicModelList.get(i).getUrl());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return musicModelList.size();
    }

    public void  addItems(List<Artist> resultExtra){

        musicModelList.addAll(resultExtra);
    }


    static class MusicHolder extends RecyclerView.ViewHolder{

        ImageView track ;
        TextView dispCount;
        CardView cardView;

        MusicHolder(@NonNull View itemView) {
            super(itemView);

            track = itemView.findViewById(R.id.trackImg);
            dispCount = itemView.findViewById(R.id.dispCount);
            cardView = itemView.findViewById(R.id.music_card);

        }
    }
}
