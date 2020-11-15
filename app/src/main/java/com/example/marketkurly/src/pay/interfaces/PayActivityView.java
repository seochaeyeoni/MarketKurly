package com.example.marketkurly.src.pay.interfaces;

import com.example.marketkurly.src.pay.models.CouponResponse;
import com.example.marketkurly.src.pay.models.PayResponse;
import com.example.marketkurly.src.pay.models.PayScreenResponse;

public interface PayActivityView {

    void payScreenSuccess(PayScreenResponse payScreenResponse);

    void payScreenFailure(String message);

    void couponSuccess(CouponResponse couponResponse);

    void paySuccess(PayResponse payResponse);
}
