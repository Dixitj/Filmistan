package com.banjara.dixitjain.filmistan.views.content.moviecontent;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.banjara.dixitjain.filmistan.network.GenerApiUtil;
import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.model.MovieResult;
import com.banjara.dixitjain.filmistan.network.IGener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class MoviesFragment extends Fragment {

    private RecyclerView moviesView ;
    private  ViewGroup view;
    private CompositeDisposable disposableObserver;
    private String generType;
    private boolean scrollState = false;
    int currentItem, totalItems, scrolledItems;
    static Integer currentPage = 1;
    @SuppressLint("StaticFieldLeak")
    private static MovieCardView adapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){


        assert getArguments() != null;
        generType = getArguments().getString("GENER_TYPE");

        try {

            view = (ViewGroup) inflater.inflate(R.layout.activity_movies_fragment, parent, false);

        }catch (InflateException e){


            System.out.println(e.getMessage());

        }

       disposableObserver = new CompositeDisposable();

        moviesView = view.findViewById(R.id.movieRecyclee);
        moviesView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        moviesView.setLayoutManager(gridLayoutManager);

        movieNetwork(generType);

        moviesView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                    scrollState = true;

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                currentItem = gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrolledItems = gridLayoutManager.findFirstVisibleItemPosition();

                if ( scrollState && (currentItem + scrolledItems == totalItems)){

                 scrollState = false;
                 currentPage ++;
                 movieNetworkNext(currentPage);

                }
            }
        });
        return view;

    }


    private void movieNetwork(String generType){

        IGener gener = GenerApiUtil.getRetrofit().create(IGener.class);
        String date = getDate();
        Observable<MovieResult> moviesData =   gener.getMovies(generType,date,currentPage);


        disposableObserver.add(
        moviesData
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe
                        (movieResult -> {

                    if (movieResult != null && movieResult.getResults().size() != 0){

                        moviesView.setItemAnimator(new SlideInUpAnimator());
                        int end = movieResult.getResults().size();
                        adapter = new MovieCardView(movieResult.getResults().subList(4,end),getContext());
                        moviesView.setAdapter(adapter);

                    } else {

                        System.out.println("Failed!!");

                    }}));
    }

    private void movieNetworkNext(Integer currentPage) {

        IGener gener = GenerApiUtil.getRetrofit().create(IGener.class);
        String date = getDate();
        Observable<MovieResult> moviesData = gener.getMovies(generType, date,currentPage);

        disposableObserver.add(
                moviesData.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(movieResult -> {

                            adapter.addItems(movieResult.getResults());
                            adapter.notifyDataSetChanged();

                        }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        disposableObserver.clear();

    }

    private String getDate(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        return mdformat.format(calendar.getTime());

    }
}