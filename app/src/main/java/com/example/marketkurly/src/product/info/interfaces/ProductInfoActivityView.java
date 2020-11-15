package com.example.marketkurly.src.product.info.interfaces;

import com.example.marketkurly.src.product.models.ProductResponse;

public interface ProductInfoActivityView {

    void productFailure(String message);

    void productInfoSuccess(ProductResponse productResponse);

}
