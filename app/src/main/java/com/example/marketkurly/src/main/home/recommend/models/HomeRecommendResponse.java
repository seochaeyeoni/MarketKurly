
package com.example.marketkurly.src.main.home.recommend.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeRecommendResponse {

    @SerializedName("code")
    private int mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private Result mResult;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public Boolean getIsSuccess() {
        return mIsSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        mIsSuccess = isSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        mResult = result;
    }


    public static class Result {

        @SerializedName("basketCount")
        private int mBasketCount;
        @SerializedName("recommend")
        private List<Recommend> mRecommend;
        @SerializedName("sale")
        private List<Sale> mSale;

        public int getBasketCount() {
            return mBasketCount;
        }

        public void setBasketCount(int basketCount) {
            mBasketCount = basketCount;
        }

        public List<Recommend> getRecommend() {
            return mRecommend;
        }

        public void setRecommend(List<Recommend> recommend) {
            mRecommend = recommend;
        }

        public List<Sale> getSale() {
            return mSale;
        }

        public void setSale(List<Sale> sale) {
            mSale = sale;
        }

    }

    public static class Recommend {

        @SerializedName("clientPrice")
        private String mClientPrice;
        @SerializedName("originalPrice")
        private String mOriginalPrice;
        @SerializedName("pictureUrl")
        private String mPictureUrl;
        @SerializedName("productIdx")
        private int mProductIdx;
        @SerializedName("productName")
        private String mProductName;
        @SerializedName("salePercent")
        private int mSalePercent;

        public String getClientPrice() {
            return mClientPrice;
        }

        public void setClientPrice(String clientPrice) {
            mClientPrice = clientPrice;
        }

        public String getOriginalPrice() {
            return mOriginalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            mOriginalPrice = originalPrice;
        }

        public String getPictureUrl() {
            return mPictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            mPictureUrl = pictureUrl;
        }

        public int getProductIdx() {
            return mProductIdx;
        }

        public void setProductIdx(int productIdx) {
            mProductIdx = productIdx;
        }

        public String getProductName() {
            return mProductName;
        }

        public void setProductName(String productName) {
            mProductName = productName;
        }

        public int getSalePercent() {
            return mSalePercent;
        }

        public void setSalePercent(int mSalePercent) {
            this.mSalePercent = mSalePercent;
        }
    }


    public static class Sale {

        @SerializedName("clientPrice")
        private String mClientPrice;
        @SerializedName("originalPrice")
        private String mOriginalPrice;
        @SerializedName("pictureUrl")
        private String mPictureUrl;
        @SerializedName("productIdx")
        private int mProductIdx;
        @SerializedName("productName")
        private String mProductName;
        @SerializedName("salePercent")
        private String mSalePercent;

        public String getClientPrice() {
            return mClientPrice;
        }

        public void setClientPrice(String clientPrice) {
            mClientPrice = clientPrice;
        }

        public String getOriginalPrice() {
            return mOriginalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            mOriginalPrice = originalPrice;
        }

        public String getPictureUrl() {
            return mPictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            mPictureUrl = pictureUrl;
        }

        public int getProductIdx() {
            return mProductIdx;
        }

        public void setProductIdx(int productIdx) {
            mProductIdx = productIdx;
        }

        public String getProductName() {
            return mProductName;
        }

        public void setProductName(String productName) {
            mProductName = productName;
        }

        public String getSalePercent() {
            return mSalePercent;
        }

        public void setSalePercent(String salePercent) {
            mSalePercent = salePercent;
        }

    }
}
