package com.example.marketkurly.src.main.home.recommend.interfaces;

import com.example.marketkurly.src.main.home.recommend.models.HomeRecommendResponse;

public interface HomeRecommendActivityView {

    void homeRecommendSuccess(HomeRecommendResponse homeRecommendResponse);

    void homeRecommendFailure(String message);
}
