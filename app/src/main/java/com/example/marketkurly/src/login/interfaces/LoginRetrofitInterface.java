package com.example.marketkurly.src.login.interfaces;

import com.example.marketkurly.src.login.models.LoginBody;
import com.example.marketkurly.src.login.models.LoginResponse;
import com.example.marketkurly.src.login.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {

    @POST("login/guest")
    Call<LoginResponse> postLogin(@Body LoginBody params);

    @GET("user")
    Call<UserInfoResponse> getUserInfo();
}