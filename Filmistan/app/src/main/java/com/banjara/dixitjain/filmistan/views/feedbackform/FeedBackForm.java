package com.banjara.dixitjain.filmistan.views.feedbackform;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.banjara.dixitjain.filmistan.databinding.ActivityFeedbackFragmentBinding;
import com.banjara.dixitjain.filmistan.viewdecoration.Display;
import com.banjara.dixitjain.filmistan.viewdecoration.IDisplay;
import com.banjara.dixitjain.filmistan.viewdecoration.RevelAnimation;
import com.banjara.dixitjain.filmistan.R;

public class FeedBackForm extends AppCompatActivity {


    RevelAnimation mRevealAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFeedbackFragmentBinding feedbackFragmentBinding = DataBindingUtil.setContentView(this, R.layout.activity_feedback_fragment);
        feedbackFragmentBinding.setFeedback(this);

        Intent intent = this.getIntent();
        mRevealAnimation = new RevelAnimation(feedbackFragmentBinding.rootLayout, intent, this);

    }


    public void onSubmitClick (View view){

        IDisplay display = new Display(this);
        display.toastDisplay("Thank You for the feedback");

    }
}