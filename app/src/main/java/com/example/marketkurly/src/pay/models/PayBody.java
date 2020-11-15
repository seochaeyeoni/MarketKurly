
package com.example.marketkurly.src.pay.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PayBody {

    @SerializedName("destinationIdx")
    private int mDestinationIdx;
    @SerializedName("orderList")
    private ArrayList<OrderList> mOrderList;
    @SerializedName("originalPrice")
    private int mOriginalPrice;
    @SerializedName("payPrice")
    private int mPayPrice;
    @SerializedName("savedPoint")
    private int mSavedPoint;
    @SerializedName("totalClientPrice")
    private int mTotalClientPrice;
    @SerializedName("usedCouponIdx")
    private int mUsedCouponIdx;
    @SerializedName("usedPoint")
    private int mUsedPoint;
    @SerializedName("wayToPay")
    private String mWayToPay;

    public PayBody(int destinationIdx, int usedCouponIdx, int usedPoint, int savedPoint, int originalPrice,
                   int totalClientPrice, int payPrice, String wayToPay, ArrayList<OrderList> orderList) {
        this.mDestinationIdx = destinationIdx;
        this.mUsedCouponIdx = usedCouponIdx;
        this.mUsedPoint = usedPoint;
        this.mSavedPoint = savedPoint;
        this.mOriginalPrice = originalPrice;
        this.mTotalClientPrice = totalClientPrice;
        this.mPayPrice = payPrice;
        this.mWayToPay = wayToPay;
        this.mOrderList = orderList;
    }

    public int getDestinationIdx() {
        return mDestinationIdx;
    }

    public void setDestinationIdx(int destinationIdx) {
        mDestinationIdx = destinationIdx;
    }

    public ArrayList<OrderList> getOrderList() {
        return mOrderList;
    }

    public void setOrderList(ArrayList<OrderList> orderList) {
        mOrderList = orderList;
    }

    public int getOriginalPrice() {
        return mOriginalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        mOriginalPrice = originalPrice;
    }

    public int getPayPrice() {
        return mPayPrice;
    }

    public void setPayPrice(int payPrice) {
        mPayPrice = payPrice;
    }

    public int getSavedPoint() {
        return mSavedPoint;
    }

    public void setSavedPoint(int savedPoint) {
        mSavedPoint = savedPoint;
    }

    public int getTotalClientPrice() {
        return mTotalClientPrice;
    }

    public void setTotalClientPrice(int totalClientPrice) {
        mTotalClientPrice = totalClientPrice;
    }

    public int getUsedCouponIdx() {
        return mUsedCouponIdx;
    }

    public void setUsedCouponIdx(int usedCouponIdx) {
        mUsedCouponIdx = usedCouponIdx;
    }

    public int getUsedPoint() {
        return mUsedPoint;
    }

    public void setUsedPoint(int usedPoint) {
        mUsedPoint = usedPoint;
    }

    public String getWayToPay() {
        return mWayToPay;
    }

    public void setWayToPay(String wayToPay) {
        mWayToPay = wayToPay;
    }


    public static class OrderList {
    
        @SerializedName("optionCount")
        private int mOptionCount;
        @SerializedName("optionIdx")
        private int mOptionIdx;
        @SerializedName("productIdx")
        private int mProductIdx;

        public OrderList(int optionCount, int optionIdx, int productIdx) {
            this.mOptionCount = optionCount;
            this.mOptionIdx = optionIdx;
            this.mProductIdx = productIdx;
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
    
        public int getProductIdx() {
            return mProductIdx;
        }
    
        public void setProductIdx(int productIdx) {
            mProductIdx = productIdx;
        }
    
    }
}
