
package com.example.marketkurly.src.product.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ProductResponse {

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

        @SerializedName("inquiry")
        private List<Inquiry> mInquiry;
        @SerializedName("productDetail")
        private String mProductDetail;
        @SerializedName("productImg")
        private String mProductImg;
        @SerializedName("productInfo")
        private ProductInfo mProductInfo;
        @SerializedName("review")
        private List<Review> mReview;
        @SerializedName("reviewCount")
        private int mReviewCount;
        @SerializedName("userInfo")
        private UserInfo mUserInfo;

        public List<Inquiry> getInquiry() {
            return mInquiry;
        }

        public void setInquiry(List<Inquiry> inquiry) {
            mInquiry = inquiry;
        }

        public String getProductDetail() {
            return mProductDetail;
        }

        public void setProductDetail(String productDetail) {
            mProductDetail = productDetail;
        }

        public String getProductImg() {
            return mProductImg;
        }

        public void setProductImg(String productImg) {
            mProductImg = productImg;
        }

        public ProductInfo getProductInfo() {
            return mProductInfo;
        }

        public void setProductInfo(ProductInfo productInfo) {
            mProductInfo = productInfo;
        }

        public List<Review> getReview() {
            return mReview;
        }

        public void setReview(List<Review> review) {
            mReview = review;
        }

        public int getReviewCount() {
            return mReviewCount;
        }

        public void setReviewCount(int reviewCount) {
            mReviewCount = reviewCount;
        }

        public UserInfo getUserInfo() {
            return mUserInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            mUserInfo = userInfo;
        }

    }


    public static class ProductInfo {

        @SerializedName("allergy")
        private String mAllergy;
        @SerializedName("calories")
        private String mCalories;
        @SerializedName("clientPrice")
        private String mClientPrice;
        @SerializedName("expiration")
        private String mExpiration;
        @SerializedName("explanationPic")
        private String mExplanationPic;
        @SerializedName("guidance")
        private String mGuidance;
        @SerializedName("mainPic")
        private String mMainPic;
        @SerializedName("origin")
        private String mOrigin;
        @SerializedName("originalPrice")
        private String mOriginalPrice;
        @SerializedName("packingType")
        private String mPackingType;
        @SerializedName("productComment")
        private String mProductComment;
        @SerializedName("productIdx")
        private int mProductIdx;
        @SerializedName("productName")
        private String mProductName;
        @SerializedName("recordInfo")
        private String mRecordInfo;
        @SerializedName("salePercent")
        private int mSalePercent;
        @SerializedName("salesUnit")
        private String mSalesUnit;
        @SerializedName("shipping")
        private String mShipping;
        @SerializedName("weight")
        private String mWeight;

        public String getAllergy() {
            return mAllergy;
        }

        public void setAllergy(String allergy) {
            mAllergy = allergy;
        }

        public String getCalories() {
            return mCalories;
        }

        public void setCalories(String calories) {
            mCalories = calories;
        }

        public String getClientPrice() {
            return mClientPrice;
        }

        public void setClientPrice(String clientPrice) {
            mClientPrice = clientPrice;
        }

        public String getExpiration() {
            return mExpiration;
        }

        public void setExpiration(String expiration) {
            mExpiration = expiration;
        }

        public String getExplanationPic() {
            return mExplanationPic;
        }

        public void setExplanationPic(String explanationPic) {
            mExplanationPic = explanationPic;
        }

        public String getGuidance() {
            return mGuidance;
        }

        public void setGuidance(String guidance) {
            mGuidance = guidance;
        }

        public String getMainPic() {
            return mMainPic;
        }

        public void setMainPic(String mainPic) {
            mMainPic = mainPic;
        }

        public String getOrigin() {
            return mOrigin;
        }

        public void setOrigin(String origin) {
            mOrigin = origin;
        }

        public String getOriginalPrice() {
            return mOriginalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            mOriginalPrice = originalPrice;
        }

        public String getPackingType() {
            return mPackingType;
        }

        public void setPackingType(String packingType) {
            mPackingType = packingType;
        }

        public String getProductComment() {
            return mProductComment;
        }

        public void setProductComment(String productComment) {
            mProductComment = productComment;
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

        public String getRecordInfo() {
            return mRecordInfo;
        }

        public void setRecordInfo(String recordInfo) {
            mRecordInfo = recordInfo;
        }

        public int getSalePercent() {
            return mSalePercent;
        }

        public void setSalePercent(int salePercent) {
            mSalePercent = salePercent;
        }

        public String getSalesUnit() {
            return mSalesUnit;
        }

        public void setSalesUnit(String salesUnit) {
            mSalesUnit = salesUnit;
        }

        public String getShipping() {
            return mShipping;
        }

        public void setShipping(String shipping) {
            mShipping = shipping;
        }

        public String getWeight() {
            return mWeight;
        }

        public void setWeight(String weight) {
            mWeight = weight;
        }

    }

    public static class Inquiry {

        @SerializedName("createdAt")
        private String mCreatedAt;
        @SerializedName("inquiryTitle")
        private String mInquiryTitle;
        @SerializedName("isAnswered")
        private String mIsAnswered;
        @SerializedName("isLocked")
        private String mIsLocked;
        @SerializedName("userName")
        private String mUserName;

        public String getCreatedAt() {
            return mCreatedAt;
        }

        public void setCreatedAt(String createdAt) {
            mCreatedAt = createdAt;
        }

        public String getInquiryTitle() {
            return mInquiryTitle;
        }

        public void setInquiryTitle(String inquiryTitle) {
            mInquiryTitle = inquiryTitle;
        }

        public String getIsAnswered() {
            return mIsAnswered;
        }

        public void setIsAnswered(String isAnswered) {
            mIsAnswered = isAnswered;
        }

        public String getIsLocked() {
            return mIsLocked;
        }

        public void setIsLocked(String isLocked) {
            mIsLocked = isLocked;
        }

        public String getUserName() {
            return mUserName;
        }

        public void setUserName(String userName) {
            mUserName = userName;
        }

    }


    public static class Review {

        @SerializedName("createdAt")
        private String mCreatedAt;
        @SerializedName("isBest")
        private String mIsBest;
        @SerializedName("isPic")
        private int mIsPic;
        @SerializedName("level")
        private String mLevel;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("userName")
        private String mUserName;

        public String getCreatedAt() {
            return mCreatedAt;
        }

        public void setCreatedAt(String createdAt) {
            mCreatedAt = createdAt;
        }

        public String getIsBest() {
            return mIsBest;
        }

        public void setIsBest(String isBest) {
            mIsBest = isBest;
        }

        public int getIsPic() {
            return mIsPic;
        }

        public void setIsPic(int isPic) {
            mIsPic = isPic;
        }

        public String getLevel() {
            return mLevel;
        }

        public void setLevel(String level) {
            mLevel = level;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getUserName() {
            return mUserName;
        }

        public void setUserName(String userName) {
            mUserName = userName;
        }

    }


    public static class UserInfo {

        @SerializedName("level")
        private String mLevel;
        @SerializedName("profit")
        private float mProfit;
        @SerializedName("profitPrice")
        private String mProfitPrice;

        public String getLevel() {
            return mLevel;
        }

        public void setLevel(String level) {
            mLevel = level;
        }

        public float getProfit() {
            return mProfit;
        }

        public void setProfit(float profit) {
            mProfit = profit;
        }

        public String getProfitPrice() {
            return mProfitPrice;
        }

        public void setProfitPrice(String profitPrice) {
            mProfitPrice = profitPrice;
        }

    }
}
