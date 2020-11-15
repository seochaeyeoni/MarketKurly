
package com.example.marketkurly.src.login.models;

import com.google.gson.annotations.SerializedName;


public class UserInfoResponse {

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
        @SerializedName("couponCount")
        private String mCouponCount;
        @SerializedName("level")
        private String mLevel;
        @SerializedName("point")
        private String mPoint;
        @SerializedName("profit")
        private float mProfit;
        @SerializedName("userName")
        private String mUserName;
    
        public int getBasketCount() {
            return mBasketCount;
        }
    
        public void setBasketCount(int basketCount) {
            mBasketCount = basketCount;
        }
    
        public String getCouponCount() {
            return mCouponCount;
        }
    
        public void setCouponCount(String couponCount) {
            mCouponCount = couponCount;
        }
    
        public String getLevel() {
            return mLevel;
        }
    
        public void setLevel(String level) {
            mLevel = level;
        }
    
        public String getPoint() {
            return mPoint;
        }
    
        public void setPoint(String point) {
            mPoint = point;
        }
    
        public float getProfit() {
            return mProfit;
        }
    
        public void setProfit(float profit) {
            mProfit = profit;
        }
    
        public String getUserName() {
            return mUserName;
        }
    
        public void setUserName(String userName) {
            mUserName = userName;
        }
    
    }
}
