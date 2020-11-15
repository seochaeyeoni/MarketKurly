package com.example.marketkurly.src.basket;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.marketkurly.R;
import com.example.marketkurly.src.basket.interfaces.BasketActivityView;
import com.example.marketkurly.src.basket.interfaces.BasketOnItemClick;
import com.example.marketkurly.src.basket.models.BasketChangeBody;
import com.example.marketkurly.src.basket.models.BasketChangeResponse;
import com.example.marketkurly.src.basket.models.BasketInfoResponse;
import com.example.marketkurly.src.basket.models.DeleteBasketItemResponse;
import com.example.marketkurly.src.pay.PayActivity;
import com.example.marketkurly.src.signup.SignUpActivity;


import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity implements BasketActivityView, BasketOnItemClick {
    private ArrayList<BasketInfoResponse.ColdProduct> coldArrayList;
    private ArrayList<BasketInfoResponse.FrozenProduct> frozenArrayList;
    private ArrayList<BasketInfoResponse.NormalProduct> normalArrayList;
    private BasketColdAdapter coldAdapter;
    private BasketFrozenAdapter frozenAdapter;
    private BasketNormalAdapter normalAdapter;
    private ListView lv_cold, lv_frozen, lv_normal;
    int mTotalPrice, mTotalPriceBefore, mProductCount, currentProductCount, totalPrice, totalPriceBefore;
    int deltaPriceCold, deltaPriceBeforeCold, deltaPriceFrozen, deltaPriceBeforeFrozen,
            deltaPriceNormal, deltaPriceBeforeNormal = 0;
    float mProfit;
    boolean mDelete = false;


    ArrayList<BasketInfoResponse.ColdProduct> coldEditArrayList = new ArrayList<BasketInfoResponse.ColdProduct>();
    ArrayList<BasketInfoResponse.FrozenProduct> frozenEditArrayList = new ArrayList<BasketInfoResponse.FrozenProduct>();
    ArrayList<BasketInfoResponse.NormalProduct> normalEditArrayList = new ArrayList<BasketInfoResponse.NormalProduct>();
    ArrayList<BasketChangeBody.Option> basketArrayList = new ArrayList<BasketChangeBody.Option>();
    ArrayList<Integer> payBillArrayList = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        tryGetBasketInfo();

        //toolbar
        Toolbar toolbar = findViewById(R.id.basket_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);


        //주문하기 버튼 클릭 이벤트
        LinearLayout button = (LinearLayout) findViewById(R.id.basket_order_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMakeBasketChangeBody();
                tryPatchBasketChange();
                onMakePayBillBody();
                if (payBillArrayList.size() != 0) {
                    //주문할 게 있을 때만 주문서 화면으로!
                    //주문서 페이지에서 로드하기 위해 인텐트로 어레이리스트를 보낸다
                    Intent intent = new Intent(getApplicationContext(), PayActivity.class);
                    intent.putIntegerArrayListExtra("payBillArrayList",payBillArrayList);
                    startActivity(intent);//액티비티 띄우기
                } else {
                    Log.d("pay", String.valueOf(payBillArrayList));
                }
            }
        });


        lv_cold = findViewById(R.id.basket_cold_lv);
        lv_frozen = findViewById(R.id.basket_frozen_lv);
        lv_normal = findViewById(R.id.basket_normal_lv);

        lv_cold.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ImageButton delete = (ImageButton) view. findViewById(R.id.basket_delete_btn);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickHandler("삭제하시겠습니까?");
                        if (mDelete) { coldArrayList.remove(position); coldAdapter.notifyDataSetChanged(); }
                    }
                });
            }
        });

        lv_frozen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ImageButton delete = (ImageButton) view. findViewById(R.id.basket_delete_btn);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickHandler("삭제하시겠습니까?");
                        if (mDelete) { frozenArrayList.remove(position); frozenAdapter.notifyDataSetChanged(); }
                    }
                });
            }
        });

        lv_normal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ImageButton delete = (ImageButton) view. findViewById(R.id.basket_delete_btn);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickHandler("삭제하시겠습니까?");
                        if (mDelete) { normalArrayList.remove(position); normalAdapter.notifyDataSetChanged(); }
                    }
                });
            }
        });


    }

    //toolbar 메뉴가 눌렸을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                //장바구니 수량 수정
                onMakeBasketChangeBody();
                //장바구니 수량이 0이 될 일은 없음
                //한번도 안 고치면 디폴트가 0이라서 0임,, onMakeBasketChangeBody 고치자
                tryPatchBasketChange();
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void onMakeBasketChangeBody() {
        //장바구니 수량 수정 바디에 담아서 보낼 어레이리스트를 만든다.
        //장바구니 수량 수정에는 count 가 1이상
        //check 유무는 중요하지 x (체크 안되어 있어도 count 업데이트)
        int j = 0;
        for (int i = 0; i < coldEditArrayList.size(); i++) {
            if (coldEditArrayList.get(i).getCount() != 0) {
                basketArrayList.add(j, new BasketChangeBody.Option(
                        coldEditArrayList.get(i).getCount(),
                        coldEditArrayList.get(i).getOptionIdx()));
                j += 1;
            }
        }
        for (int i = 0; i < frozenEditArrayList.size(); i++) {
            if (frozenEditArrayList.get(i).getCount() != 0) {
                basketArrayList.add(j, new BasketChangeBody.Option(
                        frozenEditArrayList.get(i).getCount(),
                        frozenEditArrayList.get(i).getOptionIdx()));
                j += 1;
            }
        }
        for (int i = 0; i < normalEditArrayList.size(); i++) {
            if (normalEditArrayList.get(i).getCount() != 0) {
                basketArrayList.add(j, new BasketChangeBody.Option(
                        normalEditArrayList.get(i).getCount(),
                        normalEditArrayList.get(i).getOptionIdx()));
                j += 1;
            }
        }
    }

    public void onMakePayBillBody() {
        //check 가 true 인 옵션아이디만 add
        for (int i = 0; i < coldEditArrayList.size(); i++) {
            if (coldEditArrayList.get(i).getChecked()) {
                payBillArrayList.add(coldEditArrayList.get(i).getOptionIdx());
            }
        }
        for (int i = 0; i < frozenEditArrayList.size(); i++) {
            if (frozenEditArrayList.get(i).getChecked()) {
                payBillArrayList.add(frozenEditArrayList.get(i).getOptionIdx());
            }
        }
        for (int i = 0; i < normalEditArrayList.size(); i++) {
            if (normalEditArrayList.get(i).getChecked()) {
                payBillArrayList.add(normalEditArrayList.get(i).getOptionIdx());
            }
        }
    }

    public void onClickHandler(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BasketActivity.this);

        builder.setTitle(message);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) { }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) { }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View v) { alertDialog.dismiss(); mDelete = true; }});
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View v) { alertDialog.dismiss(); mDelete = false; }});

    }

    //리스트뷰 높이 조절
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


    private void tryGetBasketInfo() {
        //showProgressDialog();

        final BasketService basketService = new BasketService(this);
        basketService.getBasketInfo();
    }

    private void tryPatchBasketChange() {
        //showProgressDialog();
        final BasketService basketService = new BasketService(this);
        BasketChangeBody basketChangeBody = new BasketChangeBody(basketArrayList);
        basketService.patchBasketChange(basketChangeBody);
    }

    @Override
    public void basketInfoSuccess(BasketInfoResponse basketInfoResponse) {
        LinearLayout cold_product = (LinearLayout) findViewById(R.id.basket_cold);
        LinearLayout frozen_product = (LinearLayout) findViewById(R.id.basket_frozen);
        LinearLayout normal_product = (LinearLayout) findViewById(R.id.basket_normal);
        TextView address = (TextView) findViewById(R.id.basket_address_tv);
        TextView select_count = (TextView) findViewById(R.id.basket_select_count);
        TextView select_count_all = (TextView) findViewById(R.id.basket_select_count_all);
        TextView select_delete = (TextView) findViewById(R.id.basket_select_delete);
        TextView product_price = (TextView) findViewById(R.id.basket_product_price);
        TextView sale_price = (TextView) findViewById(R.id.basket_sale_price);
        TextView shipping_price = (TextView) findViewById(R.id.basket_shipping_price);
        TextView total_price = (TextView) findViewById(R.id.basket_total_price);
        TextView profit = (TextView) findViewById(R.id.basket_profit);
        TextView order_price = (TextView) findViewById(R.id.basket_order_price);

        mProfit = basketInfoResponse.getResult().getPrice().getProfit();
        mTotalPrice = basketInfoResponse.getResult().getPrice().getPriceToPay();
        mTotalPriceBefore = mTotalPrice + basketInfoResponse.getResult().getPrice().getSalePrice();
        mProductCount = basketInfoResponse.getResult().getBasketCount();
        currentProductCount = mProductCount;
        totalPrice = mTotalPrice;
        totalPriceBefore = mTotalPriceBefore;
        //ㅎㅎ필요없어졌다
//        mPriceCold = basketInfoResponse.getResult().getTypePrice().get(1).getTotalClientPrice();
//        mPriceBeforeCold = basketInfoResponse.getResult().getTypePrice().get(1).getTotalOriginalPrice();
//        mPriceFrozen = basketInfoResponse.getResult().getTypePrice().get(0).getTotalClientPrice();
//        mPriceBeforeFrozen = basketInfoResponse.getResult().getTypePrice().get(0).getTotalOriginalPrice();
//        mPriceNormal = basketInfoResponse.getResult().getTypePrice().get(2).getTotalClientPrice();
//        mPriceBeforeNormal = basketInfoResponse.getResult().getTypePrice().get(2).getTotalOriginalPrice();

        coldArrayList = new ArrayList<>();
        frozenArrayList = new ArrayList<>();
        normalArrayList = new ArrayList<>();

        if (basketInfoResponse.getIsSuccess()) {
            address.setText(basketInfoResponse.getResult().getAddress());
            product_price.setText(String.valueOf(basketInfoResponse.getResult().getPrice().getTotalPrice()));
            sale_price.setText(String.valueOf(-basketInfoResponse.getResult().getPrice().getSalePrice()));
            shipping_price.setText(String.valueOf(basketInfoResponse.getResult().getPrice().getDelivery()));
            total_price.setText(String.valueOf(basketInfoResponse.getResult().getPrice().getPriceToPay()));
            int profit_price = (int) (mProfit *0.01*(basketInfoResponse.getResult().getPrice().getPriceToPay()));
            profit.setText(String.valueOf(profit_price));
            order_price.setText(String.valueOf(basketInfoResponse.getResult().getPrice().getPriceToPay()));
            select_count_all.setText(String.valueOf(basketInfoResponse.getResult().getBasketCount()));
            //default 로 모두 선택되어 있으니까
            select_count.setText(String.valueOf(basketInfoResponse.getResult().getBasketCount()));

            //lv_cold
            if (basketInfoResponse.getResult().getColdProduct().size() != 0) {
                cold_product.setVisibility(View.VISIBLE);
                for (int i = 0; i < basketInfoResponse.getResult().getColdProduct().size(); i++) {
                    coldArrayList.add(new BasketInfoResponse.ColdProduct(
                            basketInfoResponse.getResult().getColdProduct().get(i).getClientPrice(),
                            basketInfoResponse.getResult().getColdProduct().get(i).getNeedCount(),
                            basketInfoResponse.getResult().getColdProduct().get(i).getOptionIdx(),
                            basketInfoResponse.getResult().getColdProduct().get(i).getOptionName(),
                            basketInfoResponse.getResult().getColdProduct().get(i).getOriginalPrice(),
                            basketInfoResponse.getResult().getColdProduct().get(i).getProductIdx(),
                            basketInfoResponse.getResult().getColdProduct().get(i).getProductImg(),
                            basketInfoResponse.getResult().getColdProduct().get(i).getProductName(),
                            basketInfoResponse.getResult().getColdProduct().get(i).getType(),
                            basketInfoResponse.getResult().getColdProduct().get(i).getNeedCount(), true
                    ));
                }
                coldAdapter = new BasketColdAdapter(coldArrayList, getApplicationContext(), this);
                lv_cold.setAdapter(coldAdapter);
            } else {
                cold_product.setVisibility(View.GONE);
            }

            //lv_frozen
            if (basketInfoResponse.getResult().getFrozenProduct().size() != 0) {
                frozen_product.setVisibility(View.VISIBLE);
                for (int i = 0; i < basketInfoResponse.getResult().getFrozenProduct().size(); i++) {
                    frozenArrayList.add(new BasketInfoResponse.FrozenProduct(
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getClientPrice(),
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getNeedCount(),
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getOptionIdx(),
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getOptionName(),
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getOriginalPrice(),
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getProductIdx(),
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getProductImg(),
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getProductName(),
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getType(),
                            basketInfoResponse.getResult().getFrozenProduct().get(i).getNeedCount(), true
                    ));
                }
                frozenAdapter = new BasketFrozenAdapter(frozenArrayList, getApplicationContext(), this);
                lv_frozen.setAdapter(frozenAdapter);
            } else {
                frozen_product.setVisibility(View.GONE);
            }

            //lv_normal
            if (basketInfoResponse.getResult().getNormalProduct().size() != 0) {
                normal_product.setVisibility(View.VISIBLE);
                for (int i = 0; i < basketInfoResponse.getResult().getNormalProduct().size(); i++) {
                    normalArrayList.add(new BasketInfoResponse.NormalProduct(
                            basketInfoResponse.getResult().getNormalProduct().get(i).getClientPrice(),
                            basketInfoResponse.getResult().getNormalProduct().get(i).getNeedCount(),
                            basketInfoResponse.getResult().getNormalProduct().get(i).getOptionIdx(),
                            basketInfoResponse.getResult().getNormalProduct().get(i).getOptionName(),
                            basketInfoResponse.getResult().getNormalProduct().get(i).getOriginalPrice(),
                            basketInfoResponse.getResult().getNormalProduct().get(i).getProductIdx(),
                            basketInfoResponse.getResult().getNormalProduct().get(i).getProductImg(),
                            basketInfoResponse.getResult().getNormalProduct().get(i).getProductName(),
                            basketInfoResponse.getResult().getNormalProduct().get(i).getType(),
                            basketInfoResponse.getResult().getNormalProduct().get(i).getNeedCount(), true
                    ));
                }
                normalAdapter = new BasketNormalAdapter(normalArrayList, getApplicationContext(), this);
                lv_normal.setAdapter(normalAdapter);
            } else {
                normal_product.setVisibility(View.GONE);
            }

        }

        setListViewHeightBasedOnChildren(lv_cold);
        setListViewHeightBasedOnChildren(lv_frozen);
        setListViewHeightBasedOnChildren(lv_normal);


    }

    @Override
    public void basketInfoFailure(String message) {

    }

    @Override
    public void basketChangeSuccess(BasketChangeResponse basketChangeResponse) {
        Toast.makeText(getApplicationContext(), "장바구니 수량 수정 성공", Toast.LENGTH_LONG).show();
    }

    @Override
    public void deleteBasketItemSuccess(DeleteBasketItemResponse deleteBasketItemResponse) {

    }


    @Override
    public void onClickCold(int total_price_cold, int total_price_before_cold) {
        TextView product_price = (TextView) findViewById(R.id.basket_product_price);
        TextView sale_price = (TextView) findViewById(R.id.basket_sale_price);
        TextView total_price = (TextView) findViewById(R.id.basket_total_price);
        TextView profit = (TextView) findViewById(R.id.basket_profit);
        TextView order_price = (TextView) findViewById(R.id.basket_order_price);
        TextView pay_tv = (TextView) findViewById(R.id.basket_pay_tv);
        LinearLayout button = (LinearLayout) findViewById(R.id.basket_order_btn);
        LinearLayout pay_info_1 = (LinearLayout) findViewById(R.id.basket_pay_info_1);
        TextView pay_info_2 = (TextView) findViewById(R.id.basket_pay_info_2);

        deltaPriceCold = total_price_cold;
        deltaPriceBeforeCold = total_price_before_cold;

        totalPrice += deltaPriceCold;
        totalPriceBefore += deltaPriceBeforeCold;

        product_price.setText(String.valueOf(totalPriceBefore));
        sale_price.setText(String.valueOf(-totalPriceBefore + totalPrice));
        total_price.setText(String.valueOf(totalPrice));
        profit.setText(String.valueOf((int) (totalPrice * mProfit)));


        if ( totalPrice == 0 ) {
            order_price.setText("상품을 선택해주세요");
            pay_tv.setVisibility(View.GONE);
            button.setBackgroundColor(getResources().getColor(R.color.edit_text_hint));
            pay_info_1.setVisibility(View.GONE);
            pay_info_2.setVisibility(View.GONE);
        } else {
            order_price.setText(String.valueOf(totalPrice));
            pay_tv.setVisibility(View.VISIBLE);
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            pay_info_1.setVisibility(View.VISIBLE);
            pay_info_2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClickFrozen(int total_price_frozen, int total_price_before_frozen) {
        TextView product_price = (TextView) findViewById(R.id.basket_product_price);
        TextView sale_price = (TextView) findViewById(R.id.basket_sale_price);
        TextView total_price = (TextView) findViewById(R.id.basket_total_price);
        TextView profit = (TextView) findViewById(R.id.basket_profit);
        TextView order_price = (TextView) findViewById(R.id.basket_order_price);
        TextView pay_tv = (TextView) findViewById(R.id.basket_pay_tv);
        LinearLayout button = (LinearLayout) findViewById(R.id.basket_order_btn);
        LinearLayout pay_info_1 = (LinearLayout) findViewById(R.id.basket_pay_info_1);
        TextView pay_info_2 = (TextView) findViewById(R.id.basket_pay_info_2);

        deltaPriceFrozen = total_price_frozen;
        deltaPriceBeforeFrozen = total_price_before_frozen;

        totalPrice += deltaPriceFrozen;
        totalPriceBefore += deltaPriceBeforeCold;

        product_price.setText(String.valueOf(totalPriceBefore));
        sale_price.setText(String.valueOf(-totalPriceBefore + totalPrice));
        total_price.setText(String.valueOf(totalPrice));
        profit.setText(String.valueOf((int) (totalPrice * mProfit)));

        if ( totalPrice == 0 ) {
            order_price.setText("상품을 선택해주세요");
            pay_tv.setVisibility(View.GONE);
            button.setBackgroundColor(getResources().getColor(R.color.edit_text_hint));
            pay_info_1.setVisibility(View.GONE);
            pay_info_2.setVisibility(View.GONE);
        } else {
            order_price.setText(String.valueOf(totalPrice));
            pay_tv.setVisibility(View.VISIBLE);
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            pay_info_1.setVisibility(View.VISIBLE);
            pay_info_2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClickNormal(int total_price_normal, int total_price_before_normal) {
        TextView product_price = (TextView) findViewById(R.id.basket_product_price);
        TextView sale_price = (TextView) findViewById(R.id.basket_sale_price);
        TextView total_price = (TextView) findViewById(R.id.basket_total_price);
        TextView profit = (TextView) findViewById(R.id.basket_profit);
        TextView order_price = (TextView) findViewById(R.id.basket_order_price);
        TextView pay_tv = (TextView) findViewById(R.id.basket_pay_tv);
        LinearLayout button = (LinearLayout) findViewById(R.id.basket_order_btn);
        LinearLayout pay_info_1 = (LinearLayout) findViewById(R.id.basket_pay_info_1);
        TextView pay_info_2 = (TextView) findViewById(R.id.basket_pay_info_2);

        deltaPriceNormal = total_price_normal;
        deltaPriceBeforeCold = total_price_before_normal;

        totalPrice += deltaPriceNormal;
        totalPriceBefore += deltaPriceBeforeNormal;

        product_price.setText(String.valueOf(totalPriceBefore));
        sale_price.setText(String.valueOf(-totalPriceBefore + totalPrice));
        total_price.setText(String.valueOf(totalPrice));
        profit.setText(String.valueOf((int) (totalPrice * mProfit)));

        if ( totalPrice == 0 ) {
            order_price.setText("상품을 선택해주세요");
            pay_tv.setVisibility(View.GONE);
            button.setBackgroundColor(getResources().getColor(R.color.edit_text_hint));
            pay_info_1.setVisibility(View.GONE);
            pay_info_2.setVisibility(View.GONE);
        } else {
            order_price.setText(String.valueOf(totalPrice));
            pay_tv.setVisibility(View.VISIBLE);
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            pay_info_1.setVisibility(View.VISIBLE);
            pay_info_2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onColdDelivery(ArrayList arrayList) {
        coldEditArrayList = arrayList;
    }

    @Override
    public void onFrozenDelivery(ArrayList arrayList) {
        frozenEditArrayList = arrayList;
    }

    @Override
    public void onNormalDelivery(ArrayList arrayList) {
        normalEditArrayList = arrayList;
    }

    @Override
    public void onProductCountDelivery(int delta_product_count) {
        TextView select_count = (TextView) findViewById(R.id.basket_select_count);
        ImageView check_all = (ImageView) findViewById(R.id.basket_check_all);
        Drawable check_o = getResources().getDrawable(R.drawable.check_checked);
        Drawable check_x = getResources().getDrawable(R.drawable.check_unchecked);

        currentProductCount += delta_product_count;
        select_count.setText(String.valueOf(currentProductCount));

        if ( currentProductCount == mProductCount ) {
            //전체선택
            check_all.setBackground(check_o);
        } else {
            check_all.setBackground(check_x);
        }
    }

}