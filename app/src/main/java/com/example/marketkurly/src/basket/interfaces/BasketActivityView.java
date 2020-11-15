package com.example.marketkurly.src.basket.interfaces;

import com.example.marketkurly.src.basket.models.BasketChangeResponse;
import com.example.marketkurly.src.basket.models.BasketInfoResponse;
import com.example.marketkurly.src.basket.models.DeleteBasketItemResponse;

public interface BasketActivityView {

    void basketInfoSuccess(BasketInfoResponse basketInfoResponse);

    void basketInfoFailure(String message);

    void basketChangeSuccess(BasketChangeResponse basketChangeResponse);

    void deleteBasketItemSuccess(DeleteBasketItemResponse deleteBasketItemResponse);
}
