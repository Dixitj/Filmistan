package com.banjara.dixitjain.filmistan.views.content.musiccontent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.model.MusicModel;
import com.banjara.dixitjain.filmistan.network.GenerApiUtil;
import com.banjara.dixitjain.filmistan.network.IGener;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class MusicFragment extends Fragment {

    private  ViewGroup view;
    private RecyclerView musicView;
    private CompositeDisposable compositeDisposable;
    int currentItem, totalItems, scrolledItems;
    static Integer currentPage = 1;
    private Boolean isScrolling = false;
    private MusicCardView adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        compositeDisposable = new CompositeDisposable();

        try {

            view = (ViewGroup) inflater.inflate(R.layout.activity_movies_fragment, parent, false);

        }catch (InflateException e){

            System.out.println(e.getMessage());

        }

        musicView = view.findViewById(R.id.movieRecyclee);

        musicView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        musicView.setLayoutManager(gridLayoutManager);

        trackNetwork(currentPage);

        musicView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                isScrolling = true;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItem = gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrolledItems = gridLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItem + scrolledItems == totalItems)) {

                    isScrolling = false;
                    currentPage++;
                    trackNetworkNext(currentPage);

                }
            }
        });

        return view;
    }



    private void trackNetwork(Integer currentPage){


        IGener gener =  GenerApiUtil.getTrackRetrofit().create(IGener.class);
        Observable<MusicModel> trackList = gener.getTracks(currentPage);

        compositeDisposable.add(
        trackList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((MusicModel musicModelList) -> {

                    if (musicModelList != null ){

                        musicView.setItemAnimator(new SlideInUpAnimator());
                        adapter = new MusicCardView(musicModelList.getArtists().getArtist(), getContext());
                        musicView.setAdapter(adapter);

                    }

                }));
    }

    private void trackNetworkNext(Integer currentPage){

        IGener gener =  GenerApiUtil.getTrackRetrofit().create(IGener.class);
        Observable<MusicModel> trackList = gener.getTracks(currentPage);

        compositeDisposable.add(
                trackList.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(musicModelList -> {

                            adapter.addItems(musicModelList.getArtists().getArtist());
                            adapter.notifyDataSetChanged();
    }));
}

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();

    }
}