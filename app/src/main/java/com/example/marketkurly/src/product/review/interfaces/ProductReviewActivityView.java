package com.example.marketkurly.src.product.review.interfaces;

import com.example.marketkurly.src.product.models.ProductResponse;

public interface ProductReviewActivityView {

    void productFailure(String message);

    void productReviewSuccess(ProductResponse productResponse);
}
