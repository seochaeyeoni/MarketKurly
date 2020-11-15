package com.example.marketkurly.src.signup.interfaces;

import com.example.marketkurly.src.signup.models.IdCheckResponse;
import com.example.marketkurly.src.signup.models.SignUpResponse;
import com.example.marketkurly.src.signup.models.SignUpBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignUpRetrofitInterface {

    @GET("is-duplicate-id")
    Call<IdCheckResponse> getIdCheck(
            @Query("userId") final String userId
    );

    @POST("user")
    Call<SignUpResponse> postSignUp(@Body SignUpBody signUpBody);
}