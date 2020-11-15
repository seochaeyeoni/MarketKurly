package com.example.marketkurly.src.pay;

import com.example.marketkurly.src.pay.interfaces.PayActivityView;
import com.example.marketkurly.src.pay.interfaces.PayRetrofitInterface;
import com.example.marketkurly.src.pay.models.CouponBody;
import com.example.marketkurly.src.pay.models.CouponResponse;
import com.example.marketkurly.src.pay.models.PayBody;
import com.example.marketkurly.src.pay.models.PayResponse;
import com.example.marketkurly.src.pay.models.PayScreenBody;
import com.example.marketkurly.src.pay.models.PayScreenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

class PayService {
    private final PayActivityView mPayActivityView;

    PayService(final PayActivityView payActivityView) {
        this.mPayActivityView = payActivityView;
    }

    void postPayScreen(PayScreenBody payScreenBody) {
        final PayRetrofitInterface payRetrofitInterface = getRetrofit().create(PayRetrofitInterface.class);
        payRetrofitInterface.postPayScreen(payScreenBody).enqueue(new Callback<PayScreenResponse>() {
            @Override
            public void onResponse(Call<PayScreenResponse> call, Response<PayScreenResponse> response) {
                final PayScreenResponse payScreenResponse = response.body();
                if (payScreenResponse == null) {
                    mPayActivityView.payScreenFailure(null);
                    return;
                }

                mPayActivityView.payScreenSuccess(payScreenResponse);
            }

            @Override
            public void onFailure(Call<PayScreenResponse> call, Throwable t) {
                mPayActivityView.payScreenFailure(null);
            }
        });
    }

    void postCoupon(CouponBody couponBody) {
        final PayRetrofitInterface payRetrofitInterface = getRetrofit().create(PayRetrofitInterface.class);
        payRetrofitInterface.postCoupon(couponBody).enqueue(new Callback<CouponResponse>() {
            @Override
            public void onResponse(Call<CouponResponse> call, Response<CouponResponse> response) {
                final CouponResponse couponResponse = response.body();
                if (couponResponse == null) {
                    mPayActivityView.payScreenFailure(null);
                    return;
                }
                mPayActivityView.couponSuccess(couponResponse);
            }

            @Override
            public void onFailure(Call<CouponResponse> call, Throwable t) {
                mPayActivityView.payScreenFailure(null);
            }
        });
    }

    void postPay(PayBody payBody) {
        final PayRetrofitInterface payRetrofitInterface = getRetrofit().create(PayRetrofitInterface.class);
        payRetrofitInterface.postPay(payBody).enqueue(new Callback<PayResponse>() {
            @Override
            public void onResponse(Call<PayResponse> call, Response<PayResponse> response) {
                final PayResponse payResponse = response.body();
                if (payResponse == null) {
                    mPayActivityView.payScreenFailure(null);
                    return;
                }
                mPayActivityView.paySuccess(payResponse);
            }

            @Override
            public void onFailure(Call<PayResponse> call, Throwable t) {
                mPayActivityView.payScreenFailure(null);
            }
        });
    }
}