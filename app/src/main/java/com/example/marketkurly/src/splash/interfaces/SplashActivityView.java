package com.example.marketkurly.src.splash.interfaces;

import com.example.marketkurly.src.splash.models.AutoLoginResponse;

public interface SplashActivityView {

    void autoLoginSuccess(AutoLoginResponse autoLoginResponse);

    void autoLoginFailure(String message);
}
