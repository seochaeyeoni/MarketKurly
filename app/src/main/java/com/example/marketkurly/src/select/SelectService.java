package com.example.marketkurly.src.select;


import com.example.marketkurly.src.select.interfaces.SelectActivityView;
import com.example.marketkurly.src.select.interfaces.SelectRetrofitInterface;
import com.example.marketkurly.src.select.models.BasketAddBody;
import com.example.marketkurly.src.select.models.BasketAddResponse;
import com.example.marketkurly.src.select.models.SelectResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

class SelectService {
    private final SelectActivityView mSelectActivityView;

    SelectService(final SelectActivityView SelectActivityView) {
        this.mSelectActivityView = SelectActivityView;
    }

    void getSelect(int productIdx) {
        final SelectRetrofitInterface selectRetrofitInterface = getRetrofit().create(SelectRetrofitInterface.class);
        selectRetrofitInterface.getSelect(productIdx).enqueue(new Callback<SelectResponse>() {
            @Override
            public void onResponse(Call<SelectResponse> call, Response<SelectResponse> response) {
                final SelectResponse selectResponse = response.body();
                if (selectResponse == null) {
                    mSelectActivityView.selectFailure(null);
                    return;
                }

                mSelectActivityView.selectSuccess(selectResponse);
            }

            @Override
            public void onFailure(Call<SelectResponse> call, Throwable t) {
                mSelectActivityView.selectFailure(null);
            }
        });
    }

    void postBasketAdd(BasketAddBody BasketAddBody) {
        final SelectRetrofitInterface selectRetrofitInterface = getRetrofit().create(SelectRetrofitInterface.class);
        selectRetrofitInterface.postBasketAdd(BasketAddBody).enqueue(new Callback<BasketAddResponse>() {
            @Override
            public void onResponse(Call<BasketAddResponse> call, Response<BasketAddResponse> response) {
                final BasketAddResponse BasketAddResponse = response.body();
                if (BasketAddResponse == null) {
                    mSelectActivityView.selectFailure(null);
                    return;
                }

                mSelectActivityView.basketAddSuccess(BasketAddResponse);
            }

            @Override
            public void onFailure(Call<BasketAddResponse> call, Throwable t) {
                mSelectActivityView.selectFailure(null);
            }
        });
    }
}