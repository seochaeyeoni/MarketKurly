package com.example.marketkurly.src.pay.interfaces;

import com.example.marketkurly.src.main.models.DefaultResponse;
import com.example.marketkurly.src.pay.models.CouponBody;
import com.example.marketkurly.src.pay.models.CouponResponse;
import com.example.marketkurly.src.pay.models.PayBody;
import com.example.marketkurly.src.pay.models.PayResponse;
import com.example.marketkurly.src.pay.models.PayScreenBody;
import com.example.marketkurly.src.pay.models.PayScreenResponse;
import com.example.marketkurly.src.signup.models.SignUpResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PayRetrofitInterface {
    //    @GET("/test")
//    @GET("/jwt")
//    Call<DefaultResponse> getTest();
//
//    @GET("/test/{number}")
//    Call<DefaultResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );

    @POST("/order-form")
    Call<PayScreenResponse> postPayScreen(@Body PayScreenBody params);

    @POST("/order-form/coupon")
    Call<CouponResponse> postCoupon(@Body CouponBody params);

    @POST("/order")
    Call<PayResponse> postPay(@Body PayBody params);


}