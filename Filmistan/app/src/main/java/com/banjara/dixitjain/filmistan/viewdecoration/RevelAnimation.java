package com.banjara.dixitjain.filmistan.viewdecoration;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

public class RevelAnimation {

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    private final View mView;
    private Activity mActivity;

    private int revealX;
    private int revealY;

    public RevelAnimation(View view, Intent intent, Activity activity) {
        mView = view;
        mActivity = activity;

        //when you're android version is at leat Lollipop it starts the reveal activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            view.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);

            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {

            //if you are below android 5 it jist shows the activity
            view.setVisibility(View.VISIBLE);
        }
    }

    private void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(mView.getWidth(), mView.getHeight()) * 1.1);

            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(mView, x, y, 0, finalRadius);
            circularReveal.setDuration(300);
            circularReveal.setInterpolator(new AccelerateInterpolator());

            // make the view visible and start the animation
            mView.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            mActivity.finish();
        }
    }



}
