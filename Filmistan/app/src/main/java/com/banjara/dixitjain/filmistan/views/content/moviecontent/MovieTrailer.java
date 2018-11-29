package com.banjara.dixitjain.filmistan.views.content.moviecontent;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.model.MovieVideo;
import com.banjara.dixitjain.filmistan.network.GenerApiUtil;
import com.banjara.dixitjain.filmistan.network.IGener;
import com.banjara.dixitjain.filmistan.views.home.HomeViewPager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieTrailer extends AppCompatActivity {

    private CompositeDisposable disposableObserver;
    private MovieInfo moviesInfo;
    private CastInfo castInfo;
    private static String intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

        disposableObserver = new CompositeDisposable();
        moviesInfo =  new MovieInfo();
        castInfo = new CastInfo();


        ViewPager viewPager = findViewById(R.id.trailerViewPager);
        trailorNetwork();
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.trailerTab);
        tabLayout.setupWithViewPager(viewPager);


        }


    private void setupViewPager(ViewPager viewPager) {

        HomeViewPager adapter = new HomeViewPager(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putString("ID", intent);
        moviesInfo.setArguments(bundle);
        castInfo.setArguments(bundle);


        adapter.addFragment(moviesInfo, "Info");
        adapter.addFragment(castInfo, "Cast");
        viewPager.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        disposableObserver.clear();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        disposableObserver.clear();

    }

    private void trailorNetwork(){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Bundle  bundle = new Bundle();


        intent = getIntent().getStringExtra("movieID");

        IGener trailorApi = GenerApiUtil.getRetrofit().create(IGener.class);
        Observable<MovieVideo> trailorVideo = trailorApi.getTrailor(Integer.parseInt(intent));

        disposableObserver.add( trailorVideo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieVideo -> {

                    if (movieVideo != null && movieVideo.getResults().size()!= 0
                    && !movieVideo.getResults().get(0).getKey().isEmpty()){

                        bundle.putString("video_val",movieVideo.getResults().get(0).getKey());
                        VideoActivity video = new VideoActivity();
                        video.setArguments(bundle);
                        fragmentTransaction.replace(R.id.moviePlayer,video);

                    }else{

                        bundle.putString("I_D", intent);
                        MovImg movImg = new MovImg();
                        movImg.setArguments(bundle);
                        fragmentTransaction.replace(R.id.moviePlayer, movImg);
                        Toast.makeText(getApplicationContext(),"No Trailor available",Toast.LENGTH_LONG).show();

                    }

                    fragmentTransaction.commit();

                }));
    }
}
