package com.banjara.dixitjain.filmistan.views.signin;

import android.content.Context;
import android.content.Intent;
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
}
