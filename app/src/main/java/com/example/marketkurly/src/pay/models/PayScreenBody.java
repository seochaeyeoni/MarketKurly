
package com.example.marketkurly.src.pay.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PayScreenBody {

    @SerializedName("option")
    private List<Integer> mOption;

    public PayScreenBody(ArrayList<Integer> mOption) {
        this.mOption = mOption;
    }

    public List<Integer> getOption() {
        return mOption;
    }

    public void setOption(List<Integer> option) {
        mOption = option;
    }

}
