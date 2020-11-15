package com.example.marketkurly.src.main.interfaces;

import com.example.marketkurly.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
    //    @GET("/test")
    @GET("/jwt")
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

//    @POST("/test")
//    Call<SignUpResponse> postSignUp(@Body RequestBody params);

}