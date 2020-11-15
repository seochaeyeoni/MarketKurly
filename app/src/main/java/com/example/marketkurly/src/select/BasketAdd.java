package com.example.marketkurly.src.select;

public class BasketAdd {
    int count;
    int optionIdx;
    int productIdx;

    public BasketAdd(int count, int optionIdx, int productIdx) {
        this.count = count;
        this.optionIdx = optionIdx;
        this.productIdx = productIdx;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
