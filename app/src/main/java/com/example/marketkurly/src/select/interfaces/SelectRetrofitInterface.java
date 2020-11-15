package com.example.marketkurly.src.select.interfaces;

import com.example.marketkurly.src.select.models.BasketAddBody;
import com.example.marketkurly.src.select.models.BasketAddResponse;
import com.example.marketkurly.src.select.models.SelectResponse;
import com.example.marketkurly.src.signup.models.SignUpBody;
import com.example.marketkurly.src.signup.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SelectRetrofitInterface {

    @GET("product/{productIdx}/option")
    Call<SelectResponse> getSelect(
            @Path("productIdx") int productIdx
    );

    @POST("basket")
    Call<BasketAddResponse> postBasketAdd(@Body BasketAddBody basketAddBody);
}