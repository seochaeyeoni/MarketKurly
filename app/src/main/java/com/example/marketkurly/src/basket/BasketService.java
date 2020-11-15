package com.example.marketkurly.src.basket;



import com.example.marketkurly.src.basket.interfaces.BasketActivityView;
import com.example.marketkurly.src.basket.interfaces.BasketRetrofitInterface;
import com.example.marketkurly.src.basket.models.BasketChangeBody;
import com.example.marketkurly.src.basket.models.BasketChangeResponse;
import com.example.marketkurly.src.basket.models.DeleteBasketItemResponse;
import com.example.marketkurly.src.basket.models.BasketInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

class BasketService {
    private final BasketActivityView mBasketActivityView;

    BasketService(final BasketActivityView basketActivityView) {
        this.mBasketActivityView = basketActivityView;
    }

    void getBasketInfo() {
        final BasketRetrofitInterface BasketRetrofitInterface = getRetrofit().create(BasketRetrofitInterface.class);
        BasketRetrofitInterface.getBasketInfo().enqueue(new Callback<BasketInfoResponse>() {
            @Override
            public void onResponse(Call<BasketInfoResponse> call, Response<BasketInfoResponse> response) {
                final BasketInfoResponse basketInfoResponse = response.body();
                if (basketInfoResponse == null) {
                    mBasketActivityView.basketInfoFailure(null);
                    return;
                }
                mBasketActivityView.basketInfoSuccess(basketInfoResponse);
            }

            @Override
            public void onFailure(Call<BasketInfoResponse> call, Throwable t) {
                mBasketActivityView.basketInfoFailure(null);
            }
        });
    }

    void patchBasketChange(BasketChangeBody basketChangeBody) {
        final BasketRetrofitInterface basketRetrofitInterface = getRetrofit().create(BasketRetrofitInterface.class);
        basketRetrofitInterface.patchBasketChange(basketChangeBody).enqueue(new Callback<BasketChangeResponse>() {
            @Override
            public void onResponse(Call<BasketChangeResponse> call, Response<BasketChangeResponse> response) {
                final BasketChangeResponse basketChangeResponse = response.body();
                if (basketChangeResponse == null) {
                    mBasketActivityView.basketInfoFailure(null);
                    return;
                }

                mBasketActivityView.basketChangeSuccess(basketChangeResponse);
            }

            @Override
            public void onFailure(Call<BasketChangeResponse> call, Throwable t) {
                mBasketActivityView.basketInfoFailure(null);
            }
        });
    }

    void deleteBasketItem(String options) {
        final BasketRetrofitInterface basketRetrofitInterface = getRetrofit().create(BasketRetrofitInterface.class);
        basketRetrofitInterface.deleteBasketItem(options).enqueue(new Callback<DeleteBasketItemResponse>() {
            @Override
            public void onResponse(Call<DeleteBasketItemResponse> call, Response<DeleteBasketItemResponse> response) {
                final DeleteBasketItemResponse deleteBasketItemResponse = response.body();
                if (deleteBasketItemResponse == null) {
                    mBasketActivityView.basketInfoFailure(null);
                    return;
                }

                mBasketActivityView.deleteBasketItemSuccess(deleteBasketItemResponse);
            }

            @Override
            public void onFailure(Call<DeleteBasketItemResponse> call, Throwable t) {
                mBasketActivityView.basketInfoFailure(null);
            }
        });
    }
}