
package com.example.marketkurly.src.pay.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class CouponResponse {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private List<Result> mResult;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
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

    public List<Result> getResult() {
        return mResult;
    }

    public void setResult(List<Result> result) {
        mResult = result;
    }

    public static class Result {

        @SerializedName("contents")
        private String mContents;
        @SerializedName("couponIdx")
        private Long mCouponIdx;
        @SerializedName("couponName")
        private String mCouponName;
        @SerializedName("discount")
        private Long mDiscount;
        @SerializedName("expiration")
        private String mExpiration;
        @SerializedName("isAvailable")
        private String mIsAvailable;

        public Result(String contents, Long couponIdx, String couponName, Long discount,
                      String expiration, String isAvailable) {
            this.mContents = contents;
            this.mCouponIdx = couponIdx;
            this.mCouponName = couponName;
            this.mDiscount = discount;
            this.mExpiration = expiration;
            this.mIsAvailable = isAvailable;
        }

        public String getContents() {
            return mContents;
        }

        public void setContents(String contents) {
            mContents = contents;
        }

        public Long getCouponIdx() {
            return mCouponIdx;
        }

        public void setCouponIdx(Long couponIdx) {
            mCouponIdx = couponIdx;
        }

        public String getCouponName() {
            return mCouponName;
        }

        public void setCouponName(String couponName) {
            mCouponName = couponName;
        }

        public Long getDiscount() {
            return mDiscount;
        }

        public void setDiscount(Long discount) {
            mDiscount = discount;
        }

        public String getExpiration() {
            return mExpiration;
        }

        public void setExpiration(String expiration) {
            mExpiration = expiration;
        }

        public String getIsAvailable() {
            return mIsAvailable;
        }

        public void setIsAvailable(String isAvailable) {
            mIsAvailable = isAvailable;
        }

    }
}
