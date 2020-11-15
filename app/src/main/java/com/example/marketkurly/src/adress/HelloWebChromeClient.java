package com.example.marketkurly.src.adress;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.os.Message;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.marketkurly.src.main.MainActivity;

public class HelloWebChromeClient extends WebChromeClient {
    AddressActivity mActivity;

    @Override
    public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {

        WebView newWebView = new WebView(mActivity);

        WebSettings webSettings = newWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        final Dialog dialog1 = new Dialog(mActivity);
        dialog1.setContentView(newWebView);

        ViewGroup.LayoutParams params = dialog1.getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog1.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        dialog1.show();

        newWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onCloseWindow(WebView window) {
                dialog1.dismiss();
            }
        });
//        // WebView Popup에서 내용이 안보이고 빈 화면만 보여 아래 코드 추가
//        newWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return false;
//            }
//        });
        ((WebView.WebViewTransport) resultMsg.obj).setWebView(newWebView);
        resultMsg.sendToTarget();
        return true;

    }
}

