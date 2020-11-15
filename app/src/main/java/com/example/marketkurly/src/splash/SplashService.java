package com.example.marketkurly.src.splash;

import com.example.marketkurly.src.main.interfaces.MainActivityView;
import com.example.marketkurly.src.main.interfaces.MainRetrofitInterface;
import com.example.marketkurly.src.main.models.DefaultResponse;
import com.example.marketkurly.src.splash.interfaces.SplashActivityView;
import com.example.marketkurly.src.splash.interfaces.SplashRetrofitInterface;
import com.example.marketkurly.src.splash.models.AutoLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

class SplashService {
    private final SplashActivityView mSplashActivityView;

    SplashService(final SplashActivityView splashActivityView) {
        this.mSplashActivityView = splashActivityView;
    }

    void getAutoLogin() {
        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.getAutoLogin().enqueue(new Callback<AutoLoginResponse>() {
            @Override
            public void onResponse(Call<AutoLoginResponse> call, Response<AutoLoginResponse> response) {
                final AutoLoginResponse autoLoginResponse = response.body();
                if (autoLoginResponse == null) {
                    mSplashActivityView.autoLoginFailure(null);
                    return;
                }

                mSplashActivityView.autoLoginSuccess(autoLoginResponse);
            }

            @Override
            public void onFailure(Call<AutoLoginResponse> call, Throwable t) {
                mSplashActivityView.autoLoginFailure(null);
            }
        });
    }
}