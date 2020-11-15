package com.example.marketkurly.src.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.marketkurly.R;
import com.example.marketkurly.src.BaseActivity;
import com.example.marketkurly.src.basket.BasketActivity;
import com.example.marketkurly.src.main.category.CategoryFrag;
import com.example.marketkurly.src.main.home.HomeFrag;
import com.example.marketkurly.src.main.interfaces.MainActivityView;
import com.example.marketkurly.src.main.mykurly.MyKurlyFrag;
import com.example.marketkurly.src.main.recommend.RecommendFrag;
import com.example.marketkurly.src.main.search.SearchFrag;
import com.example.marketkurly.src.pay.PayActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements MainActivityView {

    private BottomNavigationView bottomNavigationView;
    Menu menu;
    private FragmentManager fm;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton button = findViewById(R.id.toolbar_shopping_basket);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);//액티비티 띄우기
            }
        });

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(false);
        final TextView toolbar_title = toolbar.findViewById(R.id.toolbar_title);

        //bottom navigation view 프래그먼트 전환(재생성x, hide&show)
        final Fragment frag1 = new HomeFrag();
        final Fragment frag2 = new RecommendFrag();
        final Fragment frag3 = new CategoryFrag();
        final Fragment frag4 = new SearchFrag();
        final Fragment frag5 = new MyKurlyFrag();
        final FragmentManager fm = getSupportFragmentManager();
        final Fragment[] active = {frag1};

        fm.beginTransaction().add(R.id.main_frame, frag5, "5").hide(frag5).commit();
        fm.beginTransaction().add(R.id.main_frame, frag4, "4").hide(frag4).commit();
        fm.beginTransaction().add(R.id.main_frame, frag3, "3").hide(frag3).commit();
        fm.beginTransaction().add(R.id.main_frame, frag2, "2").hide(frag2).commit();
        fm.beginTransaction().add(R.id.main_frame, frag1, "1").commit(); //첫 프래그먼트 화면만 hide 안 함.

        bottomNavigationView = findViewById(R.id.bottomMenu);
        menu=bottomNavigationView.getMenu();

        menu.findItem(R.id.main_home).setIcon(R.drawable.home_selected);    //첫 화면, 홈을 선택된 이미지로

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_home:
                        fm.beginTransaction().hide(active[0]).show(frag1).commit();
                        active[0] = frag1;
                        toolbar.findViewById(R.id.toolbar_logo).setVisibility(View.VISIBLE);
                        toolbar.findViewById(R.id.toolbar_title).setVisibility(View.INVISIBLE);
                        menu.findItem(R.id.main_home).setIcon(R.drawable.home_selected);    // 선택한 이미지 변경
                        menu.findItem(R.id.main_recommend).setIcon(R.drawable.recommend_unselected);
                        menu.findItem(R.id.main_category).setIcon(R.drawable.category_unselected);
                        menu.findItem(R.id.main_search).setIcon(R.drawable.search_unselected);
                        menu.findItem(R.id.main_my_kurly).setIcon(R.drawable.mykurly_unselected);
                        return true;
                    case R.id.main_recommend:
                        fm.beginTransaction().hide(active[0]).show(frag2).commit();
                        active[0] = frag2;
                        toolbar.findViewById(R.id.toolbar_logo).setVisibility(View.INVISIBLE);
                        toolbar_title.setText("추천");
                        toolbar.findViewById(R.id.toolbar_title).setVisibility(View.VISIBLE);
                        menu.findItem(R.id.main_home).setIcon(R.drawable.home_unselected);
                        menu.findItem(R.id.main_recommend).setIcon(R.drawable.recommend_selected);    // 선택한 이미지 변경
                        menu.findItem(R.id.main_category).setIcon(R.drawable.category_unselected);
                        menu.findItem(R.id.main_search).setIcon(R.drawable.search_unselected);
                        menu.findItem(R.id.main_my_kurly).setIcon(R.drawable.mykurly_unselected);
                        return true;
                    case R.id.main_category:
                        fm.beginTransaction().hide(active[0]).show(frag3).commit();
                        active[0] = frag3;
                        toolbar.findViewById(R.id.toolbar_logo).setVisibility(View.INVISIBLE);
                        toolbar_title.setText("카테고리");
                        toolbar.findViewById(R.id.toolbar_title).setVisibility(View.VISIBLE);
                        menu.findItem(R.id.main_home).setIcon(R.drawable.home_unselected);
                        menu.findItem(R.id.main_recommend).setIcon(R.drawable.recommend_unselected);
                        menu.findItem(R.id.main_category).setIcon(R.drawable.category_selected);    // 선택한 이미지 변경
                        menu.findItem(R.id.main_search).setIcon(R.drawable.search_unselected);
                        menu.findItem(R.id.main_my_kurly).setIcon(R.drawable.mykurly_unselected);
                        return true;
                    case R.id.main_search:
                        fm.beginTransaction().hide(active[0]).show(frag4).commit();
                        active[0] = frag4;
                        toolbar.findViewById(R.id.toolbar_logo).setVisibility(View.INVISIBLE);
                        toolbar_title.setText("검색");
                        toolbar.findViewById(R.id.toolbar_title).setVisibility(View.VISIBLE);
                        menu.findItem(R.id.main_home).setIcon(R.drawable.home_unselected);
                        menu.findItem(R.id.main_recommend).setIcon(R.drawable.recommend_unselected);
                        menu.findItem(R.id.main_category).setIcon(R.drawable.category_unselected);
                        menu.findItem(R.id.main_search).setIcon(R.drawable.search_selected);    // 선택한 이미지 변경
                        menu.findItem(R.id.main_my_kurly).setIcon(R.drawable.mykurly_unselected);
                        return true;
                    case R.id.main_my_kurly:
                        fm.beginTransaction().hide(active[0]).show(frag5).commit();
                        active[0] = frag5;
                        toolbar.findViewById(R.id.toolbar_logo).setVisibility(View.INVISIBLE);
                        toolbar_title.setText("마이컬리");
                        toolbar.findViewById(R.id.toolbar_title).setVisibility(View.VISIBLE);
                        menu.findItem(R.id.main_home).setIcon(R.drawable.home_unselected);
                        menu.findItem(R.id.main_recommend).setIcon(R.drawable.recommend_unselected);
                        menu.findItem(R.id.main_category).setIcon(R.drawable.category_unselected);
                        menu.findItem(R.id.main_search).setIcon(R.drawable.search_unselected);
                        menu.findItem(R.id.main_my_kurly).setIcon(R.drawable.mykurly_selected);    // 선택한 이미지 변경
                        return true;
                }
                return false;
            }
        });

        //SplashActivity 의 인텐트를 받아서 저장
        Intent intent = getIntent();
        boolean isUser = intent.getBooleanExtra("isUser", false);
        //번들객체 생성, text값 저장
        Bundle bundle = new Bundle();
        bundle.putBoolean("isUser", isUser);
        //MyKurlyFrag 로 번들 전달
        frag5.setArguments(bundle);


    }
    private void tryGetTest() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getTest();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();

    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }




//    public void customOnClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
//            default:
//                break;
//        }
//    }
}