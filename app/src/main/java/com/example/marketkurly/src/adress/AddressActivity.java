package com.example.marketkurly.src.adress;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketkurly.R;
import com.example.marketkurly.src.main.MainActivity;
import com.example.marketkurly.src.signup.SignUpActivity;
import com.example.marketkurly.src.splash.SplashActivity;

public class AddressActivity extends AppCompatActivity {
    private WebView daum_webView;
    private Handler handler;
    private String mSubAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        // WebView 초기화
        init_webView();

        // 핸들러를 통한 JavaScript 이벤트 반응
        handler = new Handler();
    }


    public class WebChromeClient extends android.webkit.WebChromeClient {

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView newWebView = new WebView(AddressActivity.this);

            WebSettings webSettings = newWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            final Dialog dialog = new Dialog(AddressActivity.this);
            dialog.setContentView(newWebView);

            ViewGroup.LayoutParams params = dialog.getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
            dialog.show();

            newWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onCloseWindow(WebView window) {
                    dialog.dismiss();
                }
            });
            ((WebView.WebViewTransport)resultMsg.obj).setWebView(newWebView);
            resultMsg.sendToTarget();
            return true;
        }
    }

    public void init_webView() {
        // WebView 설정
        daum_webView = (WebView) findViewById(R.id.daum_webview);

        // JavaScript 허용
        daum_webView.getSettings().setJavaScriptEnabled(true);

        // JavaScript의 window.open 허용
        daum_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        daum_webView.getSettings().setSupportMultipleWindows(true);

        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        daum_webView.addJavascriptInterface(new AndroidBridge(), "TestApp");

        // web client 를 chrome 으로 설정
        daum_webView.setWebChromeClient(new WebChromeClient());

        // webview url load. html 파일 주소
        daum_webView.loadUrl("https://mymarketcurly.shop/address.php");
    }


    private class AndroidBridge {

        @JavascriptInterface

        public void setAddress(final String arg1, final String arg2, final String arg3) {

            handler.post(new Runnable() {

                @Override

                public void run() {

                    final LinearLayout address_input = (LinearLayout) findViewById(R.id.address_input);
                    TextView address_num = (TextView) findViewById(R.id.address_num);
                    TextView address_main = (TextView) findViewById(R.id.address_main);
                    final EditText address_sub = (EditText) findViewById(R.id.address_sub);

                    address_num.setText(arg1);
                    address_main.setText(String.format("%s (%s)", arg2, arg3));
                    address_input.setVisibility(View.VISIBLE);
                    daum_webView.setVisibility(View.INVISIBLE);

                    LinearLayout address_before = (LinearLayout) findViewById(R.id.address_before);
                    address_before.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            address_input.setVisibility(View.INVISIBLE);
                            daum_webView.setVisibility(View.VISIBLE);
                        }
                    });

                    address_sub.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) { }
                        @Override
                        public void afterTextChanged(Editable s) {
                            mSubAddress = address_sub.getText().toString();
                        }
                    });

                    Button address_btn = (Button) findViewById(R.id.address_btn);
                    address_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("address_num", arg1);
                            intent.putExtra("address_main", String.format("%s (%s)", arg2, arg3));
                            intent.putExtra("address_sub", mSubAddress);
                            setResult(102, intent);

                            init_webView();

                            finish();

                        }
                    });

                    // WebView를 초기화 하지않으면 재사용할 수 없음



                }

            });

        }

    }

}


