package com.example.marketkurly.src.product.inquiry;

import com.example.marketkurly.src.product.inquiry.interfaces.ProductInquiryActivityView;
import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.interfaces.ProductRetrofitInterface;
import com.example.marketkurly.src.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

public class ProductInquiryService {
    private final ProductInquiryActivityView mProductInquiryActivityView;

    public ProductInquiryService(final ProductInquiryActivityView ProductInquiryActivityView) {
        this.mProductInquiryActivityView = ProductInquiryActivityView;
    }

    public void getProduct(int productIdx) {
        final ProductRetrofitInterface ProductRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        ProductRetrofitInterface.getProduct(productIdx).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                if (productResponse == null) {
                    mProductInquiryActivityView.productFailure(null);
                    return;
                }

                mProductInquiryActivityView.productInquirySuccess(productResponse);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mProductInquiryActivityView.productFailure(null);
            }
        });
    }
}