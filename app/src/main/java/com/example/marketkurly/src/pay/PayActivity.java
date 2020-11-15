package com.example.marketkurly.src.pay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketkurly.R;
import com.example.marketkurly.src.pay.interfaces.PayActivityView;
import com.example.marketkurly.src.pay.models.CouponBody;
import com.example.marketkurly.src.pay.models.CouponResponse;
import com.example.marketkurly.src.pay.models.PayBody;
import com.example.marketkurly.src.pay.models.PayResponse;
import com.example.marketkurly.src.pay.models.PayScreenBody;
import com.example.marketkurly.src.pay.models.PayScreenResponse;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity implements PayActivityView {
    private ListView lv_order_info;
    private OrderInfoAdapter orderInfoAdapter;

    ArrayList<Integer> payBillArrayList = new ArrayList<Integer>();
    ArrayList<PayScreenResponse.OrderInfo> orderInfoArrayList = new ArrayList<PayScreenResponse.OrderInfo>();

    ArrayList<PayBody.OrderList> orderList = new ArrayList<PayBody.OrderList>();

    int destinationIdx, usedCouponIdx, usedPoint, savedPoint, originalPrice, totalClientPrice, payPrice;
    float mProfit;
    String wayToPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Intent intent = getIntent();
        payBillArrayList = (ArrayList<Integer>) intent.getIntegerArrayListExtra("payBillArrayList");

        tryPostPayScreen();

        //toolbar
        Toolbar toolbar = findViewById(R.id.pay_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);


        //결제하기 버튼 누르면
        Button button = (Button) findViewById(R.id.pay_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryPostPay();
            }
        });


        lv_order_info = findViewById(R.id.pay_screen_lv);
        final ImageView product_info_btn = (ImageView) findViewById(R.id.pay_product_info_btn);
        final Drawable arrow_down = getResources().getDrawable(R.drawable.pay_arrow_down);
        final Drawable arrow_up = getResources().getDrawable(R.drawable.pay_arrow_up);
        final LinearLayout product_info_hide = (LinearLayout) findViewById(R.id.pay_product_info_hide);
        LinearLayout product_info_col = (LinearLayout) findViewById(R.id.pay_product_info_col);
        product_info_col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product_info_btn.getBackground() == arrow_down) {
                    product_info_btn.setBackground(arrow_up);
                    lv_order_info.setVisibility(View.VISIBLE);
                    product_info_hide.setVisibility(View.INVISIBLE);
                } else {
                    product_info_btn.setBackground(arrow_down);
                    lv_order_info.setVisibility(View.GONE);
                    product_info_hide.setVisibility(View.VISIBLE);
                }
            }
        });

        final ImageView user_info_btn = (ImageView) findViewById(R.id.pay_user_info_btn);
        final TextView user_info_tv = (TextView) findViewById(R.id.pay_user_info_tv);
        final LinearLayout user_info_hide = (LinearLayout) findViewById(R.id.pay_user_info_hide);
        LinearLayout user_info_col = (LinearLayout) findViewById(R.id.pay_user_info_col);
        user_info_col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_info_btn.getBackground() == arrow_down) {
                    user_info_btn.setBackground(arrow_up);
                    user_info_tv.setVisibility(View.INVISIBLE);
                    user_info_hide.setVisibility(View.VISIBLE);
                } else {
                    user_info_btn.setBackground(arrow_down);
                    user_info_tv.setVisibility(View.VISIBLE);
                    user_info_hide.setVisibility(View.GONE);
                }
            }
        });


        //쿠폰 창
        final TextView coupon_tv = (TextView) findViewById(R.id.pay_coupon_tv);
        final LinearLayout coupon_col = (LinearLayout) findViewById(R.id.pay_coupon_col);
        coupon_col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CouponActivity.class);
                intent.putIntegerArrayListExtra("payBillArrayList",payBillArrayList);
                startActivity(intent );//액티비티 띄우기

            }
        });




        //적립금
        final EditText point_entered = (EditText) findViewById(R.id.pay_point_entered);
        point_entered.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView point_use = (TextView) findViewById(R.id.pay_point_use);
                TextView total_price = (TextView) findViewById(R.id.pay_total_price);
                TextView profit = (TextView) findViewById(R.id.pay_profit);

                point_use.setText(point_entered.getText().toString().trim());
                totalClientPrice -= Integer.parseInt(point_entered.getText().toString().trim());
                total_price.setText(String.valueOf(totalClientPrice));
                savedPoint = (int) (totalClientPrice*mProfit*0.01);
                profit.setText(String.valueOf(savedPoint));

            }
            @Override
            public void afterTextChanged(Editable s) {
                usedPoint = Integer.parseInt(point_entered.getText().toString().trim());
                totalClientPrice -= Integer.parseInt(point_entered.getText().toString().trim());
                savedPoint = (int) (totalClientPrice*mProfit*0.01);
            }
        });
        //결제수단 선택
        final Button credit_card = (Button) findViewById(R.id.pay_credit_card_btn);
        final Button chai = (Button) findViewById(R.id.pay_chai_btn);
        final Button toss = (Button) findViewById(R.id.pay_toss_btn);
        final Button naver = (Button) findViewById(R.id.pay_naver_btn);
        final Button payco = (Button) findViewById(R.id.pay_payco_btn);
        final Button smile = (Button) findViewById(R.id.pay_smile_btn);
        final Button mobile = (Button) findViewById(R.id.pay_mobile_btn);
        final Drawable check_o = getResources().getDrawable(R.drawable.btn_pay_checked);
        final Drawable check_x = getResources().getDrawable(R.drawable.btn_pay_unchecked);

        credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wayToPay = "신용카드";
                credit_card.setBackground(check_o);
                chai.setBackground(check_x);
                toss.setBackground(check_x);
                naver.setBackground(check_x);
                payco.setBackground(check_x);
                smile.setBackground(check_x);
                mobile.setBackground(check_x);
            }
        });
        chai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wayToPay = "차이페이";
                credit_card.setBackground(check_x);
                chai.setBackground(check_o);
                toss.setBackground(check_x);
                naver.setBackground(check_x);
                payco.setBackground(check_x);
                smile.setBackground(check_x);
                mobile.setBackground(check_x);
            }
        });
        toss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wayToPay = "토스";
                credit_card.setBackground(check_x);
                chai.setBackground(check_x);
                toss.setBackground(check_o);
                naver.setBackground(check_x);
                payco.setBackground(check_x);
                smile.setBackground(check_x);
                mobile.setBackground(check_x);
            }
        });
        naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wayToPay = "네이버 페이";
                credit_card.setBackground(check_x);
                chai.setBackground(check_x);
                toss.setBackground(check_x);
                naver.setBackground(check_o);
                payco.setBackground(check_x);
                smile.setBackground(check_x);
                mobile.setBackground(check_x);
            }
        });
        payco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wayToPay = "페이코";
                credit_card.setBackground(check_x);
                chai.setBackground(check_x);
                toss.setBackground(check_x);
                naver.setBackground(check_x);
                payco.setBackground(check_o);
                smile.setBackground(check_x);
                mobile.setBackground(check_x);
            }
        });
        smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wayToPay = "스마일페이";
                credit_card.setBackground(check_x);
                chai.setBackground(check_x);
                toss.setBackground(check_x);
                naver.setBackground(check_x);
                payco.setBackground(check_x);
                smile.setBackground(check_o);
                mobile.setBackground(check_x);
            }
        });
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wayToPay = "휴대폰";
                credit_card.setBackground(check_x);
                chai.setBackground(check_x);
                toss.setBackground(check_x);
                naver.setBackground(check_x);
                payco.setBackground(check_x);
                smile.setBackground(check_x);
                mobile.setBackground(check_o);
            }
        });

        final ImageView consent_check = (ImageView) findViewById(R.id.pay_consent_necessary);
        final Drawable checked = getResources().getDrawable(R.drawable.check_checked);
        final Drawable unchecked = getResources().getDrawable(R.drawable.check_unchecked);
        LinearLayout conent_col = (LinearLayout) findViewById(R.id.pay_consent_col);
        conent_col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( consent_check.getBackground() == checked ) {
                    consent_check.setBackground(unchecked);
                } else { consent_check.setBackground(checked); }
            }
        });


    }

    //toolbar 메뉴가 눌렸을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //리스트뷰 높이 조절.. 왜 여기선 안 될까... 된다.. 서버에서 안 보내졌던 거 근데 높이가 2배로 계산되네?
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    private void tryPostPayScreen() {
        //showProgressDialog();
        final PayService payService = new PayService(this);
        PayScreenBody payScreenBody = new PayScreenBody(payBillArrayList);
        payService.postPayScreen(payScreenBody);
    }



    private void tryPostPay() {
        //showProgressDialog();
        final PayService payService = new PayService(this);
        PayBody payBody = new PayBody(destinationIdx, usedCouponIdx, usedPoint, savedPoint,
                originalPrice, totalClientPrice, payPrice, wayToPay, orderList);
        payService.postPay(payBody);
    }

    @Override
    public void payScreenSuccess(PayScreenResponse payScreenResponse) {

        LinearLayout shipping_col = (LinearLayout) findViewById(R.id.pay_shipping_col);
        LinearLayout receive_col = (LinearLayout) findViewById(R.id.pay_receive_col);
        LinearLayout payment_method_col = (LinearLayout) findViewById(R.id.pay_payment_method_col);
        TextView product_name = (TextView) findViewById(R.id.pay_product_name);
        TextView product_number = (TextView) findViewById(R.id.pay_product_number);
        TextView user_info_tv = (TextView) findViewById(R.id.pay_user_info_tv);
        TextView user_name = (TextView) findViewById(R.id.pay_user_name);
        TextView user_mobile = (TextView) findViewById(R.id.pay_user_mobile);
        TextView user_email = (TextView) findViewById(R.id.pay_user_email);
        TextView address = (TextView) findViewById(R.id.pay_address);
        TextView is_morning = (TextView) findViewById(R.id.pay_is_morning);
        TextView is_main = (TextView) findViewById(R.id.pay_is_main);
        TextView address_user_info = (TextView) findViewById(R.id.pay_address_user_info);
        TextView recieve_place = (TextView) findViewById(R.id.pay_recieve_place);
        TextView how_to_enter = (TextView) findViewById(R.id.pay_how_to_enter);
        TextView coupon_tv = (TextView) findViewById(R.id.pay_coupon_tv);
        TextView coupon_available = (TextView) findViewById(R.id.pay_coupon_available);
        TextView coupon_total = (TextView) findViewById(R.id.pay_coupon_total);
        TextView point_available = (TextView) findViewById(R.id.pay_point_available);
        TextView order_price = (TextView) findViewById(R.id.pay_order_price);
        TextView order_price_product = (TextView) findViewById(R.id.pay_order_price_product);
        TextView order_price_discount = (TextView) findViewById(R.id.pay_order_price_discount);
        TextView shipping_price = (TextView) findViewById(R.id.pay_shipping_price);
        TextView coupon_discount = (TextView) findViewById(R.id.pay_coupon_discount);
        TextView point_use = (TextView) findViewById(R.id.pay_point_use);
        TextView total_price = (TextView) findViewById(R.id.pay_total_price);
        TextView profit = (TextView) findViewById(R.id.pay_profit);
        TextView profit_percent = (TextView) findViewById(R.id.pay_profit_percent);

        mProfit = payScreenResponse.getResult().getPrice().getProfitPercent();

        destinationIdx = payScreenResponse.getResult().getAddress().getDestinationIdx();
        savedPoint = payScreenResponse.getResult().getPrice().getProfitPrice();
        originalPrice = payScreenResponse.getResult().getPrice().getTotalPrice();
        totalClientPrice = payScreenResponse.getResult().getPrice().getPriceToPay();
        payPrice = payScreenResponse.getResult().getPrice().getPriceToPay();

        product_name.setText(payScreenResponse.getResult().getOrderInfo().get(0).getProductName());
        product_number.setText(String.valueOf(payScreenResponse.getResult().getOrderInfo().size()-1));
        user_info_tv.setText(String.format("%s, %s", payScreenResponse.getResult().getUserInfo().getUserName(),
                payScreenResponse.getResult().getUserInfo().getPhoneNumber()));
        user_name.setText(payScreenResponse.getResult().getUserInfo().getUserName());
        user_mobile.setText(payScreenResponse.getResult().getUserInfo().getPhoneNumber());
        user_email.setText(payScreenResponse.getResult().getUserInfo().getEmail());
        if (payScreenResponse.getResult().getAddress().getIsMorning().equals("Y")) {
            is_morning.setVisibility(View.VISIBLE);
        } else { is_morning.setVisibility(View.GONE); }
        if (payScreenResponse.getResult().getAddress().getIsMain().equals("Y")) {
            is_main.setVisibility(View.VISIBLE);
        } else { is_main.setVisibility(View.GONE); }
        address.setText(payScreenResponse.getResult().getAddress().getAddress());
        address_user_info.setText(String.format("%s, %s", payScreenResponse.getResult().getAddress().getReceiverName(),
                payScreenResponse.getResult().getAddress().getReceiverPhone()));
        recieve_place.setText(payScreenResponse.getResult().getReceivePlace().getReceivePlace());
        how_to_enter.setText(payScreenResponse.getResult().getReceivePlace().getHowToEnter());
        if (payScreenResponse.getResult().getCouponAndProfit().getAvailableCouponCount() != 0) {
            coupon_tv.setText("쿠폰을 선택해주세요");
        }
        coupon_available.setText(String.valueOf(payScreenResponse.getResult().getCouponAndProfit().getAvailableCouponCount()));
        coupon_total.setText(String.valueOf(payScreenResponse.getResult().getCouponAndProfit().getAllCouponCount()));
        point_available.setText(String.valueOf(payScreenResponse.getResult().getCouponAndProfit().getAvailablePoint()));
        order_price.setText(String.valueOf(payScreenResponse.getResult().getPrice().getOrderPrice()));
        order_price_product.setText(String.valueOf(payScreenResponse.getResult().getPrice().getTotalPrice()));
        order_price_discount.setText(String.valueOf(payScreenResponse.getResult().getPrice().getSalePrice()));
        shipping_price.setText(String.valueOf(payScreenResponse.getResult().getPrice().getDelivery()));
        total_price.setText(String.valueOf(payScreenResponse.getResult().getPrice().getPriceToPay()));
        profit.setText(String.valueOf(payScreenResponse.getResult().getPrice().getProfitPrice()));
        profit_percent.setText(String.valueOf(payScreenResponse.getResult().getPrice().getProfitPercent()));

        
        //lv_order_info
        for (int i = 0; i < payScreenResponse.getResult().getOrderInfo().size(); i++) {
            orderInfoArrayList.add(new PayScreenResponse.OrderInfo(
                    payScreenResponse.getResult().getOrderInfo().get(i).getClientPrice(),
                    payScreenResponse.getResult().getOrderInfo().get(i).getOptionCount(),
                    payScreenResponse.getResult().getOrderInfo().get(i).getOptionIdx(),
                    payScreenResponse.getResult().getOrderInfo().get(i).getOptionName(),
                    payScreenResponse.getResult().getOrderInfo().get(i).getOriginalPrice(),
                    payScreenResponse.getResult().getOrderInfo().get(i).getProductIdx(),
                    payScreenResponse.getResult().getOrderInfo().get(i).getProductImg(),
                    payScreenResponse.getResult().getOrderInfo().get(i).getProductName()
            ));
        }
        orderInfoAdapter = new OrderInfoAdapter(orderInfoArrayList, getApplicationContext());
        lv_order_info.setAdapter(orderInfoAdapter);
        setListViewHeightBasedOnChildren(lv_order_info);

        //orderList
        for (int i = 0; i < payScreenResponse.getResult().getOrderInfo().size(); i++) {
            orderList.add(new PayBody.OrderList(
                    payScreenResponse.getResult().getOrderInfo().get(i).getOptionCount(),
                    payScreenResponse.getResult().getOrderInfo().get(i).getOptionIdx(),
                    payScreenResponse.getResult().getOrderInfo().get(i).getProductIdx()
            ));
        }

    }

    @Override
    public void payScreenFailure(String message) {

    }

    @Override
    public void couponSuccess(CouponResponse couponResponse) {

    }


    @Override
    public void paySuccess(PayResponse payResponse) {
        Toast.makeText(getApplicationContext(), payResponse.getMessage(), Toast.LENGTH_LONG).show();
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        TextView daum_result = (TextView) findViewById(R.id.sign_up_address);
//        final EditText address_detail = (EditText) findViewById(R.id.sign_up_address_detail);
//
//        if (requestCode == 105) {
//            if (resultCode == 106) {
//                String address_num = data.getStringExtra("address_num");
//                String address_main = data.getStringExtra("address_main");
//                String address_sub = data.getStringExtra("address_sub");
//                daum_result.setText(address_main);
//                daum_result.setTextColor(Color.parseColor("#333333"));
//                address_detail.setText(address_sub);
//                address_detail.setTextColor(Color.parseColor("#333333"));
//                address_detail.setVisibility(View.VISIBLE);
//
//            }
//        }
//    }

}