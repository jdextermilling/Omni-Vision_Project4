package com.example.omni_vision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

/**
 * Created by JacobDexter-Milling on 5/5/16.
 */
public class CustomWebViewFragment extends Fragment {

    private static final String CURRENT_URL = "current_custom_url";
    WebView myWebView;
    String customURL;
    String myUrl;

    SharedPreferences sharedPreferences3;

    private Bundle webViewBundle;

    public void setCustomURL(String customURL){
        Log.i("CustomWebViewFRagment", "setting custom URL to " +customURL);
        this.customURL = "http://www." +customURL;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wikipedia_webview, container, false);
        myWebView = (WebView) view.findViewById(R.id.wikiPediaWebView);

        sharedPreferences3 = getContext().getSharedPreferences("countPrefThree", Context.MODE_PRIVATE);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());

//        myUrl = customURL;
        myUrl = sharedPreferences3.getString(CURRENT_URL, null);

        Log.i("CustomWebView", " myURL is " + myUrl);
        if (customURL != null) {
            myUrl = customURL;
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
            sharedPreferences3.edit().putString(CURRENT_URL, url).apply();

        }
    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setRetainInstance(true);
//        if (webViewBundle != null)
//        {
//            myWebView.restoreState(webViewBundle);
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//
//        webViewBundle = new Bundle();
//        myWebView.saveState(webViewBundle);
//    }
}
