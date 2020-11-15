
package com.example.marketkurly.src.pay.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PayScreenResponse {

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

        @SerializedName("address")
        private Address mAddress;
        @SerializedName("couponAndProfit")
        private CouponAndProfit mCouponAndProfit;
        @SerializedName("orderInfo")
        private List<OrderInfo> mOrderInfo;
        @SerializedName("price")
        private Price mPrice;
        @SerializedName("receivePlace")
        private ReceivePlace mReceivePlace;
        @SerializedName("userInfo")
        private UserInfo mUserInfo;

        public Address getAddress() {
            return mAddress;
        }

        public void setAddress(Address address) {
            mAddress = address;
        }

        public CouponAndProfit getCouponAndProfit() {
            return mCouponAndProfit;
        }

        public void setCouponAndProfit(CouponAndProfit couponAndProfit) {
            mCouponAndProfit = couponAndProfit;
        }

        public List<OrderInfo> getOrderInfo() {
            return mOrderInfo;
        }

        public void setOrderInfo(List<OrderInfo> orderInfo) {
            mOrderInfo = orderInfo;
        }

        public Price getPrice() {
            return mPrice;
        }

        public void setPrice(Price price) {
            mPrice = price;
        }

        public ReceivePlace getReceivePlace() {
            return mReceivePlace;
        }

        public void setReceivePlace(ReceivePlace receivePlace) {
            mReceivePlace = receivePlace;
        }

        public UserInfo getUserInfo() {
            return mUserInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            mUserInfo = userInfo;
        }

    }


    public static class OrderInfo {

        @SerializedName("clientPrice")
        private String mClientPrice;
        @SerializedName("optionCount")
        private int mOptionCount;
        @SerializedName("optionIdx")
        private int mOptionIdx;
        @SerializedName("optionName")
        private String mOptionName;
        @SerializedName("originalPrice")
        private String mOriginalPrice;
        @SerializedName("productIdx")
        private int mProductIdx;
        @SerializedName("productImg")
        private String mProductImg;
        @SerializedName("productName")
        private String mProductName;

        public OrderInfo(String clientPrice, int optionCount, int optionIdx, String optionName,
                         String originalPrice, int productIdx, String productImg, String productName) {
            this.mClientPrice = clientPrice;
            this.mOptionCount = optionCount;
            this.mOptionCount = optionIdx;
            this.mOptionName = optionName;
            this.mOriginalPrice = originalPrice;
            this.mProductIdx = productIdx;
            this.mProductImg = productImg;
            this.mProductName = productName;
        }

        public String getClientPrice() {
            return mClientPrice;
        }

        public void setClientPrice(String clientPrice) {
            mClientPrice = clientPrice;
        }

        public int getOptionCount() {
            return mOptionCount;
        }

        public void setOptionCount(int optionCount) {
            mOptionCount = optionCount;
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

        public String getOriginalPrice() {
            return mOriginalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            mOriginalPrice = originalPrice;
        }

        public int getProductIdx() {
            return mProductIdx;
        }

        public void setProductIdx(int productIdx) {
            mProductIdx = productIdx;
        }

        public String getProductImg() {
            return mProductImg;
        }

        public void setProductImg(String productImg) {
            mProductImg = productImg;
        }

        public String getProductName() {
            return mProductName;
        }

        public void setProductName(String productName) {
            mProductName = productName;
        }

    }


    public static class UserInfo {

        @SerializedName("phoneNumber")
        private String mPhoneNumber;
        @SerializedName("userIdx")
        private int mUserIdx;
        @SerializedName("userName")
        private String mUserName;
        @SerializedName("email")
        private String mEmail;

        public String getPhoneNumber() {
            return mPhoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            mPhoneNumber = phoneNumber;
        }

        public int getUserIdx() {
            return mUserIdx;
        }

        public void setUserIdx(int userIdx) {
            mUserIdx = userIdx;
        }

        public String getUserName() {
            return mUserName;
        }

        public void setUserName(String userName) {
            mUserName = userName;
        }

        public String getEmail() {
            return mEmail;
        }

        public void setEmail(String mEmail) {
            this.mEmail = mEmail;
        }
    }


    public static class Address {

        @SerializedName("address")
        private String mAddress;
        @SerializedName("destinationIdx")
        private int mDestinationIdx;
        @SerializedName("isMain")
        private String mIsMain;
        @SerializedName("isMorning")
        private String mIsMorning;
        @SerializedName("receiverName")
        private String mReceiverName;
        @SerializedName("receiverPhone")
        private String mReceiverPhone;

        public String getAddress() {
            return mAddress;
        }

        public void setAddress(String address) {
            mAddress = address;
        }

        public int getDestinationIdx() {
            return mDestinationIdx;
        }

        public void setDestinationIdx(int destinationIdx) {
            mDestinationIdx = destinationIdx;
        }

        public String getIsMain() {
            return mIsMain;
        }

        public void setIsMain(String isMain) {
            mIsMain = isMain;
        }

        public String getIsMorning() {
            return mIsMorning;
        }

        public void setIsMorning(String isMorning) {
            mIsMorning = isMorning;
        }

        public String getReceiverName() {
            return mReceiverName;
        }

        public void setReceiverName(String receiverName) {
            mReceiverName = receiverName;
        }

        public String getReceiverPhone() {
            return mReceiverPhone;
        }

        public void setReceiverPhone(String receiverPhone) {
            mReceiverPhone = receiverPhone;
        }

    }


    public static class ReceivePlace {

        @SerializedName("howToEnter")
        private String mHowToEnter;
        @SerializedName("receivePlace")
        private String mReceivePlace;

        public String getHowToEnter() {
            return mHowToEnter;
        }

        public void setHowToEnter(String howToEnter) {
            mHowToEnter = howToEnter;
        }

        public String getReceivePlace() {
            return mReceivePlace;
        }

        public void setReceivePlace(String receivePlace) {
            mReceivePlace = receivePlace;
        }

    }


    public static class CouponAndProfit {

        @SerializedName("allCouponCount")
        private int mAllCouponCount;
        @SerializedName("availableCouponCount")
        private int mAvailableCouponCount;
        @SerializedName("availablePoint")
        private int mAvailablePoint;

        public int getAllCouponCount() {
            return mAllCouponCount;
        }

        public void setAllCouponCount(int allCouponCount) {
            mAllCouponCount = allCouponCount;
        }

        public int getAvailableCouponCount() {
            return mAvailableCouponCount;
        }

        public void setAvailableCouponCount(int availableCouponCount) {
            mAvailableCouponCount = availableCouponCount;
        }

        public int getAvailablePoint() {
            return mAvailablePoint;
        }

        public void setAvailablePoint(int availablePoint) {
            mAvailablePoint = availablePoint;
        }

    }

  
    public static class Price {

        @SerializedName("delivery")
        private int mDelivery;
        @SerializedName("orderPrice")
        private int mOrderPrice;
        @SerializedName("priceToPay")
        private int mPriceToPay;
        @SerializedName("profitPercent")
        private float mProfitPercent;
        @SerializedName("profitPrice")
        private int mProfitPrice;
        @SerializedName("salePrice")
        private int mSalePrice;
        @SerializedName("totalPrice")
        private int mTotalPrice;

        public int getDelivery() {
            return mDelivery;
        }

        public void setDelivery(int delivery) {
            mDelivery = delivery;
        }

        public int getOrderPrice() {
            return mOrderPrice;
        }

        public void setOrderPrice(int orderPrice) {
            mOrderPrice = orderPrice;
        }

        public int getPriceToPay() {
            return mPriceToPay;
        }

        public void setPriceToPay(int priceToPay) {
            mPriceToPay = priceToPay;
        }

        public float getProfitPercent() {
            return mProfitPercent;
        }

        public void setProfitPercent(float profitPercent) {
            mProfitPercent = profitPercent;
        }

        public int getProfitPrice() {
            return mProfitPrice;
        }

        public void setProfitPrice(int profitPrice) {
            mProfitPrice = profitPrice;
        }

        public int getSalePrice() {
            return mSalePrice;
        }

        public void setSalePrice(int salePrice) {
            mSalePrice = salePrice;
        }

        public int getTotalPrice() {
            return mTotalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            mTotalPrice = totalPrice;
        }

    }


}
