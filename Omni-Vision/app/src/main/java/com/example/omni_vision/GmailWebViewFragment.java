package com.example.omni_vision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by JacobDexter-Milling on 5/4/16.
 */
public class GmailWebViewFragment extends Fragment {

    private static final String CURRENT_URL = "current_gmail_url";
    SharedPreferences sharedPreferences2;

    public static WebView myWebView;
    String gmailURL = "https://accounts.google.com/ServiceLogin?" +
            "service=mail&continue=https://mail.google.com/mail/#identifier";
    String myUrl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gmail_webview, container, false);
        myWebView = (WebView) view.findViewById(R.id.gmailWebView);
        sharedPreferences2 = getContext().getSharedPreferences("countPrefTwo", Context.MODE_PRIVATE);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());

        myUrl = sharedPreferences2.getString(CURRENT_URL, null);

        if (myUrl == null) {
            myUrl = gmailURL;

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
            sharedPreferences2.edit().putString(CURRENT_URL, url).apply();

        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

}
