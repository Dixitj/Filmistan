package com.banjara.dixitjain.filmistan.views.feedbackform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.banjara.dixitjain.filmistan.viewdecoration.RevelAnimation;
import com.banjara.dixitjain.filmistan.R;

public class FeedBackForm extends AppCompatActivity {

    RevelAnimation mRevealAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_fragment);

        Intent intent = this.getIntent();   //get the intent to recieve the x and y coords, that you passed before


        RelativeLayout rootLayout = findViewById(R.id.rootLayout); //there you have to get the root layout of your second activity
        mRevealAnimation = new RevelAnimation(rootLayout, intent, this);

        Button feedButton = findViewById(R.id.subbutton);
        feedButton.setOnClickListener(v -> Toast.makeText(getApplicationContext(),"Thank You for the feedback",Toast.LENGTH_LONG).show());

    }
}