
package com.example.marketkurly.src.main.recommend.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecommendResponse {

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

    public static class BestComment {

        @SerializedName("name")
        private String mName;
        @SerializedName("productIdx")
        private int mProductIdx;
        @SerializedName("review")
        private String mReview;

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public int getProductIdx() {
            return mProductIdx;
        }

        public void setProductIdx(int productIdx) {
            mProductIdx = productIdx;
        }

        public String getReview() {
            return mReview;
        }

        public void setReview(String review) {
            mReview = review;
        }

    }

    public static class GoodComment {

        @SerializedName("bestComment")
        private ArrayList<BestComment> mBestComment;
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

        public ArrayList<BestComment> getBestComment() {
            return mBestComment;
        }

        public void setBestComment(ArrayList<BestComment> bestComment) {
            mBestComment = bestComment;
        }

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

        public void setSalePercent(int salePercent) {
            mSalePercent = salePercent;
        }

    }


    public static class Related {

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

        public void setSalePercent(int salePercent) {
            mSalePercent = salePercent;
        }

    }

    public static class Result {

        @SerializedName("basketCount")
        private int mBasketCount;
        @SerializedName("goodComment")
        private List<GoodComment> mGoodComment;
        @SerializedName("related")
        private List<Related> mRelated;
        @SerializedName("userName")
        private String mUserName;

        public int getBasketCount() {
            return mBasketCount;
        }

        public void setBasketCount(int basketCount) {
            mBasketCount = basketCount;
        }

        public List<GoodComment> getGoodComment() {
            return mGoodComment;
        }

        public void setGoodComment(List<GoodComment> goodComment) {
            mGoodComment = goodComment;
        }

        public List<Related> getRelated() {
            return mRelated;
        }

        public void setRelated(List<Related> related) {
            mRelated = related;
        }

        public String getUserName() {
            return mUserName;
        }

        public void setUserName(String userName) {
            mUserName = userName;
        }

    }
}
