
package com.example.marketkurly.src.login.models;


import com.google.gson.annotations.SerializedName;

public class LoginBody {

    @SerializedName("password")
    private String password;
    @SerializedName("userId")
    private String userId;
    @SerializedName("tokenId")
    private String tokenId;

    public LoginBody(String userId, String password, String tokenId) {
        this.userId = userId;
        this.password = password;
        this.tokenId = tokenId;
    }

}
