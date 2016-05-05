package com.example.omni_vision;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    WebView myWebView;
    String customURL = "http://espn.go.com/"; // update to editText input
    String myUrl;

    private Bundle webViewBundle;

    public void setCustomURL(String customURL){
        this.customURL = "http://www." +customURL;
    }

//    OnUrlSelectedListener mListener;
//
//    public interface OnUrlSelectedListener {
//        public void onUrlSelected(String selectedUrl);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            mListener = (OnUrlSelectedListener) getActivity();
//        } catch (ClassCastException e) {
//            throw new ClassCastException(getActivity().toString() + " must implement OnUrlSelectedListener");
//        }
//    }

//    @Override
//    public void onListItemClick() {
//        mListener.onUrlSelected();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.wikipedia_webview, container, false);
        myWebView = (WebView) view.findViewById(R.id.wikiPediaWebView);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());

        if (myUrl == null) {
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
