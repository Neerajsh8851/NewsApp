package com.ns.newsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.ns.newsapp.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView mWebView = findViewById(R.id.web_view);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        // load
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(url);
    }
}