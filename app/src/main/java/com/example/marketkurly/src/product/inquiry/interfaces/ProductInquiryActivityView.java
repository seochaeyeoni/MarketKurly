package com.example.marketkurly.src.product.inquiry.interfaces;

import com.example.marketkurly.src.product.models.ProductResponse;

public interface ProductInquiryActivityView {

    void productFailure(String message);

    void productInquirySuccess(ProductResponse productResponse);
}
