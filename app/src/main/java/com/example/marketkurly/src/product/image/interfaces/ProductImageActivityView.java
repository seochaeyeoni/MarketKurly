package com.example.marketkurly.src.product.image.interfaces;

import com.example.marketkurly.src.product.models.ProductResponse;

public interface ProductImageActivityView {

    void productFailure(String message);

    void productImageSuccess(ProductResponse productResponse);

}
