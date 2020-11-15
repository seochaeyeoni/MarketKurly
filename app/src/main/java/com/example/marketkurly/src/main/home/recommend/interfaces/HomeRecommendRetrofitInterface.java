package com.example.marketkurly.src.main.home.recommend.interfaces;

import com.example.marketkurly.src.main.home.recommend.models.HomeRecommendResponse;
import com.example.marketkurly.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HomeRecommendRetrofitInterface {

    @GET("home")
    Call<HomeRecommendResponse> getHomeRecommend();

}