package com.example.marketkurly.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.marketkurly.R;
import com.example.marketkurly.src.main.MainActivity;
import com.example.marketkurly.src.splash.SplashService;
import com.example.marketkurly.src.splash.interfaces.SplashActivityView;
import com.example.marketkurly.src.splash.models.AutoLoginResponse;

public class SplashActivity extends AppCompatActivity implements SplashActivityView {
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tryGetAutoLogin();

        intent = new Intent(SplashActivity.this, MainActivity.class);

        startLoading();

    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void tryGetAutoLogin() {
        //showProgressDialog();

        final SplashService splashService = new SplashService(this);
        splashService.getAutoLogin();
    }

    @Override
    public void autoLoginSuccess(AutoLoginResponse autoLoginResponse) {
        if (autoLoginResponse.getIsSuccess()) {
            //자동 로그인 성공 -> 회원정보 가져오기 -> 회원정보 뿌려주기
            Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_LONG).show();
            //RequestActivity로 전달할 인텐트 생성
            Intent intent = new Intent(this, MainActivity.class);
            //text값 인텐트에 저장
            intent.putExtra("isUser", autoLoginResponse.getIsSuccess());

        } else {
            Toast.makeText(getApplicationContext(), "login fail", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void autoLoginFailure(String message) {

    }
}
