package com.example.omni_vision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by JacobDexter-Milling on 5/4/16.
 */

// context.getSharedPreferences("OtherPrefs", Context.MODE_PRIVATE);


public class WikiPediaWebViewFragment extends Fragment {
    private static final String CURRENT_URL = "current_wiki_url";
    private static final String TAG = WikiPediaWebViewFragment.class.getSimpleName();

    WebView myWebView;
    String wikiPediaURL = "https://en.wikipedia.org/wiki/Main_Page";
    String myUrl = null;
    SharedPreferences sharedPreferences;
    private Bundle webViewBundle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wikipedia_webview, container, false);
        myWebView = (WebView) view.findViewById(R.id.wikiPediaWebView);
        sharedPreferences = getContext().getSharedPreferences("countPrefOne", Context.MODE_PRIVATE);


        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());



        //runWebView();

        myUrl = sharedPreferences.getString(CURRENT_URL, null);

        if (myUrl == null) {
            myUrl = wikiPediaURL;
        }
        myWebView.loadUrl(myUrl);

        return view;

    }

    public void runWebView(){
        if (myUrl == null) {
            myUrl = wikiPediaURL;
            myWebView.loadUrl(myUrl);
        } else{
            myUrl = sharedPreferences.getString(CURRENT_URL, null);
            myWebView.loadUrl(myUrl);
        }
    }

    /**
     *
     * @param newHome if new home is set to null the WebView will reset itself to display the Wikipedia homepage.
     */
    public void setNewWebViewHome(@Nullable String newHome) {
        Log.w(TAG, "Home url was set to null by: setNewWebViewHome()");
        sharedPreferences.edit().putString(CURRENT_URL, newHome).apply();
    }

    public void resetWebView(){
        sharedPreferences.edit().putString(CURRENT_URL, null).apply();
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
           sharedPreferences.edit().putString(CURRENT_URL, url).apply();

        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        if (webViewBundle != null)
        {
            myWebView.restoreState(webViewBundle);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        webViewBundle = new Bundle();
        myWebView.saveState(webViewBundle);
    }

}

