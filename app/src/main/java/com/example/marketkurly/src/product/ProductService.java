package com.example.marketkurly.src.product;

import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.interfaces.ProductRetrofitInterface;
import com.example.marketkurly.src.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

public class ProductService {
    private final ProductActivityView mProductActivityView;

    public ProductService(final ProductActivityView ProductActivityView) {
        this.mProductActivityView = ProductActivityView;
    }

    public void getProduct(int productIdx) {
        final ProductRetrofitInterface ProductRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        ProductRetrofitInterface.getProduct(productIdx).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                if (productResponse == null) {
                    mProductActivityView.productFailure(null);
                    return;
                }

                mProductActivityView.productSuccess(productResponse);
                mProductActivityView.productInfoSuccess(productResponse);

                mProductActivityView.productDetailSuccess(productResponse);
                mProductActivityView.productReviewSuccess(productResponse);
                mProductActivityView.productInquirySuccess(productResponse);
                mProductActivityView.productImageSuccess(productResponse);


            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mProductActivityView.productFailure(null);
            }
        });
    }
}