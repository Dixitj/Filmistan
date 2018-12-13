package com.banjara.dixitjain.filmistan.views.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.banjara.dixitjain.filmistan.model.Genre;
import com.banjara.dixitjain.filmistan.views.content.ContentActivity;
import com.banjara.dixitjain.filmistan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeCardView extends RecyclerView.Adapter<HomeCardView.HomeHolder> {

    private List<Genre> moviesGener ;
    private Context context;
    private  List<String> imageURl = new ArrayList<>();

    public HomeCardView(){}

    HomeCardView(List<Genre> moviesGener, Context context){

        this.moviesGener = moviesGener;
        this.context = context;

    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View homeView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.activity_home_card_view,viewGroup,false);

        imageUrl();
        return new HomeHolder(homeView);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder homeHolder, int i) {

        final String image = "https://image.tmdb.org/t/p/w342";

        homeHolder.title.setText(moviesGener.get(i).getName());
        Picasso.get().load(image+imageURl.get(i))
                .resize(800,800)
                .placeholder(R.drawable.launcher)
                .into(homeHolder.catImage);

        homeHolder.cardView.setOnClickListener(v -> {

            Intent intent = new Intent(context,ContentActivity.class);
            intent.putExtra("GENER_TYPE", moviesGener.get(i).getId().toString());
            intent.putExtra("image_URL", image+imageURl.get(i));
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation((Activity) context
                            ,homeHolder.catImage ,
                            Objects.requireNonNull(ViewCompat.getTransitionName(homeHolder.catImage)));

            context.startActivity(intent,optionsCompat.toBundle());

        });
    }

    @Override
    public int getItemCount() {
        return moviesGener.size();
    }


    static class HomeHolder extends RecyclerView.ViewHolder  {
        ImageView catImage;
        TextView title;
        CardView cardView;

        HomeHolder(@NonNull View itemView ) {
            super(itemView);

            catImage = itemView.findViewById(R.id.catImage);
            title = itemView.findViewById(R.id.titleText);
            cardView =  itemView.findViewById(R.id.home_card);

        }
    }

    private void imageUrl (){


        imageURl.add("/jHoq7jolZbdtHgQsjouUt0wBPlh.jpg");
        imageURl.add("/lmrgXnG90DFZYeLrNhuEKUo7nKk.jpg");
        imageURl.add("/6Nsr5pyRL85DEYV2UTLCZeDcKfR.jpg");
        imageURl.add("/ew8dDvSB5moPyu4PeX4Hyva4I01.jpg");
        imageURl.add("/8ZwOeuXpj9pcnEzZoMANdkM06Nt.jpg");
        imageURl.add("/nJ7CtpcrqCL6MkgVQaDvMba75dG.jpg");
        imageURl.add("/4wktp6lrAGeYnPD5HWB8fsyzYhk.jpg");
        imageURl.add("/nLfHxGaXAHd3RaQYf9kW8DQ5CZ6.jpg");
        imageURl.add("/njm9No3y4HmnNbzJXMUWsZSftEi.jpg");
        imageURl.add("/e0NZxqQ4B8YZ6kRrZish2DPqtdi.jpg");
        imageURl.add("/bO6u62tU2hPAOUO4KlsYaiItsgR.jpg");
        imageURl.add("/jf7jiy0rDVn1ljUwv3pWKousQ1p.jpg");
        imageURl.add("/mmV5TX3IYy7NEd6WEHgpnM6VLZx.jpg");
        imageURl.add("/5Zui0AMg6PBNmw9F82XLJCFz3aq.jpg");
        imageURl.add("/n7gzELVhuQmW0Wt1vIO4gKUx4Vi.jpg");
        imageURl.add("/lWv0hPLIqdVwjFE9z3Xz6TqeNzm.jpg");
        imageURl.add("/3h9CXbBmHxjjeoXoslTiDtep3PB.jpg");
        imageURl.add("/n7gzELVhuQmW0Wt1vIO4gKUx4Vi.jpg");
        imageURl.add("/AdYPOcJt1g8LTs16q8mIbEE63y4.jpg");

    }
}
