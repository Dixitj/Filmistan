package com.banjara.dixitjain.filmistan.views.content.moviecontent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.banjara.dixitjain.filmistan.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


public class VideoActivity extends Fragment implements YouTubePlayer.OnInitializedListener {

    private  String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        assert getArguments() != null;
        id = getArguments().getString("video_val");

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_video, container, false);

        YouTubePlayerSupportFragment youTube = YouTubePlayerSupportFragment.newInstance();
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.moviePlayer, youTube);
        transaction.commit();


        youTube.initialize("AIzaSyD3PEmPcMWDV-XdZaQ6V1XYKKPtCYJYT6M",this);

        return view;

    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


        youTubePlayer.cueVideo(id);
        youTubePlayer.setShowFullscreenButton(true);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
