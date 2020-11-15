package com.example.marketkurly.src.select;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketkurly.R;

import com.example.marketkurly.src.login.LoginActivity;
import com.example.marketkurly.src.select.interfaces.OnItemClick;
import com.example.marketkurly.src.select.interfaces.SelectActivityView;
import com.example.marketkurly.src.select.models.BasketAddBody;
import com.example.marketkurly.src.select.models.BasketAddResponse;
import com.example.marketkurly.src.select.models.SelectResponse;

import java.util.ArrayList;

import static com.example.marketkurly.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.marketkurly.src.ApplicationClass.sSharedPreferences;

public class SelectActivity extends AppCompatActivity implements SelectActivityView, OnItemClick {
    private ArrayList<SelectData> selectArrayList;
    private SelectAdapter selectAdapter;
    private ListView listView;
//    private RecyclerView recyclerView;
//    private LinearLayoutManager linearLayoutManager;
    boolean isUser;
    String productName;
    int productIdx, price, optionSize;
    float profit;

    ArrayList<SelectData> selectEditArrayList = new ArrayList<SelectData>();
    ArrayList<BasketAddBody.Option> basketArrayList = new ArrayList<BasketAddBody.Option>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        productIdx = sSharedPreferences.getInt("productIdx", 0);
        tryGetSelect();


        //toolbar
        Toolbar toolbar = findViewById(R.id.select_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

//        recyclerView = (RecyclerView) findViewById(R.id.select_rv);
//        recyclerView.setHasFixedSize(true); //일정한 크기 사용
//        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //LinearLayoutManager 는 리스트뷰(가로/세로)
//        recyclerView.setLayoutManager(linearLayoutManager);

        listView = findViewById(R.id.select_lv);


        //장바구니에 담기 버튼 클릭 이벤트
        Button button = (Button) findViewById(R.id.select_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //먼저 바디에 담아서 보낼 어레이리스트를 만든다.
                int j=0;
                for (int i=0; i<optionSize; i++) {
                    Log.d("count", String.valueOf(selectEditArrayList.get(i).getCount()));

                    if (selectEditArrayList.get(i).getCount() != 0) {
//                        basketArrayList.set(i, new BasketAddBody.Option(selectArrayList.get(i).getCount(),
//                                selectArrayList.get(i).getTv_option_id(),
//                                selectArrayList.get(i).getTv_product_id()));
                      //  try {
                            basketArrayList.add(j, new BasketAddBody.Option(
                                    selectEditArrayList.get(i).getCount(),
                                    selectEditArrayList.get(i).getTv_option_id(),
                                    selectEditArrayList.get(i).getTv_product_id()));
                            j+=1;
                    //    } catch (IndexOutOfBoundsException e) {
                     //       e.printStackTrace();
                     //   }

                    }
                }

                if ( basketArrayList != null) { tryPostBasketAdd(); }

            }
        });


    }

    //toolbar 메뉴가 눌렸을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void tryGetSelect() {
        //showProgressDialog();

        final SelectService selectService = new SelectService(this);
        selectService.getSelect(productIdx);
    }

    private void tryPostBasketAdd() {
        //showProgressDialog();

        final SelectService selectService = new SelectService(this);
        BasketAddBody basketAddBody = new BasketAddBody(basketArrayList);
        selectService.postBasketAdd(basketAddBody);
    }

    @Override
    public void selectSuccess(SelectResponse selectResponse) {
        LinearLayout product = (LinearLayout) findViewById(R.id.select_product);
        TextView product_name = (TextView) findViewById(R.id.tv_product_name);
        TextView user_true1 = (TextView) findViewById(R.id.select_user_true1);
        TextView user_true2 = (TextView) findViewById(R.id.select_user_true2);
        TextView user_false = (TextView) findViewById(R.id.select_user_false);

        product_name.setText(selectResponse.getResult().getOption().get(0).getProductName());

        if (sSharedPreferences.getString(X_ACCESS_TOKEN, null) != null) {
            //로그인이 되어있을 때
            isUser = true;
            profit = selectResponse.getResult().getProfit();
            user_true1.setVisibility(View.VISIBLE);
            user_true2.setVisibility(View.VISIBLE);
            user_false.setVisibility(View.GONE);
        } else {
            isUser = false;
            profit = 0;
            user_true1.setVisibility(View.GONE);
            user_true2.setVisibility(View.GONE);
            user_false.setVisibility(View.VISIBLE);
        }
        if (selectResponse.getResult().getOption().size() > 1) {
            //여러개의 옵션이 있을 때 프로덕트 네임 저장
            productName = selectResponse.getResult().getOption().get(0).getProductName();
            product.setVisibility(View.VISIBLE);
            //옵션 몇 개인지 세놓기
            optionSize = selectResponse.getResult().getOption().size();
        } else {
            product.setVisibility(View.GONE);
            //옵션 하나인 걸로!
            optionSize = 1;
        }
        //lv
        selectArrayList = new ArrayList<>();
        if (selectResponse.getIsSuccess()) {
            for (int i = 0; i < selectResponse.getResult().getOption().size(); i++) {
                selectArrayList.add(new SelectData(
                        selectResponse.getResult().getOption().get(i).getProductIdx(),
                        selectResponse.getResult().getOption().get(i).getOptionIdx(),
                        selectResponse.getResult().getOption().get(i).getOriginalPrice(),
                        selectResponse.getResult().getOption().get(i).getClientPrice(),
                        0,
                        selectResponse.getResult().getOption().get(i).getOptionName(),
                        String.valueOf(selectResponse.getResult().getOption().get(i).getIsSoldOut())));
            }
            selectAdapter = new SelectAdapter(selectArrayList, getApplicationContext(), this);
            listView.setAdapter(selectAdapter);
        }

    }



    @Override
    public void selectFailure(String message) {

    }

    @Override
    public void basketAddSuccess(BasketAddResponse basketAddResponse) {
        String message = basketAddResponse.getMessage();
        Log.d("message", message);
        //'장바구니에 상품을 담았습니다' 커스텀 토스트 메시지 띄워주기
        View view = View.inflate(this, R.layout.toast_basket_add, null);
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();

    }

    @Override
    public void onClick(int total_price) {
        TextView total_price_tv = (TextView) findViewById(R.id.select_total_price);
        TextView user_true2 = (TextView) findViewById(R.id.select_user_true2);

        total_price_tv.setText(String.valueOf(total_price)+"원");
        user_true2.setText(String.valueOf(total_price*profit)+"원 적립");
    }

    @Override
    public void onDelivery(ArrayList arrayList) {
        selectEditArrayList = arrayList;
    }
}