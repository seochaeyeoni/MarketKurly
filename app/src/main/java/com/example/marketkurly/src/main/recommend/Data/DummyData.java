package com.example.marketkurly.src.main.recommend.Data;

public class DummyData {

    private int iv_product;
    private String tv_product_name;
    private String tv_product_price;
    private String tv_product_price_before;
    private String tv_sale_percent;


    public DummyData(int iv_product, String tv_product_name, String tv_product_price,
                     String tv_product_price_before, String tv_sale_percent) {
        this.iv_product = iv_product;
        this.tv_product_name = tv_product_name;
        this.tv_product_price = tv_product_price;
        this.tv_product_price_before = tv_product_price_before;
        this.tv_sale_percent = tv_sale_percent;
    }

    public int getIv_product() {
        return iv_product;
    }

    public void setIv_product(int iv_product) {
        this.iv_product = iv_product;
    }

    public String getTv_product_name() {
        return tv_product_name;
    }

    public void setTv_product_name(String tv_product_name) {
        this.tv_product_name = tv_product_name;
    }

    public String getTv_product_price() {
        return tv_product_price;
    }

    public void setTv_product_price(String tv_product_price) {
        this.tv_product_price = tv_product_price;
    }

    public String getTv_product_price_before() {
        return tv_product_price_before;
    }

    public void setTv_product_price_before(String tv_product_price_before) {
        this.tv_product_price_before = tv_product_price_before;
    }

    public String getTv_sale_percent() {
        return tv_sale_percent;
    }

    public void setTv_sale_percent(String tv_sale_percent) {
        this.tv_sale_percent = tv_sale_percent;
    }
}

