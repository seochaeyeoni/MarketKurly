
package com.example.marketkurly.src.signup.models;

import com.google.gson.annotations.SerializedName;


public class SignUpBody {

    @SerializedName("acceptPrivacy")
    private String mAcceptPrivacy;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("birthday")
    private String mBirthday;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("event")
    private String mEvent;
    @SerializedName("gender")
    private String mGender;
    @SerializedName("isEmail")
    private String mIsEmail;
    @SerializedName("isSMS")
    private String mIsSMS;
    @SerializedName("name")
    private String mName;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("recommenderId")
    private String mRecommenderId;
    @SerializedName("userId")
    private String mUserId;

    public SignUpBody(String userId, String password, String name, String email, String phoneNumber,
                      String address, String birthday, String gender, String recommenderId, String event,
                      String acceptPrivacy, String isSMS, String isEmail) {
        this.mUserId = userId;
        this.mPassword = password;
        this.mName = name;
        this.mEmail = email;
        this.mPhoneNumber = phoneNumber;
        this.mAddress = address;
        this.mBirthday = birthday;
        this.mGender = gender;
        this.mRecommenderId = recommenderId;
        this.mEvent = event;
        this.mAcceptPrivacy = acceptPrivacy;
        this.mIsSMS = isSMS;
        this.mIsEmail = isEmail;
    }


    public String getAcceptPrivacy() {
        return mAcceptPrivacy;
    }

    public void setAcceptPrivacy(String acceptPrivacy) {
        mAcceptPrivacy = acceptPrivacy;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        mBirthday = birthday;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        mEvent = event;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getIsEmail() {
        return mIsEmail;
    }

    public void setIsEmail(String isEmail) {
        mIsEmail = isEmail;
    }

    public String getIsSMS() {
        return mIsSMS;
    }

    public void setIsSMS(String isSMS) {
        mIsSMS = isSMS;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getRecommenderId() {
        return mRecommenderId;
    }

    public void setRecommenderId(String recommenderId) {
        mRecommenderId = recommenderId;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
