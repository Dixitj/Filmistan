package com.banjara.dixitjain.filmistan.views.content;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.banjara.dixitjain.filmistan.views.content.moviecontent.MoviesFragment;
import com.banjara.dixitjain.filmistan.views.content.musiccontent.MusicFragment;
import com.banjara.dixitjain.filmistan.R;
import com.banjara.dixitjain.filmistan.views.home.HomeViewPager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ContentActivity extends AppCompatActivity {


    public ViewPager viewPager;
    private MoviesFragment moviesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ImageView backImg = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String generType = intent.getStringExtra("GENER_TYPE");
        String  image_URL = intent.getStringExtra("image_URL");
        postponeEnterTransition();

        Picasso.get()
                .load(image_URL)
                .resize(2000,200)
                .noFade()
                .into(backImg, new Callback(){

                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {
                        supportStartPostponedEnterTransition();

                    }
                });

        moviesFragment = new MoviesFragment();
        viewPager = findViewById(R.id.homeViewPager);
        setupViewPager(viewPager, generType);


        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);


    }



    private void setupViewPager(ViewPager viewPager, String generType) {


        HomeViewPager adapter = new HomeViewPager(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("GENER_TYPE", generType);
        moviesFragment.setArguments(bundle);

        adapter.addFragment(moviesFragment, "Movies");
        adapter.addFragment(new MusicFragment(), "Music");
        viewPager.setAdapter(adapter);
    }

}


