package com.example.marketkurly.src.product.interfaces;

import com.example.marketkurly.src.main.models.DefaultResponse;
import com.example.marketkurly.src.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductRetrofitInterface {

    @GET("product/{productIdx}")
    Call<ProductResponse> getProduct(
            @Path("productIdx") int productIdx
    );
}
