
package com.example.marketkurly.src.signup.models;


import com.google.gson.annotations.SerializedName;


public class SignUpResult {

    @SerializedName("jwt")
    private String mJwt;

    public String getJwt() {
        return mJwt;
    }

    public void setJwt(String jwt) {
        mJwt = jwt;
    }

}