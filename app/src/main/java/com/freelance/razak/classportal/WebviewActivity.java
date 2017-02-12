package com.freelance.razak.classportal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Razak on 2/11/2017.
 */

public class WebviewActivity extends Fragment {
    private WebView webview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.webview_layout,container,false);
        webview = (WebView) v.findViewById(R.id.webviewId);
        webview.clearCache(true);
        webview.getSettings().setJavaScriptEnabled(true);
        Bundle bundle = this.getArguments();
        String url = bundle.getString("parse");
        webview.loadUrl(url);
        return v;
    }
}
