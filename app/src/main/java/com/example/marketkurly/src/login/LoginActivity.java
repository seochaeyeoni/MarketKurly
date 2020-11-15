package com.example.marketkurly.src.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.marketkurly.R;
import com.example.marketkurly.src.basket.BasketActivity;
import com.example.marketkurly.src.login.interfaces.LoginActivityView;
import com.example.marketkurly.src.login.models.LoginBody;
import com.example.marketkurly.src.login.models.LoginResponse;
import com.example.marketkurly.src.login.models.UserInfoResponse;
import com.example.marketkurly.src.main.MainActivity;
import com.example.marketkurly.src.signup.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import static com.example.marketkurly.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.marketkurly.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {
    String userId, password, tokenId;
    boolean isSucces;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //toolbar
        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        //회원가입 버튼 클릭시 회원가입 액티비티로 이동
        Button signupButton = (Button) findViewById(R.id.login_signup_btn);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);//액티비티 띄우기
            }
        });

        //아이디 패스워드 입력 값 저장
        final EditText login_id = (EditText) findViewById(R.id.login_id);
        final EditText login_pw = (EditText) findViewById(R.id.login_pw);
        login_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                userId = login_id.getText().toString().trim();
            }
        });
        login_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                password = login_pw.getText().toString().trim();
            }
        });

        //로그인 버튼 클릭 시 게스트로그인 api
        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryPostLogin();
            }
        });

        //기기 토큰 가져오기
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            //Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        tokenId = task.getResult();


                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        //Log.d(TAG, msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
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

    private void tryPostLogin() {
        //showProgressDialog();

        final LoginService loginService = new LoginService(this);
        LoginBody loginBody = new LoginBody(userId, password, tokenId);
        loginService.postLogin(loginBody);
    }

    private void tryGetUserInfo() {
        //showProgressDialog();

        final LoginService loginService = new LoginService(this);
        loginService.getUserInfo();
    }


    @Override
    public void loginSuccess(LoginResponse loginResponse) {
        //hideProgressDialog();
        Log.d("token", tokenId);

        if (loginResponse.getIsSuccess()) {
            //jwt 값 SharedPreference 에 저장
            //SharedPreferences를 sFile이름, 기본모드로 설정
            sSharedPreferences = getSharedPreferences(X_ACCESS_TOKEN,MODE_PRIVATE);
            //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            String jwt = loginResponse.getResult().getJwt(); // 사용자가 입력한 저장할 데이터
            editor.putString(X_ACCESS_TOKEN,jwt); // key, value를 이용하여 저장하는 형태
            editor.commit(); //최종 커밋

            Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_LONG).show();

//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//액티비티 띄우기

            //회원정보 조회하는 api 다시쏴서, 그 정보로 로그인 된 마이컬리 페이지 구성(등)
            tryGetUserInfo();

        } else {
            View view = View.inflate(LoginActivity.this, R.layout.toast_login, null);
            Toast toast = new Toast(LoginActivity.this);
            toast.setView(view);
            int yOffset = Math.max(0, view.getHeight() - toast.getYOffset());
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, yOffset);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void loginFailure(String message) {

    }

    @Override
    public void userInfoSuccess(UserInfoResponse userInfoResponse) {

        Intent intent = new Intent();
        intent.putExtra("isSuccess",userInfoResponse.getIsSuccess());
        intent.putExtra("userName",userInfoResponse.getResult().getUserName());
        intent.putExtra("level",userInfoResponse.getResult().getLevel());
        intent.putExtra("point",userInfoResponse.getResult().getPoint());
        intent.putExtra("couponCount",userInfoResponse.getResult().getCouponCount());
        intent.putExtra("profit",userInfoResponse.getResult().getProfit());

        setResult(104, intent);
        finish();
    }

    @Override
    public void userInfoFailure(String message) {

    }
}