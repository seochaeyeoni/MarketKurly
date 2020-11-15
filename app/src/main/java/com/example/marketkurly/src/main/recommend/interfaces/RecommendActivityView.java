package com.example.marketkurly.src.main.recommend.interfaces;

import com.example.marketkurly.src.main.recommend.models.RecommendResponse;

public interface RecommendActivityView {

    void recommendSuccess(RecommendResponse recommendResponse);

    void recommendFailure(String message);
}
