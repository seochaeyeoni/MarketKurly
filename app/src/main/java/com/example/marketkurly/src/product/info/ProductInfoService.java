package com.example.marketkurly.src.product.info;

import com.example.marketkurly.src.product.info.interfaces.ProductInfoActivityView;
import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.interfaces.ProductRetrofitInterface;
import com.example.marketkurly.src.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

public class ProductInfoService {
    private final ProductInfoActivityView mProductInfoActivityView;

    public ProductInfoService(final ProductInfoActivityView ProductInfoActivityView) {
        this.mProductInfoActivityView = ProductInfoActivityView;
    }

    public void getProduct(int productIdx) {
        final ProductRetrofitInterface ProductRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        ProductRetrofitInterface.getProduct(productIdx).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                if (productResponse == null) {
                    mProductInfoActivityView.productFailure(null);
                    return;
                }
                mProductInfoActivityView.productInfoSuccess(productResponse);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mProductInfoActivityView.productFailure(null);
            }
        });
    }
}