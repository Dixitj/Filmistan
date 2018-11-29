package com.banjara.dixitjain.filmistan.views.content.moviecontent;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.model.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastInfoCardView extends RecyclerView.Adapter<CastInfoCardView.CastInfoHolder> {

    private List<Cast> castList;

    CastInfoCardView(List<Cast> castList){

        this.castList = castList;

    }



    @NonNull
    @Override
    public CastInfoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View castLayout =  LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.activity_music_card_view,viewGroup,false);

        return new  CastInfoCardView.CastInfoHolder(castLayout);
    }


    @Override
    public void onBindViewHolder(@NonNull CastInfoHolder castInfoHolder, int i) {

        final String imageURl = "https://image.tmdb.org/t/p/w342";

       castInfoHolder.dispCount.setText(castList.get(i).getCharacter());
       castInfoHolder.dispCount2.setText(castList.get(i).getName());
        Picasso.get()
                .load(imageURl+castList.get(i).getProfilePath())
                .resize(800,800)
                .placeholder(R.drawable.launcher)
                .into(castInfoHolder.actorImg);

    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    static class CastInfoHolder extends RecyclerView.ViewHolder {

        ImageView actorImg ;
        TextView dispCount,dispCount2;
        CardView cardView;

        CastInfoHolder(@NonNull View itemView) {
            super(itemView);

            actorImg = itemView.findViewById(R.id.trackImg);
            dispCount = itemView.findViewById(R.id.dispCount);
            dispCount2 = itemView.findViewById(R.id.dispCount2);
            cardView = itemView.findViewById(R.id.music_card);

        }
    }

}
