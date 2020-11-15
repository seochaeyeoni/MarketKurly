package com.example.marketkurly.src.main.recommend.Data;

import com.example.marketkurly.src.main.recommend.models.RecommendResponse;

import java.util.ArrayList;
import java.util.List;

public class GoodCommentData {

    private int tv_product_id;
    private String iv_product_url;
    private String tv_product_name;
    private String tv_product_price;
    private String tv_product_price_before;
    private String tv_sale_percent;
    private ArrayList reviewList;
    private ArrayList nameList;


    public GoodCommentData(int tv_product_id, String iv_product_url, String tv_product_name, String tv_product_price,
                           String tv_product_price_before, String tv_sale_percent, ArrayList reviewList, ArrayList nameList) {
        this.tv_product_id = tv_product_id;
        this.iv_product_url = iv_product_url;
        this.tv_product_name = tv_product_name;
        this.tv_product_price = tv_product_price;
        this.tv_product_price_before = tv_product_price_before;
        this.tv_sale_percent = tv_sale_percent;
        this.reviewList = reviewList;
        this.nameList = nameList;
    }

    public int getTv_product_id() {
        return tv_product_id;
    }

    public void setTv_product_id(int tv_product_id) {
        this.tv_product_id = tv_product_id;
    }

    public String getIv_product_url() {
        return iv_product_url;
    }

    public void setIv_product_url(String iv_product_url) {
        this.iv_product_url = iv_product_url;
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

    public ArrayList getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList reviewList) {
        this.reviewList = reviewList;
    }

    public ArrayList getNameList() {
        return nameList;
    }

    public void setNameList(ArrayList nameList) {
        this.nameList = nameList;
    }
}

