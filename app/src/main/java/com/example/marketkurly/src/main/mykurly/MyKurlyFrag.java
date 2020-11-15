package com.example.marketkurly.src.main.mykurly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.example.marketkurly.R;
import com.example.marketkurly.src.login.LoginActivity;
import com.example.marketkurly.src.login.models.UserInfoResponse;
import com.example.marketkurly.src.main.MainActivity;
import com.example.marketkurly.src.main.mykurly.interfaces.MyKurlyActivityView;

import static android.app.Application.getProcessName;
import static com.example.marketkurly.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.marketkurly.src.ApplicationClass.sSharedPreferences;

public class MyKurlyFrag extends Fragment implements MyKurlyActivityView {
    private View view;

    LinearLayout signup_page, my_page, list1, list2;
    ConstraintLayout non_member_order, kurly_pass, edit_personal, logout;
    ImageButton ad;
    TextView level, name, profit, point, coupon_count;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_my_kurly, container, false);

        //보여줬다가 안 보여줬다가 할 애들
        signup_page = (LinearLayout) view.findViewById(R.id.mykurly_signup_page);
        my_page = (LinearLayout) view.findViewById(R.id.mykurly_my_page);
        non_member_order = (ConstraintLayout) view.findViewById(R.id.non_member_order);
        ad = (ImageButton) view.findViewById(R.id.mykurly_ad);
        list1 = (LinearLayout) view.findViewById(R.id.mykurly_list_1);
        list2 = (LinearLayout) view.findViewById(R.id.mykurly_list_2);
        kurly_pass = (ConstraintLayout) view.findViewById(R.id.mykurly_kurlypass);
        edit_personal = (ConstraintLayout) view.findViewById(R.id.mykurly_edit_personal);
        logout = (ConstraintLayout) view.findViewById(R.id.mykurly_logout);

        //값을 받아서 넣어줘야 하는 애들
        level = (TextView) view.findViewById(R.id.mykurly_level);
        name = (TextView) view.findViewById(R.id.mykurly_name);
        profit = (TextView) view.findViewById(R.id.mykurly_profit);
        point = (TextView) view.findViewById(R.id.mykurly_point);
        coupon_count = (TextView) view.findViewById(R.id.mykurly_coupon_count);

        //로그인/회원가입 버튼 클릭시 로그인 액티비티로 이동
        Button button = (Button) view.findViewById(R.id.mykurly_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 103);
            }
        });


        //로그아웃 버튼 눌렀을 때
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //토큰 삭제하고 다시 회원정보조회 api 쏘기
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove(X_ACCESS_TOKEN);
                editor.commit();
            }
        });

        //자동로그인 여부 받아오기
        //MainActivity 에서 전달한 번들 저장
        Bundle bundle = getArguments();
        //번들 안의 boolean 불러오기
        boolean isUser =  bundle.getBoolean("isUser");
        //그냥 토큰 있는지 확인하고 했어도 됐는데,,,
        if (isUser) { tryGetUserInfo(); }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 103) {

            if (resultCode == 104) {
                Boolean isSuccess = data.getBooleanExtra("isSuccess", false);
                String name_login = data.getStringExtra("userName");
                String level_login = data.getStringExtra("level");
                String point_login = data.getStringExtra("point");
                String coupon_count_login = data.getStringExtra("couponCount");
                float profit_login = data.getFloatExtra("profit", 0);

                if (isSuccess) { //회원정보가 있을 떄
                    //보여줄 것들(visible)
                    my_page.setVisibility(View.VISIBLE);
                    ad.setVisibility(View.VISIBLE);
                    list1.setVisibility(View.VISIBLE);
                    list2.setVisibility(View.VISIBLE);
                    kurly_pass.setVisibility(View.VISIBLE);
                    edit_personal.setVisibility(View.VISIBLE);
                    logout.setVisibility(View.VISIBLE);

                    //값을 넣어줄 것들
                    name.setText(name_login);
                    level.setText(level_login);
                    point.setText(point_login);
                    coupon_count.setText(coupon_count_login);
                    profit.setText(Float.toString(profit_login));

                    //안 보여줄 것들(gone)
                    signup_page.setVisibility(View.GONE);
                    non_member_order.setVisibility(View.GONE);

                } else { //회원정보가 없을 때 <- 로그아웃 시 회원정보조회 api 다시쏠 때 이렇게 되겠지
                    //보여줄 것들(visible)
                    signup_page.setVisibility(View.VISIBLE);
                    non_member_order.setVisibility(View.VISIBLE);

                    //안 보여줄 것들(gone)
                    my_page.setVisibility(View.GONE);
                    ad.setVisibility(View.GONE);
                    list1.setVisibility(View.GONE);
                    list2.setVisibility(View.GONE);
                    kurly_pass.setVisibility(View.GONE);
                    edit_personal.setVisibility(View.GONE);
                    logout.setVisibility(View.GONE);
                }


            }
        }
    }

    private void tryGetUserInfo() {
        //showProgressDialog();

        final MyKurlyService myKurlyService = new MyKurlyService(this);
        myKurlyService.getUserInfo();
    }

    @Override
    public void userInfoSuccess(UserInfoResponse userInfoResponse) {

        Boolean isSuccess_auto = userInfoResponse.getIsSuccess();
        String name_login_auto = userInfoResponse.getResult().getUserName();
        String level_login_auto = userInfoResponse.getResult().getLevel();
        String point_login_auto = userInfoResponse.getResult().getPoint();
        String coupon_count_login_auto = userInfoResponse.getResult().getCouponCount();
        float profit_login_auto = userInfoResponse.getResult().getProfit();

        if (isSuccess_auto) { //회원정보가 있을 떄
            //보여줄 것들(visible)
            my_page.setVisibility(View.VISIBLE);
            ad.setVisibility(View.VISIBLE);
            list1.setVisibility(View.VISIBLE);
            list2.setVisibility(View.VISIBLE);
            kurly_pass.setVisibility(View.VISIBLE);
            edit_personal.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);

            //값을 넣어줄 것들
            name.setText(name_login_auto);
            level.setText(level_login_auto);
            point.setText(point_login_auto);
            coupon_count.setText(coupon_count_login_auto);
            profit.setText(Float.toString(profit_login_auto));

            //안 보여줄 것들(gone)
            signup_page.setVisibility(View.GONE);
            non_member_order.setVisibility(View.GONE);

        } else { //회원정보가 없을 때 <- 로그아웃 시 회원정보조회 api 다시쏠 때 이렇게 되겠지
            //보여줄 것들(visible)
            signup_page.setVisibility(View.VISIBLE);
            non_member_order.setVisibility(View.VISIBLE);

            //안 보여줄 것들(gone)
            my_page.setVisibility(View.GONE);
            ad.setVisibility(View.GONE);
            list1.setVisibility(View.GONE);
            list2.setVisibility(View.GONE);
            kurly_pass.setVisibility(View.GONE);
            edit_personal.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
        }

    }

    @Override
    public void userInfoFailure(String message) {

    }
}