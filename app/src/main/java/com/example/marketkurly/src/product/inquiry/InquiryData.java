package com.example.marketkurly.src.product.inquiry;

public class InquiryData {

    private String tv_title;
    private String tv_user_name;
    private String isLocked;
    private String tv_created_date;
    private String isAnswered;

    public InquiryData(String tv_title, String tv_user_name, String isLocked, String tv_created_date, String isAnswered) {
        this.tv_title = tv_title;
        this.tv_user_name = tv_user_name;
        this.isLocked = isLocked;
        this.tv_created_date = tv_created_date;
        this.isAnswered = isAnswered;
    }

    public String getTv_title() {
        return tv_title;
    }

    public void setTv_title(String tv_title) {
        this.tv_title = tv_title;
    }

    public String getTv_user_name() {
        return tv_user_name;
    }

    public void setTv_user_name(String tv_user_name) {
        this.tv_user_name = tv_user_name;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getTv_created_date() {
        return tv_created_date;
    }

    public void setTv_created_date(String tv_created_date) {
        this.tv_created_date = tv_created_date;
    }

    public String getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(String isAnswered) {
        this.isAnswered = isAnswered;
    }
}

