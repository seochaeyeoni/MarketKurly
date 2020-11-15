package com.example.marketkurly.src.product.image.interfaces;

import com.example.marketkurly.src.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductImageRetrofitInterface {

    @GET("product/{productIdx}")
    Call<ProductResponse> getProduct(
            @Path("productIdx") int productIdx
    );
}
