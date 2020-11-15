
package com.example.marketkurly.src.signup.models;

import com.google.gson.annotations.SerializedName;


public class IdCheckResponse {

    @SerializedName("code")
    private int mCode;
    @SerializedName("isSuccess")
    private boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;

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
}
