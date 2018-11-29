package com.banjara.dixitjain.filmistan.views.content.musiccontent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.banjara.dixitjain.filmistan.R;

public class MusicContent extends AppCompatActivity {

    private  WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_content);

        String URL = getIntent().getStringExtra("MuiscURL");
        webView = findViewById(R.id.webView);
        webView.loadUrl(URL);

        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;

        }

        super.onBackPressed();
    }
}
