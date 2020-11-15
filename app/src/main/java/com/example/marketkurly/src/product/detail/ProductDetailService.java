package com.example.marketkurly.src.product.detail;

import com.example.marketkurly.src.product.detail.interfaces.ProductDetailActivityView;
import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.interfaces.ProductRetrofitInterface;
import com.example.marketkurly.src.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

public class ProductDetailService {
    private final ProductDetailActivityView mProductDetailActivityView;

    public ProductDetailService(final ProductDetailActivityView ProductDetailActivityView) {
        this.mProductDetailActivityView = ProductDetailActivityView;
    }

    public void getProduct(int productIdx) {
        final ProductRetrofitInterface ProductRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        ProductRetrofitInterface.getProduct(productIdx).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                if (productResponse == null) {
                    mProductDetailActivityView.productFailure(null);
                    return;
                }
                mProductDetailActivityView.productDetailSuccess(productResponse);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mProductDetailActivityView.productFailure(null);
            }
        });
    }
}