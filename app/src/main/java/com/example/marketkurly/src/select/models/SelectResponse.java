
package com.example.marketkurly.src.select.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectResponse {

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

        @SerializedName("option")
        private List<Option> mOption;
        @SerializedName("profit")
        private float mProfit;

        public List<Option> getOption() {
            return mOption;
        }

        public void setOption(List<Option> option) {
            mOption = option;
        }

        public float getProfit() {
            return mProfit;
        }

        public void setProfit(float profit) {
            mProfit = profit;
        }

    }


    public static class Option {

        @SerializedName("clientPrice")
        private int mClientPrice;
        @SerializedName("isSoldOut")
        private String mIsSoldOut;
        @SerializedName("optionIdx")
        private int mOptionIdx;
        @SerializedName("optionName")
        private String mOptionName;
        @SerializedName("originalPrice")
        private int mOriginalPrice;
        @SerializedName("productIdx")
        private int mProductIdx;
        @SerializedName("productName")
        private String mProductName;

        public int getClientPrice() {
            return mClientPrice;
        }

        public void setClientPrice(int clientPrice) {
            mClientPrice = clientPrice;
        }

        public String getIsSoldOut() {
            return mIsSoldOut;
        }

        public void setIsSoldOut(String isSoldOut) {
            mIsSoldOut = isSoldOut;
        }

        public int getOptionIdx() {
            return mOptionIdx;
        }

        public void setOptionIdx(int optionIdx) {
            mOptionIdx = optionIdx;
        }

        public String getOptionName() {
            return mOptionName;
        }

        public void setOptionName(String optionName) {
            mOptionName = optionName;
        }

        public int getOriginalPrice() {
            return mOriginalPrice;
        }

        public void setOriginalPrice(int originalPrice) {
            mOriginalPrice = originalPrice;
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

    }
}
