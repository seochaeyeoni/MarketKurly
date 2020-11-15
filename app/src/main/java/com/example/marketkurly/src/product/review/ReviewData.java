package com.example.marketkurly.src.product.review;

public class ReviewData {

    private String tv_title;
    private String isBest;
    private String tv_level;
    private String tv_user_name;
    private int isPic;
    private String tv_created_date;

    public ReviewData(String tv_title, String isBest, String tv_level, String tv_user_name, int isPic, String tv_created_date) {
        this.tv_title = tv_title;
        this.isBest = isBest;
        this.tv_level = tv_level;
        this.tv_user_name = tv_user_name;
        this.isPic = isPic;
        this.tv_created_date = tv_created_date;
    }

    public String getTv_title() {
        return tv_title;
    }

    public void setTv_title(String tv_title) {
        this.tv_title = tv_title;
    }

    public String getIsBest() {
        return isBest;
    }

    public void setIsBest(String isBest) {
        this.isBest = isBest;
    }

    public String getTv_level() {
        return tv_level;
    }

    public void setTv_level(String tv_level) {
        this.tv_level = tv_level;
    }

    public String getTv_user_name() {
        return tv_user_name;
    }

    public void setTv_user_name(String tv_user_name) {
        this.tv_user_name = tv_user_name;
    }

    public int getIsPic() {
        return isPic;
    }

    public void setIsPic(int isPic) {
        this.isPic = isPic;
    }

    public String getTv_created_date() {
        return tv_created_date;
    }

    public void setTv_created_date(String tv_created_date) {
        this.tv_created_date = tv_created_date;
    }
}

