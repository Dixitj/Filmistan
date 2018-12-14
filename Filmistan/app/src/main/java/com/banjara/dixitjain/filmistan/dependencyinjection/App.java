package com.banjara.dixitjain.filmistan.dependencyinjection;

import android.app.Application;


public class App extends Application {


    /*
    private Appcomponent appcomponent;


    @Override
    public void onCreate() {
        super.onCreate();
        appcomponent = buildComponent();
    }

    public Appcomponent getAppcomponent(){

        //appcomponent = new A
        return appcomponent;
    }

    public Appcomponent buildComponent(){

        return DaggerAppcomponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }*/
}
