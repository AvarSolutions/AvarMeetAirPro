package com.avarsolutions.meetings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Policy extends AppCompatActivity {

    private WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_policy);
        webView1 = (WebView) findViewById(R.id.wv);
        webView1.setWebChromeClient(new WebChromeClient());
        webView1.loadUrl("file:///android_asset/policy.html");
        WebSettings webSettings = webView1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView1.getSettings().setBuiltInZoomControls(true);
        webView1.getSettings().setDisplayZoomControls(false);

    }
}