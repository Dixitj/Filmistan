package com.banjara.dixitjain.filmistan.views.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import com.banjara.dixitjain.filmistan.views.feedbackform.FeedBackForm;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Timer;
import java.util.TimerTask;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class HomeActivity extends AppCompatActivity {

    //private RecyclerView recyclerView;
    private CompositeDisposable disposableObserver;
    private IGener homeApi;
    private ViewPager mPager;
    ActivityHomeBinding homeBinding;
    GridLayoutManager gridLayoutManager;
    int currentPage = 0;
    int NUM_PAGES = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeBinding.setHome(this);

        disposableObserver = new CompositeDisposable();

        mPager = findViewById(R.id.nav_slideshow);
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

        startRevealActivity(view);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        signOutProcess();

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
                                //recyclerView.setAdapter(new HomeCardView(generList.getGenres(), this));

                                homeBinding.homeRecycle.setItemAnimator(new SlideInUpAnimator());
                                //recyclerView.setItemAnimator(new SlideInUpAnimator());

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

                                //mPager.setAdapter(new SlideShowImg(movieResult.getResults(), getApplicationContext()));
                                mPager.setAdapter(new SlideShowImg(movieResult.getResults(),HomeActivity.this));
                                //indicator.setViewPager(mPager);
                                NUM_PAGES = movieResult.getResults().size();
                            }
                        }));



        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == NUM_PAGES - 1) {
                currentPage = 0;
            }
            mPager.setCurrentItem(currentPage++, true);
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

    }

    private void startRevealActivity(View v) {
        //calculates the center of the View v you are passing
        int revealX = (int) (v.getX() + v.getWidth() / 2);
        int revealY = (int) (v.getY() + v.getHeight() / 2);

        //create an intent, that launches the second activity and pass the x and y coordinates
        Intent intent = new Intent(this, FeedBackForm.class);
        intent.putExtra(RevelAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(RevelAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        //just start the activity as an shared transition, but set the options bundle to null
        ActivityCompat.startActivity(this, intent, null);

        //to prevent strange behaviours override the pending transitions
        overridePendingTransition(0, 0);
    }


    private void signOutProcess(){


        AlertDialog.Builder aletBox = new AlertDialog.Builder(this);

        aletBox.setMessage("Are you sure,\n" + "You wanted to SignOut?").setPositiveButton("Yes", (dialog, which) -> {

            Toast.makeText(getApplicationContext(),"Successfully Logged Out!", Toast.LENGTH_LONG).show();
            FirebaseAuth.getInstance().signOut();
            disposableObserver.clear();
            HomeActivity.this.finish();

        }).setNegativeButton("No", (dialog, which) -> dialog.cancel());

        AlertDialog display = aletBox.create();
        display.show();
    }


}
