package com.example.marketkurly.src.login;

import com.example.marketkurly.src.login.interfaces.LoginActivityView;
import com.example.marketkurly.src.login.interfaces.LoginRetrofitInterface;
import com.example.marketkurly.src.login.models.LoginBody;
import com.example.marketkurly.src.login.models.LoginResponse;
import com.example.marketkurly.src.login.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postLogin(LoginBody loginBody) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postLogin(loginBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mLoginActivityView.loginFailure(null);
                    return;
                }

                mLoginActivityView.loginSuccess(loginResponse);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.loginFailure(null);
            }
        });
    }

    void getUserInfo() {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.getUserInfo().enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                final UserInfoResponse userInfoResponse = response.body();
                if (userInfoResponse == null) {
                    mLoginActivityView.userInfoFailure(null);
                    return;
                }
                mLoginActivityView.userInfoSuccess(userInfoResponse);
            }
            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mLoginActivityView.userInfoFailure(null);
            }
        });
    }
}