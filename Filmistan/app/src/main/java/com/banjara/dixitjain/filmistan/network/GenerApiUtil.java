package com.banjara.dixitjain.filmistan.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenerApiUtil {


    public static Retrofit getRetrofit (){

        final String  BASE_URL = "https://api.themoviedb.org/3/";

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static Retrofit getTrackRetrofit(){

        final String  BASE_URL = "http://ws.audioscrobbler.com/";

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

}
