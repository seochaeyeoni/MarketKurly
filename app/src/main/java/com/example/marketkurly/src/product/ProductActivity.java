package com.example.marketkurly.src.product;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.marketkurly.R;
import com.example.marketkurly.src.login.LoginActivity;
import com.example.marketkurly.src.product.ProductService;
import com.example.marketkurly.src.product.ViewPagerAdapter;
import com.example.marketkurly.src.product.detail.ProductDetailFrag;
import com.example.marketkurly.src.product.image.ProductImageFrag;
import com.example.marketkurly.src.product.info.ProductInfoFrag;
import com.example.marketkurly.src.product.inquiry.ProductInquiryFrag;
import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.models.ProductResponse;
import com.example.marketkurly.src.product.review.ProductReviewFrag;
import com.example.marketkurly.src.select.SelectActivity;
import com.google.android.material.tabs.TabLayout;

import static com.example.marketkurly.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.marketkurly.src.ApplicationClass.sSharedPreferences;

public class ProductActivity extends AppCompatActivity implements ProductActivityView {

    private TabLayout mTabLayout;

    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        //상품 id 받기
        Intent intent = getIntent();
        id = getIntent().getIntExtra("id",1);

        //상품 id 저장
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        int productIdx = id; // 사용자가 입력한 저장할 데이터
        editor.putInt("productIdx",productIdx); // key, value를 이용하여 저장하는 형태
        editor.commit(); //최종 커밋

        //toolbar
        Toolbar toolbar = findViewById(R.id.product_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        //tablayout + viewpager
        mTabLayout = (TabLayout) findViewById(R.id.detail_tab);
        mViewPager = (ViewPager) findViewById(R.id.detail_viewpager);
        mViewPagerAdapter = new ViewPagerAdapter(
                this.getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //구매하기 버튼 눌렀을 때
        Button buying = (Button) findViewById(R.id.product_btn);
        buying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
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

    private void tryGetProduct() {
        //showProgressDialog();

        final ProductService productService = new ProductService(this);
        productService.getProduct(id);
    }

    @Override
    public void productSuccess(ProductResponse productResponse) {


    }

    @Override
    public void productFailure(String message) {

    }

    @Override
    public void productInfoSuccess(ProductResponse productResponse) {

    }

    @Override
    public void productImageSuccess(ProductResponse productResponse) {

    }

    @Override
    public void productDetailSuccess(ProductResponse productResponse) {

    }

    @Override
    public void productReviewSuccess(ProductResponse productResponse) {

    }

    @Override
    public void productInquirySuccess(ProductResponse productResponse) {

    }
}