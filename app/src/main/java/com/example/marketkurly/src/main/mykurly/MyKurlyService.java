package com.example.marketkurly.src.main.mykurly;



import com.example.marketkurly.src.login.models.UserInfoResponse;
import com.example.marketkurly.src.main.mykurly.interfaces.MyKurlyActivityView;
import com.example.marketkurly.src.main.mykurly.interfaces.MyKurlyRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

class MyKurlyService {
    private final MyKurlyActivityView mMyKurlyActivityView;

    MyKurlyService(final MyKurlyActivityView MyKurlyActivityView) {
        this.mMyKurlyActivityView = MyKurlyActivityView;
    }

    void getUserInfo() {
        final MyKurlyRetrofitInterface MyKurlyRetrofitInterface = getRetrofit().create(MyKurlyRetrofitInterface.class);
        MyKurlyRetrofitInterface.getUserInfo().enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                final UserInfoResponse userInfoResponse = response.body();
                if (userInfoResponse == null) {
                    mMyKurlyActivityView.userInfoFailure(null);
                    return;
                }
                mMyKurlyActivityView.userInfoSuccess(userInfoResponse);
            }
            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mMyKurlyActivityView.userInfoFailure(null);
            }
        });
    }
}