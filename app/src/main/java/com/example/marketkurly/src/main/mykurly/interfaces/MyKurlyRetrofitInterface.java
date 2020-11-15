package com.example.marketkurly.src.main.mykurly.interfaces;

import com.example.marketkurly.src.login.models.LoginBody;
import com.example.marketkurly.src.login.models.LoginResponse;
import com.example.marketkurly.src.login.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyKurlyRetrofitInterface {

    @GET("user")
    Call<UserInfoResponse> getUserInfo();
}