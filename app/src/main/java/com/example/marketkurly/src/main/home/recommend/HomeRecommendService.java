package com.example.marketkurly.src.main.home.recommend;

import com.example.marketkurly.src.main.home.recommend.interfaces.HomeRecommendActivityView;
import com.example.marketkurly.src.main.home.recommend.interfaces.HomeRecommendRetrofitInterface;
import com.example.marketkurly.src.main.home.recommend.models.HomeRecommendResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

class HomeRecommendService {
    private final HomeRecommendActivityView mHomeRecommendActivityView;

    HomeRecommendService(final HomeRecommendActivityView homeRecommendActivityView) {
        this.mHomeRecommendActivityView = homeRecommendActivityView;
    }

    void postHomeRecommend() {
        final HomeRecommendRetrofitInterface homeRecommendRetrofitInterface = getRetrofit().create(HomeRecommendRetrofitInterface.class);
        homeRecommendRetrofitInterface.getHomeRecommend().enqueue(new Callback<HomeRecommendResponse>() {
            @Override
            public void onResponse(Call<HomeRecommendResponse> call, Response<HomeRecommendResponse> response) {
                final HomeRecommendResponse homeRecommendResponse = response.body();
                if (homeRecommendResponse == null) {
                    mHomeRecommendActivityView.homeRecommendFailure(null);
                    return;
                }

                mHomeRecommendActivityView.homeRecommendSuccess(homeRecommendResponse);
            }

            @Override
            public void onFailure(Call<HomeRecommendResponse> call, Throwable t) {
                mHomeRecommendActivityView.homeRecommendFailure(null);
            }
        });
    }
}