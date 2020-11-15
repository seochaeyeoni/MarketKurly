
package com.example.marketkurly.src.select.models;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class BasketAddBody {

    public BasketAddBody(ArrayList<Option> mOption) {
        this.mOption = mOption;
    }

    @SerializedName("option")
    private ArrayList<Option> mOption;

    public ArrayList<Option> getOption() {
        return mOption;
    }

    public void setOption(ArrayList<Option> option) {
        mOption = option;
    }


    public static class Option {

        @SerializedName("count")
        private int mCount;
        @SerializedName("optionIdx")
        private int mOptionIdx;
        @SerializedName("productIdx")
        private int mProductIdx;

        public Option(int mCount, int mOptionIdx, int mProductIdx) {
            this.mCount = mCount;
            this.mOptionIdx = mOptionIdx;
            this.mProductIdx = mProductIdx;
        }

        public int getCount() {
            return mCount;
        }

        public void setCount(int count) {
            mCount = count;
        }

        public int getOptionIdx() {
            return mOptionIdx;
        }

        public void setOptionIdx(int optionIdx) {
            mOptionIdx = optionIdx;
        }

        public int getProductIdx() {
            return mProductIdx;
        }

        public void setProductIdx(int productIdx) {
            mProductIdx = productIdx;
        }

    }
}
