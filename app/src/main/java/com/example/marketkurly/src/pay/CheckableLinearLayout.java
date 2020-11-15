package com.example.marketkurly.src.pay;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

import com.example.marketkurly.R;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    public CheckableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isChecked() {
        RadioButton radioButton = (RadioButton) findViewById(R.id.coupon_select);
        return radioButton.isChecked();
    }

    @Override
    public void toggle() {
        RadioButton radioButton = (RadioButton) findViewById(R.id.coupon_select);

        setChecked(radioButton.isChecked() ? false : true);

    }

    @Override
    public void setChecked(boolean checked) {
        RadioButton radioButton = (RadioButton) findViewById(R.id.coupon_select);
        if (radioButton.isChecked() != checked) {
            radioButton.setChecked(checked);
        }


    }


}
