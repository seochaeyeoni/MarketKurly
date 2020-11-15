package com.example.marketkurly.src.splash.interfaces;

import com.example.marketkurly.src.login.models.UserInfoResponse;
import com.example.marketkurly.src.splash.models.AutoLoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SplashRetrofitInterface {

    @GET("login/jwt")
    Call<AutoLoginResponse> getAutoLogin();

//    @GET("user")
//    Call<UserInfoResponse> getUserInfo();

}