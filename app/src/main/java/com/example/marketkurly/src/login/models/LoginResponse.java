
package com.example.marketkurly.src.login.models;

import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private Result mResult;

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

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        mResult = result;
    }

    public static class Result {

        @SerializedName("jwt")
        private String mJwt;

        public String getJwt() {
            return mJwt;
        }

        public void setJwt(String jwt) {
            mJwt = jwt;
        }

    }
}
