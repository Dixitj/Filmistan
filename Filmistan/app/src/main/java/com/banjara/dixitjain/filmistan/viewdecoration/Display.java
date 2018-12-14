package com.banjara.dixitjain.filmistan.viewdecoration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.widget.ImageView;
import android.widget.Toast;


public class Display implements IDisplay {

    private Context  context;

    public Display(Context context){

        this.context = context;
    }

    @Override
    public void screenTransition(Intent intent) {

        context.startActivity(intent);

    }

    @Override
    public void toastDisplay(String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void ontTransition(Intent intent, ImageView imageView, String transitionName) {

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,imageView, transitionName);

        context.startActivity(intent,optionsCompat.toBundle());
    }

}
