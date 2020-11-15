package com.example.marketkurly.src.product.detail.interfaces;

import com.example.marketkurly.src.product.models.ProductResponse;

public interface ProductDetailActivityView {

    void productFailure(String message);

    void productDetailSuccess(ProductResponse productResponse);
}
