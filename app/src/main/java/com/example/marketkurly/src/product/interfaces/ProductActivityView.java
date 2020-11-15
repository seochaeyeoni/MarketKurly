package com.example.marketkurly.src.product.interfaces;

import com.example.marketkurly.src.product.models.ProductResponse;

public interface ProductActivityView {

    void productSuccess(ProductResponse productResponse);

    void productFailure(String message);

    void productInfoSuccess(ProductResponse productResponse);

    void productImageSuccess(ProductResponse productResponse);

    void productDetailSuccess(ProductResponse productResponse);

    void productReviewSuccess(ProductResponse productResponse);

    void productInquirySuccess(ProductResponse productResponse);
}
