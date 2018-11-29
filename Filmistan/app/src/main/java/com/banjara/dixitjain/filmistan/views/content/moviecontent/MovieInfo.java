package com.banjara.dixitjain.filmistan.views.content.moviecontent;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.banjara.dixitjain.filmistan.model.MovieInfoModel;
import com.banjara.dixitjain.filmistan.network.GenerApiUtil;
import com.banjara.dixitjain.filmistan.network.IGener;
import com.banjara.dixitjain.filmistan.R;
import com.squareup.picasso.Picasso;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieInfo extends Fragment {

    private ViewGroup  view;
    private static  String id;
    private CompositeDisposable disposableObserver;
    private TextView movTitle,dateText,duraText,descpText,tvotesText,avotText,popTex,reveText,bugText;
    ImageView movImg;
    private static  String duration ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        assert getArguments() != null;
        id = getArguments().getString("ID");
        disposableObserver = new CompositeDisposable();


        try {

          view = (ViewGroup) inflater.inflate(R.layout.activity_movie_info, container, false);
           movImg = view.findViewById(R.id.postImage);
            movTitle = view.findViewById(R.id.movTitle);
            dateText = view.findViewById(R.id.releasedate);
            duraText = view.findViewById(R.id.durText);
            descpText = view.findViewById(R.id.descpText);
            tvotesText = view.findViewById(R.id.tvotesText);
            avotText = view.findViewById(R.id.avotText);
            popTex = view.findViewById(R.id.popTex);
            reveText = view.findViewById(R.id.reveText);
            bugText = view.findViewById(R.id.budgText);

        }catch (InflateException e){

            System.out.println(e.getMessage());

        }


        movieInfoNetwork();
        return view;
    }


    private void movieInfoNetwork(){

        IGener homeApi = GenerApiUtil.getRetrofit().create(IGener.class);
        Observable<MovieInfoModel> movieData = homeApi.getMovieInfo(Integer.parseInt(id)) ;
        disposableObserver.add(
                movieData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieInfoModel -> {

                    imagUpload(movieInfoModel.getPosterPath());

                    if ( movieInfoModel.getRuntime() == null) {

                        duration = "0";
                        //Toast.makeText(getContext(),"Movie will be out soon",Toast.LENGTH_LONG).show();

                    } else {

                        duration = movieInfoModel.getRuntime().toString();
                    }

                    setTextView(movieInfoModel);
                })
        );
    }

    private void setTextView(MovieInfoModel movieInfoModels){

        movTitle.setText(movieInfoModels.getTitle());
        dateText.setText(movieInfoModels.getReleaseDate());
        duraText.setText(duration+" mins");
        descpText.setText(movieInfoModels.getOverview());
        bugText.setText("$"+movieInfoModels.getBudget().toString());
        reveText.setText(movieInfoModels.getRevenue().toString());
        avotText.setText(movieInfoModels.getVoteAverage().toString());
        tvotesText.setText(movieInfoModels.getVoteCount().toString());
        popTex.setText(movieInfoModels.getPopularity().toString());

    }


    private void imagUpload(String image_URL){

        final String imageURl = "https://image.tmdb.org/t/p/w342";

        Picasso.get()
                .load(imageURl+image_URL)
                .resize(800,800)
                .placeholder(R.drawable.launcher)
                .noFade()
                .into(movImg);

    }
}
