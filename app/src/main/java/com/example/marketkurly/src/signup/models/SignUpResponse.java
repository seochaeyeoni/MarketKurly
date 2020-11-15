
package com.example.marketkurly.src.signup.models;

import com.google.gson.annotations.SerializedName;


public class SignUpResponse {

    @SerializedName("isSuccess")
    private boolean mIsSuccess;
    @SerializedName("code")
    private int mCode;
    @SerializedName("message")
    private String mMessage;

    @SerializedName("result")
    private SignUpResult mSignUpResult;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public boolean getIsSuccess() {
        return mIsSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        mIsSuccess = isSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public SignUpResult getResult() {
        return mSignUpResult;
    }

    public void setResult(SignUpResult signUpResult) {
        mSignUpResult = signUpResult;
    }

}
