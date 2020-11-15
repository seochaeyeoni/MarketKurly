package com.example.marketkurly.src.login.interfaces;

import com.example.marketkurly.src.login.models.LoginResponse;
import com.example.marketkurly.src.login.models.UserInfoResponse;

public interface LoginActivityView {

    void loginSuccess(LoginResponse loginResponse);

    void loginFailure(String message);

    void userInfoSuccess(UserInfoResponse userInfoResponse);

    void userInfoFailure(String message);
}
