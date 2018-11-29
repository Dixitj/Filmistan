package com.banjara.dixitjain.filmistan.views.content.moviecontent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.banjara.dixitjain.filmistan.model.MovieInfoModel;
import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.network.GenerApiUtil;
import com.banjara.dixitjain.filmistan.network.IGener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CastInfo extends Fragment {

private ViewGroup view;
private static String  id ;
private CompositeDisposable disposableObserver;
private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        try {

            view = (ViewGroup) inflater.inflate(R.layout.activity_movies_fragment, container, false);


            assert getArguments() != null;
            id = getArguments().getString("ID");
            disposableObserver = new CompositeDisposable();

            recyclerView = view.findViewById(R.id.movieRecyclee);
            recyclerView.setHasFixedSize(true);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(gridLayoutManager);

            castInfoNetwork();


        }catch (InflateException e){

            System.out.println(e.getMessage());

        }

        return view;
    }


    private void castInfoNetwork(){

        IGener homeApi = GenerApiUtil.getRetrofit().create(IGener.class);
        Observable<MovieInfoModel> movieData = Observable.create(new ObservableOnSubscribe<MovieInfoModel>() {
            @Override
            public void subscribe(ObservableEmitter<MovieInfoModel> emitter) throws Exception {

                homeApi.getMovieInfo(Integer.parseInt(id));
            }
        });

        disposableObserver.add(
                movieData.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(movieInfoModel -> {

                            if (movieInfoModel != null) {

                                recyclerView.setAdapter(new CastInfoCardView(movieInfoModel.getCredits().getCast()));

                            }
                        }));
    }
}
