package com.example.marketkurly.src.main.recommend;

import com.example.marketkurly.src.main.interfaces.MainActivityView;
import com.example.marketkurly.src.main.interfaces.MainRetrofitInterface;
import com.example.marketkurly.src.main.models.DefaultResponse;
import com.example.marketkurly.src.main.recommend.interfaces.RecommendActivityView;
import com.example.marketkurly.src.main.recommend.interfaces.RecommendRetrofitInterface;
import com.example.marketkurly.src.main.recommend.models.RecommendResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

class RecommendService {
    private final RecommendActivityView mRecommendActivityView;

    RecommendService(final RecommendActivityView recommendActivityView) {
        this.mRecommendActivityView = recommendActivityView;
    }

    void getRecommend() {
        final RecommendRetrofitInterface recommendRetrofitInterface = getRetrofit().create(RecommendRetrofitInterface.class);
        recommendRetrofitInterface.getRecommend().enqueue(new Callback<RecommendResponse>() {
            @Override
            public void onResponse(Call<RecommendResponse> call, Response<RecommendResponse> response) {
                final RecommendResponse recommendResponse = response.body();
                if (recommendResponse == null) {
                    mRecommendActivityView.recommendFailure(null);
                    return;
                }

                mRecommendActivityView.recommendSuccess(recommendResponse);
            }

            @Override
            public void onFailure(Call<RecommendResponse> call, Throwable t) {
                mRecommendActivityView.recommendFailure(null);
            }
        });
    }
}