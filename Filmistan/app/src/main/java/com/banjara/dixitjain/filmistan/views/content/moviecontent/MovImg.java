package com.banjara.dixitjain.filmistan.views.content.moviecontent;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.banjara.dixitjain.filmistan.network.GenerApiUtil;
import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.model.MovieInfoModel;
import com.banjara.dixitjain.filmistan.network.IGener;
import com.squareup.picasso.Picasso;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovImg extends Fragment {

    private CompositeDisposable disposableObserver;
    private ImageView movImg;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        disposableObserver = new CompositeDisposable();

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_mov_img, container, false);
        movImg = view.findViewById(R.id.mov);

        assert getArguments() != null;
        image(getArguments().getString("I_D"));

        return view;

    }


    private void image(String id){

        IGener homeApi = GenerApiUtil.getRetrofit().create(IGener.class);
        Observable<MovieInfoModel> movieData = homeApi.getMovieInfo(Integer.parseInt(id)) ;
        disposableObserver.add(
                movieData.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(movieInfoModel -> {

                            //if (movieInfoModel.getRuntime() == null) {

                                imagUpload(movieInfoModel.getPosterPath());

                            //}
                        }));
        }


    private void imagUpload(String image_URL){

        final String imageURl = "https://image.tmdb.org/t/p/w342";

        Picasso.get()
                .load(imageURl+image_URL)
                .resize(1500,800)
                .noFade()
                .placeholder(R.drawable.launcher)
                .into(movImg);

    }
}
