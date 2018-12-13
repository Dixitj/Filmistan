package com.banjara.dixitjain.filmistan.viewdecoration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.banjara.dixitjain.filmistan.views.home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

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


}
