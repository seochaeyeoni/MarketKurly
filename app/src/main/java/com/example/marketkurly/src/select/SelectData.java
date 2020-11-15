package com.example.marketkurly.src.select;

public class SelectData {

    private int tv_product_id;
    private int tv_option_id;
    private int tv_price_before;
    private int tv_price;
    private int count;
    private String tv_option_name;
    private String tv_soldout;

    public SelectData(int tv_product_id, int tv_option_id, int tv_price_before, int tv_price, int count, String tv_option_name, String tv_soldout) {
        this.tv_product_id = tv_product_id;
        this.tv_option_id = tv_option_id;
        this.tv_price_before = tv_price_before;
        this.tv_price = tv_price;
        this.count = count;
        this.tv_option_name = tv_option_name;
        this.tv_soldout = tv_soldout;
    }

    public int getTv_product_id() {
        return tv_product_id;
    }

    public void setTv_product_id(int tv_product_id) {
        this.tv_product_id = tv_product_id;
    }

    public int getTv_option_id() {
        return tv_option_id;
    }

    public void setTv_option_id(int tv_option_id) {
        this.tv_option_id = tv_option_id;
    }

    public int getTv_price_before() {
        return tv_price_before;
    }

    public void setTv_price_before(int tv_price_before) {
        this.tv_price_before = tv_price_before;
    }

    public int getTv_price() {
        return tv_price;
    }

    public void setTv_price(int tv_price) {
        this.tv_price = tv_price;
    }


    public String getTv_option_name() {
        return tv_option_name;
    }

    public void setTv_option_name(String tv_option_name) {
        this.tv_option_name = tv_option_name;
    }

    public String getTv_soldout() {
        return tv_soldout;
    }

    public void setTv_soldout(String tv_soldout) {
        this.tv_soldout = tv_soldout;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

