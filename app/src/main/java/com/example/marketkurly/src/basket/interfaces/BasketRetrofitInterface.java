package com.example.marketkurly.src.basket.interfaces;

import com.example.marketkurly.src.basket.models.BasketChangeBody;
import com.example.marketkurly.src.basket.models.BasketChangeResponse;
import com.example.marketkurly.src.basket.models.BasketInfoResponse;
import com.example.marketkurly.src.basket.models.DeleteBasketItemResponse;
import com.example.marketkurly.src.main.models.DefaultResponse;
import com.example.marketkurly.src.signup.models.IdCheckResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BasketRetrofitInterface {

    @GET("basket")
    Call<BasketInfoResponse> getBasketInfo();

    @PATCH("basket")
    Call<BasketChangeResponse> patchBasketChange(@Body BasketChangeBody params);

    @DELETE("basket")
    Call<DeleteBasketItemResponse> deleteBasketItem(
            @Query("option") final String options
    );

//    @GET("/test/{number}")
//    Call<DefaultResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );

//    @POST("/test")
//    Call<SignUpResponse> postSignUp(@Body RequestBody params);

}