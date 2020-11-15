package com.example.marketkurly.src.main.mykurly.interfaces;

import com.example.marketkurly.src.login.models.LoginResponse;
import com.example.marketkurly.src.login.models.UserInfoResponse;

public interface MyKurlyActivityView {

    void userInfoSuccess(UserInfoResponse userInfoResponse);

    void userInfoFailure(String message);
}
