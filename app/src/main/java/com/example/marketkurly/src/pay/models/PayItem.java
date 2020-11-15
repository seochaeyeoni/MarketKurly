package com.example.marketkurly.src.pay.models;

public class PayItem {
    int optionCount;
    int optionIdx;
    int productIdx;

    public PayItem(int optionCount, int optionIdx, int productIdx) {
        this.optionCount = optionCount;
        this.optionIdx = optionIdx;
        this.productIdx = productIdx;
    }

    public int getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }

    public int getOptionIdx() {
        return optionIdx;
    }

    public void setOptionIdx(int optionIdx) {
        this.optionIdx = optionIdx;
    }

    public int getProductIdx() {
        return productIdx;
    }

    public void setProductIdx(int productIdx) {
        this.productIdx = productIdx;
    }
}
