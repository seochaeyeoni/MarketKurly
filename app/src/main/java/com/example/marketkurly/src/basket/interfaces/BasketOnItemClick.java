package com.example.marketkurly.src.basket.interfaces;

import java.util.ArrayList;

public interface BasketOnItemClick {
    void onClickCold(int total_price_cold, int total_price_before_cold);
    void onClickFrozen(int total_price_frozen, int total_price_before_frozen);
    void onClickNormal(int total_price_normal, int total_price_before_normal);
    void onColdDelivery(ArrayList arrayList);
    void onFrozenDelivery(ArrayList arrayList);
    void onNormalDelivery(ArrayList arrayList);
    void onProductCountDelivery(int delta_product_count);
}
