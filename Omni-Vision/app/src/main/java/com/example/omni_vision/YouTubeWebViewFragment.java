package com.example.omni_vision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by JacobDexter-Milling on 5/9/16.
 */
public class YouTubeWebViewFragment extends Fragment {

    private static final String CURRENT_URL = "current_youtube_url";
    SharedPreferences sharedPreferences4;

    public static WebView myWebView;
    String youTubeURL = "https://www.youtube.com/";
    String myUrl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.youtube_webview, container, false);
        myWebView = (WebView) view.findViewById(R.id.youTubeWebView);
        sharedPreferences4 = getContext().getSharedPreferences("countPrefFour", Context.MODE_PRIVATE);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());

        myUrl = sharedPreferences4.getString(CURRENT_URL, null);

        if (myUrl == null) {
            myUrl = youTubeURL;

        }

        myWebView.loadUrl(myUrl);


        return view;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            myUrl = url;
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            sharedPreferences4.edit().putString(CURRENT_URL, url).apply();

        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}
