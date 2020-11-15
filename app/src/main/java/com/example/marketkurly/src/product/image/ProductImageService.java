package com.example.marketkurly.src.product.image;

import com.example.marketkurly.src.product.image.interfaces.ProductImageActivityView;
import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.interfaces.ProductRetrofitInterface;
import com.example.marketkurly.src.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

public class ProductImageService {
    private final ProductImageActivityView mProductImageActivityView;

    public ProductImageService(final ProductImageActivityView ProductImageActivityView) {
        this.mProductImageActivityView = ProductImageActivityView;
    }

    public void getProduct(int productIdx) {
        final ProductRetrofitInterface ProductRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        ProductRetrofitInterface.getProduct(productIdx).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                if (productResponse == null) {
                    mProductImageActivityView.productFailure(null);
                    return;
                }
                mProductImageActivityView.productImageSuccess(productResponse);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mProductImageActivityView.productFailure(null);
            }
        });
    }
}