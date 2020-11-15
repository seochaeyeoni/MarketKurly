
package com.example.marketkurly.src.basket.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;


public class BasketChangeBody {

    @SerializedName("option")
    private List<Option> mOption;

    public BasketChangeBody(ArrayList<Option> mOption) {
        this.mOption = mOption;
    }

    public List<Option> getOption() {
        return mOption;
    }

    public void setOption(List<Option> option) {
        mOption = option;
    }
    
    public static class Option {
    
        @SerializedName("optionCount")
        private int mOptionCount;
        @SerializedName("optionIdx")
        private int mOptionIdx;

        public Option(int count, int optionIdx) {
            this.mOptionCount = count;
            this.mOptionIdx = optionIdx;
        }

        public int getOptionCount() {
            return mOptionCount;
        }
    
        public void setOptionCount(int optionCount) {
            mOptionCount = optionCount;
        }
    
        public int getOptionIdx() {
            return mOptionIdx;
        }
    
        public void setOptionIdx(int optionIdx) {
            mOptionIdx = optionIdx;
        }
    
    }
}
