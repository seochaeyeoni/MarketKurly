package com.example.marketkurly.src.pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.marketkurly.R;
import com.example.marketkurly.src.pay.interfaces.PayActivityView;
import com.example.marketkurly.src.pay.models.CouponBody;
import com.example.marketkurly.src.pay.models.CouponResponse;
import com.example.marketkurly.src.pay.models.PayResponse;
import com.example.marketkurly.src.pay.models.PayScreenResponse;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity implements PayActivityView {
    private CouponAdapter couponAdapter;
    private ListView lv_coupon;
    ArrayList<CouponResponse.Result> couponArrayList = new ArrayList<CouponResponse.Result>();
    ArrayList<Integer> payBillArrayList = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        Intent intent = getIntent();
        payBillArrayList = (ArrayList<Integer>) intent.getIntegerArrayListExtra("payBillArrayList");

        tryPostCoupon();

//        Intent intent = new Intent();
//        intent.putExtra("address_num", arg1);
//        intent.putExtra("address_main", String.format("%s (%s)", arg2, arg3));
//        intent.putExtra("address_sub", mSubAddress);
//        setResult(106, intent);


        Button coupon_o = (Button) findViewById(R.id.coupon_o_btn);
        coupon_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button coupon_x = (Button) findViewById(R.id.coupon_x_btn);
        coupon_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
    }

    private void tryPostCoupon() {
        //showProgressDialog();
        final PayService payService = new PayService(this);
        CouponBody couponBody = new CouponBody(payBillArrayList);
        payService.postCoupon(couponBody);
    }

    @Override
    public void payScreenSuccess(PayScreenResponse payScreenResponse) {

    }

    @Override
    public void payScreenFailure(String message) {

    }

    @Override
    public void couponSuccess(CouponResponse couponResponse) {
        //lv_coupon
        for (int i = 0; i < couponResponse.getResult().size(); i++) {
            couponArrayList.add(new CouponResponse.Result(
                    couponResponse.getResult().get(i).getContents(),
                    couponResponse.getResult().get(i).getCouponIdx(),
                    couponResponse.getResult().get(i).getCouponName(),
                    couponResponse.getResult().get(i).getDiscount(),
                    couponResponse.getResult().get(i).getExpiration(),
                    couponResponse.getResult().get(i).getIsAvailable()
            ));
        }
        couponAdapter = new CouponAdapter(couponArrayList, getApplicationContext());
        lv_coupon.setAdapter(couponAdapter);
        //lv_coupon.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    @Override
    public void paySuccess(PayResponse payResponse) {

    }
}