package com.itpvt.manara;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    android.webkit.WebView webView;
    boolean loadingFinished = true;
    boolean redirect = false;
    private ProgressBar progress;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (android.webkit.WebView) findViewById(R.id.webView);
        progress = (ProgressBar) findViewById(R.id.progressBar);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.loadUrl("http://manara.pk/index.php/");
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                    tvNoInternet.setVisibility(View.VISIBLE);
                progress.setVisibility(View.VISIBLE);
                setTitle("Loading....");
            }


            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                super.onPageFinished(view, url);
//                    tvNoInternet.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                setTitle(view.getTitle());
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
