package com.example.omni_vision;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by JacobDexter-Milling on 5/4/16.
 */
public class WikiPediaWebViewFragment extends Fragment {

    WebView myWebView;
    final static String wikiPediaURL = "https://en.wikipedia.org/wiki/Main_Page";
    String myUrl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wikipedia_webview, container, false);
        myWebView = (WebView) view.findViewById(R.id.wikiPediaWebView);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());

        if (myUrl == null) {
            myUrl = wikiPediaURL;
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
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

}
