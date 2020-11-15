
package com.example.marketkurly.src.basket.models;


import com.google.gson.annotations.SerializedName;

public class DeleteBasketItemResponse {

    @SerializedName("basketCount")
    private Long mBasketCount;
    @SerializedName("code")
    private Long mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;

    public Long getBasketCount() {
        return mBasketCount;
    }

    public void setBasketCount(Long basketCount) {
        mBasketCount = basketCount;
    }

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

}
