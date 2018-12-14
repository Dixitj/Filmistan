package com.banjara.dixitjain.filmistan.views.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;
import com.banjara.dixitjain.filmistan.model.Gener;
import com.banjara.dixitjain.filmistan.network.GenerApiUtil;
import com.banjara.dixitjain.filmistan.network.IGener;
import com.banjara.dixitjain.filmistan.viewdecoration.RevelAnimation;
import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.databinding.ActivityHomeBinding;
import com.banjara.dixitjain.filmistan.model.MovieResult;
import com.banjara.dixitjain.filmistan.views.signin.ISignUpVm;
import com.banjara.dixitjain.filmistan.views.signin.SignIn;
import com.banjara.dixitjain.filmistan.views.signin.SignUpVm;
import java.util.Timer;
import java.util.TimerTask;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class HomeActivity extends AppCompatActivity {

    private CompositeDisposable disposableObserver;
    private IGener homeApi;
    ActivityHomeBinding homeBinding;
    int currentPage = 0;
    int NUM_PAGES = 0;
    //SignUpVm signUpVm = new SignUpVm(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GridLayoutManager gridLayoutManager;

        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeBinding.setHome(this);

        disposableObserver = new CompositeDisposable();

        homeBinding.homeRecycle.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this, 2);
        homeBinding.homeRecycle.setLayoutManager(gridLayoutManager);


        homeApi = GenerApiUtil.getRetrofit().create(IGener.class);

        slideShowScreen();
        homeNetwork();


    }

    @SuppressLint("CheckResult")
    @Override
    public void onStart() {
        super.onStart();

    }

    public void onResume() {
        super.onResume();

    }


    public void fabClick(View view) {

        RevelAnimation revelAnimation = new RevelAnimation(view);
        revelAnimation.startRevealActivity();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {

        ISignUpVm signUpVm = new SignUpVm(this);
        signUpVm.signOutHandler(disposableObserver, new Intent(this, SignIn.class));

    }

    @Override
    protected void onStop() {
        super.onStop();

        disposableObserver.clear();

    }

    private void homeNetwork() {

        Single<Gener> generSingle = homeApi.getGenerData();

        disposableObserver.add(
                generSingle.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(generList -> {

                            if (generList != null && generList.getGenres().size() != 0) {

                                homeBinding.homeRecycle.setAdapter(new HomeCardView(generList.getGenres(), this));
                                homeBinding.homeRecycle.setItemAnimator(new SlideInUpAnimator());

                            } else {

                                Toast.makeText(this, "NO RESULTS FOUND",
                                        Toast.LENGTH_LONG).show();
                            }
                        }));
    }


    private void slideShowScreen() {


        Observable<MovieResult> upComingMovies = homeApi.getUpcomingMovies();

        disposableObserver.add(
                upComingMovies.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(movieResult -> {

                            if (movieResult != null) {

                                homeBinding.navSlideshow.setAdapter(new SlideShowImg(movieResult.getResults(), HomeActivity.this));
                                NUM_PAGES = movieResult.getResults().size();
                            }
                        }));


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == NUM_PAGES - 1) {
                currentPage = 0;
            }

            homeBinding.navSlideshow.setCurrentItem(currentPage++, true);
            //mPager.setCurrentItem(currentPage++, true);
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

    }


}
