package com.example.marketkurly.src.main.recommend.interfaces;

import com.example.marketkurly.src.main.models.DefaultResponse;
import com.example.marketkurly.src.main.recommend.models.RecommendResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecommendRetrofitInterface {

    @GET("recommend")
    Call<RecommendResponse> getRecommend();

}