
package com.example.marketkurly.src.basket.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BasketInfoResponse {

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
        private String mAddress;
        @SerializedName("basketCount")
        private int mBasketCount;
        @SerializedName("coldProduct")
        private List<ColdProduct> mColdProduct;
        @SerializedName("frozenProduct")
        private List<FrozenProduct> mFrozenProduct;
        @SerializedName("normalProduct")
        private List<NormalProduct> mNormalProduct;
        @SerializedName("price")
        private Price mPrice;
        @SerializedName("typePrice")
        private List<BasketInfoResponse.TypePrice> mTypePrice;

        public String getAddress() {
            return mAddress;
        }

        public void setAddress(String address) {
            mAddress = address;
        }

        public int getBasketCount() {
            return mBasketCount;
        }

        public void setBasketCount(int basketCount) {
            mBasketCount = basketCount;
        }

        public List<ColdProduct> getColdProduct() {
            return mColdProduct;
        }

        public void setColdProduct(List<ColdProduct> coldProduct) {
            mColdProduct = coldProduct;
        }

        public List<FrozenProduct> getFrozenProduct() {
            return mFrozenProduct;
        }

        public void setFrozenProduct(List<FrozenProduct> frozenProduct) {
            mFrozenProduct = frozenProduct;
        }

        public List<NormalProduct> getNormalProduct() {
            return mNormalProduct;
        }

        public void setNormalProduct(List<NormalProduct> normalProduct) {
            mNormalProduct = normalProduct;
        }

        public Price getPrice() {
            return mPrice;
        }

        public void setPrice(Price price) {
            mPrice = price;
        }

        public List<BasketInfoResponse.TypePrice> getTypePrice() {
            return mTypePrice;
        }

        public void setTypePrice(List<BasketInfoResponse.TypePrice> typePrice) {
            mTypePrice = typePrice;
        }

    }


    public static class ColdProduct {
    
        @SerializedName("clientPrice")
        private int mClientPrice;
        @SerializedName("needCount")
        private int mNeedCount;
        @SerializedName("optionIdx")
        private int mOptionIdx;
        @SerializedName("optionName")
        private String mOptionName;
        @SerializedName("originalPrice")
        private int mOriginalPrice;
        @SerializedName("productIdx")
        private int mProductIdx;
        @SerializedName("productImg")
        private String mProductImg;
        @SerializedName("productName")
        private String mProductName;
        @SerializedName("type")
        private String mType;
        @SerializedName("count")
        private int mCount;
        @SerializedName("checked")
        private boolean mChecked;

        public ColdProduct(int mClientPrice, int mNeedCount, int mOptionIdx, String mOptionName,
                           int mOriginalPrice, int mProductIdx, String mProductImg, String mProductName,
                           String mType, int count, boolean checked) {
            this.mClientPrice = mClientPrice;
            this.mNeedCount = mNeedCount;
            this.mOptionIdx = mOptionIdx;
            this.mOptionName = mOptionName;
            this.mOriginalPrice = mOriginalPrice;
            this.mProductIdx = mProductIdx;
            this.mProductImg = mProductImg;
            this.mProductName = mProductName;
            this.mType = mType;
            this.mCount = count;
            this.mChecked = checked;
        }

        public int getClientPrice() {
            return mClientPrice;
        }
    
        public void setClientPrice(int clientPrice) {
            mClientPrice = clientPrice;
        }
    
        public int getNeedCount() {
            return mNeedCount;
        }
    
        public void setNeedCount(int needCount) {
            mNeedCount = needCount;
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
    
        public String getType() {
            return mType;
        }
    
        public void setType(String type) {
            mType = type;
        }

        public int getCount() {
            return mCount;
        }

        public void setCount(int mCount) {
            this.mCount = mCount;
        }

        public boolean getChecked() {
            return mChecked;
        }

        public void setChecked(boolean mChecked) {
            this.mChecked = mChecked;
        }
    }


    public static class FrozenProduct {
    
        @SerializedName("clientPrice")
        private int mClientPrice;
        @SerializedName("needCount")
        private int mNeedCount;
        @SerializedName("optionIdx")
        private int mOptionIdx;
        @SerializedName("optionName")
        private String mOptionName;
        @SerializedName("originalPrice")
        private int mOriginalPrice;
        @SerializedName("productIdx")
        private int mProductIdx;
        @SerializedName("productImg")
        private String mProductImg;
        @SerializedName("productName")
        private String mProductName;
        @SerializedName("type")
        private String mType;
        @SerializedName("count")
        private int mCount;
        @SerializedName("checked")
        private boolean mChecked;

        public FrozenProduct(int mClientPrice, int mNeedCount, int mOptionIdx, String mOptionName,
                             int mOriginalPrice, int mProductIdx, String mProductImg, String mProductName,
                             String mType, int mCount, boolean checked) {
            this.mClientPrice = mClientPrice;
            this.mNeedCount = mNeedCount;
            this.mOptionIdx = mOptionIdx;
            this.mOptionName = mOptionName;
            this.mOriginalPrice = mOriginalPrice;
            this.mProductIdx = mProductIdx;
            this.mProductImg = mProductImg;
            this.mProductName = mProductName;
            this.mType = mType;
            this.mCount = mCount;
            this.mChecked = checked;
        }

        public int getClientPrice() {
            return mClientPrice;
        }
    
        public void setClientPrice(int clientPrice) {
            mClientPrice = clientPrice;
        }
    
        public int getNeedCount() {
            return mNeedCount;
        }
    
        public void setNeedCount(int needCount) {
            mNeedCount = needCount;
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
    
        public String getType() {
            return mType;
        }
    
        public void setType(String type) {
            mType = type;
        }

        public int getCount() {
            return mCount;
        }

        public void setCount(int mCount) {
            this.mCount = mCount;
        }

        public boolean getChecked() {
            return mChecked;
        }

        public void setChecked(boolean mChecked) {
            this.mChecked = mChecked;
        }
    }


    public static class NormalProduct {
    
        @SerializedName("clientPrice")
        private int mClientPrice;
        @SerializedName("needCount")
        private int mNeedCount;
        @SerializedName("optionIdx")
        private int mOptionIdx;
        @SerializedName("optionName")
        private String mOptionName;
        @SerializedName("originalPrice")
        private int mOriginalPrice;
        @SerializedName("productIdx")
        private int mProductIdx;
        @SerializedName("productImg")
        private String mProductImg;
        @SerializedName("productName")
        private String mProductName;
        @SerializedName("type")
        private String mType;
        @SerializedName("count")
        private int mCount;
        @SerializedName("checked")
        private boolean mChecked;

        public NormalProduct(int mClientPrice, int mNeedCount, int mOptionIdx, String mOptionName,
                             int mOriginalPrice, int mProductIdx, String mProductImg, String mProductName,
                             String mType, int mCount, boolean checked) {
            this.mClientPrice = mClientPrice;
            this.mNeedCount = mNeedCount;
            this.mOptionIdx = mOptionIdx;
            this.mOptionName = mOptionName;
            this.mOriginalPrice = mOriginalPrice;
            this.mProductIdx = mProductIdx;
            this.mProductImg = mProductImg;
            this.mProductName = mProductName;
            this.mType = mType;
            this.mCount = mCount;
            this.mChecked = checked;
        }

        public int getClientPrice() {
            return mClientPrice;
        }
    
        public void setClientPrice(int clientPrice) {
            mClientPrice = clientPrice;
        }
    
        public int getNeedCount() {
            return mNeedCount;
        }
    
        public void setNeedCount(int needCount) {
            mNeedCount = needCount;
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
    
        public String getType() {
            return mType;
        }
    
        public void setType(String type) {
            mType = type;
        }

        public int getCount() {
            return mCount;
        }

        public void setCount(int mCount) {
            this.mCount = mCount;
        }

        public boolean getChecked() {
            return mChecked;
        }

        public void setChecked(boolean mChecked) {
            this.mChecked = mChecked;
        }
    
    }


    public static class Price {
    
        @SerializedName("delivery")
        private int mDelivery;
        @SerializedName("priceToPay")
        private int mPriceToPay;
        @SerializedName("profit")
        private float mProfit;
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
    
        public int getPriceToPay() {
            return mPriceToPay;
        }
    
        public void setPriceToPay(int priceToPay) {
            mPriceToPay = priceToPay;
        }

        public float getProfit() {
            return mProfit;
        }

        public void setProfit(float mProfit) {
            this.mProfit = mProfit;
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
    
    public static class TypePrice {
    
        @SerializedName("totalClientPrice")
        private int mTotalClientPrice;
        @SerializedName("totalOriginalPrice")
        private int mTotalOriginalPrice;
        @SerializedName("type")
        private String mType;
    
        public int getTotalClientPrice() {
            return mTotalClientPrice;
        }
    
        public void setTotalClientPrice(int totalClientPrice) {
            mTotalClientPrice = totalClientPrice;
        }
    
        public int getTotalOriginalPrice() {
            return mTotalOriginalPrice;
        }
    
        public void setTotalOriginalPrice(int totalOriginalPrice) {
            mTotalOriginalPrice = totalOriginalPrice;
        }
    
        public String getType() {
            return mType;
        }
    
        public void setType(String type) {
            mType = type;
        }
    
    }
}
