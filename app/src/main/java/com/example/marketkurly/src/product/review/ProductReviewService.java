package com.example.marketkurly.src.product.review;

import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.interfaces.ProductRetrofitInterface;
import com.example.marketkurly.src.product.models.ProductResponse;
import com.example.marketkurly.src.product.review.interfaces.ProductReviewActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

public class ProductReviewService {
    private final ProductReviewActivityView mProductReviewActivityView;

    public ProductReviewService(final ProductReviewActivityView ProductReviewActivityView) {
        this.mProductReviewActivityView = ProductReviewActivityView;
    }

    public void getProduct(int productIdx) {
        final ProductRetrofitInterface ProductRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        ProductRetrofitInterface.getProduct(productIdx).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                if (productResponse == null) {
                    mProductReviewActivityView.productFailure(null);
                    return;
                }
                mProductReviewActivityView.productReviewSuccess(productResponse);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mProductReviewActivityView.productFailure(null);
            }
        });
    }
}