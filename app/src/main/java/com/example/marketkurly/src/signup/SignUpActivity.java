package com.example.marketkurly.src.signup;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.marketkurly.R;
import com.example.marketkurly.src.BaseActivity;
import com.example.marketkurly.src.adress.AddressActivity;
import com.example.marketkurly.src.signup.interfaces.SignUpActivityView;
import com.example.marketkurly.src.signup.models.IdCheckResponse;
import com.example.marketkurly.src.signup.models.SignUpBody;
import com.example.marketkurly.src.signup.models.SignUpResponse;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {
    String userId=null; String password=null; String name=null; String email=null; String phoneNumber=null;
    String address="d"; String birthday=null; String gender=null; String recommenderId=null;
    String event=null; String acceptPrivacy="N"; String isSMS="N"; String isEmail="N";
    boolean idCheck = false; boolean pwCheck = false; boolean consent1 = false; boolean consent2 = false; boolean consent3 = false;
    int mIdCheckCode; boolean mIsSuccess; String mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //toolbar
        Toolbar toolbar = findViewById(R.id.sign_up_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        //에디트텍스트
        final EditText id = (EditText) findViewById(R.id.sign_up_id);
        final EditText pw = (EditText) findViewById(R.id.sign_up_pw);
        final EditText pw_check = (EditText) findViewById(R.id.sign_up_pw_check);
        final EditText name_edit = (EditText) findViewById(R.id.sign_up_name);
        final EditText email_edit = (EditText) findViewById(R.id.sign_up_email);
        final EditText mobile_edit = (EditText) findViewById(R.id.sign_up_mobile);
        //주소...해야함...일단 에디트텍스트로...
        //EditText address_edit = (EditText) findViewById(R.id.sign_up_address);
        final EditText yyyy = (EditText) findViewById(R.id.sign_up_birth_yyyy);
        final EditText mm = (EditText) findViewById(R.id.sign_up_birth_mm);
        final EditText dd = (EditText) findViewById(R.id.sign_up_birth_dd);
        final EditText option_id = (EditText) findViewById(R.id.sign_up_option_id_edit);
        final EditText option_event = (EditText) findViewById(R.id.sign_up_option_event_edit);


        //비밀번호 입력 값 안 보이게
        pw.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        pw_check.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        pw_check.setTransformationMethod(PasswordTransformationMethod.getInstance());

        //에디트텍스트 값 적용(유효성 검사 안 하는 것들)
        //address = address_edit.getText().toString(); //주소는 띄어쓰기 포함
        name_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                name = name_edit.getText().toString().trim();
            }
        });

        mobile_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                phoneNumber = mobile_edit.getText().toString().trim();
            }
        });

        dd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                birthday = yyyy.getText().toString().trim() + "-" + mm.getText().toString().trim() + "-" + dd.getText().toString().trim();
                if (birthday.equals("--")) {birthday = "";}
            }
        });

        option_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                recommenderId = option_id.getText().toString().trim();
            }
        });

        option_event.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                event = option_event.getText().toString().trim();
            }
        });



//        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//        if (yyyy!=null && mm!=null && dd!=null) {
//            try {
//                String birth = yyyy.getText().toString().trim() + "-" + mm.getText().toString().trim() + "-" + dd.getText().toString().trim();
//                birthday = transFormat.parse(birth);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }


        //에디트텍스트 클릭하면 조건 띄우기
        id.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LinearLayout layout1 = (LinearLayout) findViewById(R.id.sign_up_id_1);
                LinearLayout layout2 = (LinearLayout) findViewById(R.id.sign_up_id_2);
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.VISIBLE);
                return false;
            }
        });
        pw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LinearLayout layout1 = (LinearLayout) findViewById(R.id.sign_up_pw_1);
                LinearLayout layout2 = (LinearLayout) findViewById(R.id.sign_up_pw_2);
                LinearLayout layout3 = (LinearLayout) findViewById(R.id.sign_up_pw_3);
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.VISIBLE);
                return false;
            }
        });
        pw_check.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LinearLayout layout1 = (LinearLayout) findViewById(R.id.sign_up_pw_check_1);
                layout1.setVisibility(View.VISIBLE);
                return false;
            }
        });

        //id 유효성 검사
        final String idValidation_1_1 = "^[a-zA-Z]{6,16}$"; //6~16자의 영문
        final String idValidation_1_2 = "^[a-zA-Z0-9]{6,16}$"; //6~16자의 영문,숫자 조합
        final TextView id_1_sym = (TextView) findViewById(R.id.sign_up_id_1_sym);
        final TextView id_1_tv = (TextView) findViewById(R.id.sign_up_id_1_tv);
        final TextView id_2_sym = (TextView) findViewById(R.id.sign_up_id_2_sym);
        final TextView id_2_tv = (TextView) findViewById(R.id.sign_up_id_2_tv);
        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String input_id = id.getText().toString().trim();

                if (input_id.matches(idValidation_1_1) | input_id.matches(idValidation_1_2) && s.length()>0 ) {
                    id_1_sym.setText("✓");
                    id_1_sym.setTextColor(Color.parseColor("#10851a"));
                    id_1_tv.setTextColor(Color.parseColor("#10851a"));
                } else {
                    id_1_sym.setText("×");
                    id_1_sym.setTextColor(Color.parseColor("#ae150d"));
                    id_1_tv.setTextColor(Color.parseColor("#ae150d"));
                }

            }
        });

        //id 중복확인
        Button duplication_check_btn = (Button) findViewById(R.id.sign_up_id_btn);
        duplication_check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id_1_sym.getCurrentTextColor() == Color.parseColor("#10851a")){
                    //id 유효성 검사 통과했으면, id 중복체크하기
                    userId = id.getText().toString().trim();
                    tryGetIdCheck();
//                    원래 이 부분을 idCheckSuccess에서 해야 한다. 여기서 하면 비동기 처리(?)
//                    if(mIdCheckCode == 402){
//                        //중복되는 아이디가 있을 때
//                        onClickHandler("동일한 아이디가 이미 등록되어 있습니다");
//                        id_2_sym.setText("×");
//                        id_2_sym.setTextColor(Color.parseColor("#ae150d"));
//                        id_2_tv.setTextColor(Color.parseColor("#ae150d"));
//                    } else if (mIdCheckCode == 200){
//                        //아이디로 사용할 수 있을 때(id 중복확인!)
//                        onClickHandler("사용하실 수 있는 아이디입니다!");
//                        userId = id.getText().toString().trim();
//                        id_2_sym.setText("✓");
//                        id_2_sym.setTextColor(Color.parseColor("#10851a"));
//                        id_2_tv.setTextColor(Color.parseColor("#10851a"));
//                        idCheck = true;
//                    }
                } else {
                    onClickHandler("6자 이상의 영문 혹은 영문과 숫자를 조합으로 입력해주세요");
                }
            }
        });

        //pw 유효성 검사
        final String pwValidation_1_1 = "^[A-Za-z[0-9]]{10,16}$"; //10~16자의 영문,숫자 조합
        final String pwValidation_1_2 = "^[[A-Za-z]$@$!%*#?&]{10,16}$"; //10~16자의 특수문자,숫자 조합
        final String pwValidation_1_3 = "^[[0-9]$@$!%*#?&]{10,16}$"; //10~16자의 영문,특수문자 조합
        //final String pwValidation_2 = "^(\\\\d)\\\\1\\\\1"; //같은 숫자 3개 이상
        pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String input_pw = pw.getText().toString().trim();
                TextView pw_1_sym = (TextView) findViewById(R.id.sign_up_pw_1_sym);
                TextView pw_1_tv = (TextView) findViewById(R.id.sign_up_pw_1_tv);
                TextView pw_2_sym = (TextView) findViewById(R.id.sign_up_pw_2_sym);
                TextView pw_2_tv = (TextView) findViewById(R.id.sign_up_pw_2_tv);
                TextView pw_3_sym = (TextView) findViewById(R.id.sign_up_pw_3_sym);
                TextView pw_3_tv = (TextView) findViewById(R.id.sign_up_pw_3_tv);

                if (Integer.parseInt(String.valueOf(input_pw.length())) >= 10) {
                    Log.d("pw", (String.valueOf(input_pw.length())));
                    pw_1_sym.setText("✓");
                    pw_1_sym.setTextColor(Color.parseColor("#10851a"));
                    pw_1_tv.setTextColor(Color.parseColor("#10851a"));
                } else {
                    pw_1_sym.setText("×");
                    pw_1_sym.setTextColor(Color.parseColor("#ae150d"));
                    pw_1_tv.setTextColor(Color.parseColor("#ae150d"));
                }
                if (input_pw.matches(pwValidation_1_1) | input_pw.matches(pwValidation_1_2) | input_pw.matches(pwValidation_1_3) && s.length() > 0) {
                    pw_2_sym.setText("✓");
                    pw_2_sym.setTextColor(Color.parseColor("#10851a"));
                    pw_2_tv.setTextColor(Color.parseColor("#10851a"));
                } else {
                    pw_2_sym.setText("×");
                    pw_2_sym.setTextColor(Color.parseColor("#ae150d"));
                    pw_2_tv.setTextColor(Color.parseColor("#ae150d"));
                }
                if (input_pw.contains("000") | input_pw.contains("111") | input_pw.contains("222") | input_pw.contains("333") | input_pw.contains("444") | input_pw.contains("555") | input_pw.contains("666") | input_pw.contains("777") | input_pw.contains("888") | input_pw.contains("999")) {
                    pw_3_sym.setText("×");
                    pw_3_sym.setTextColor(Color.parseColor("#ae150d"));
                    pw_3_tv.setTextColor(Color.parseColor("#ae150d"));

                } else {
                    pw_3_sym.setText("✓");
                    pw_3_sym.setTextColor(Color.parseColor("#10851a"));
                    pw_3_tv.setTextColor(Color.parseColor("#10851a"));
                }

                //pw 유효성 검사 통과했으면, password에 값 입력
                if (pw_1_sym.getCurrentTextColor() == Color.parseColor("#10851a") && pw_2_sym.getCurrentTextColor() == Color.parseColor("#10851a") && pw_3_sym.getCurrentTextColor() == Color.parseColor("#10851a")) {
                    password = pw.getText().toString().trim();
                }
            }
        });



        //pw와 pw_check가 같은지
        pw_check.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView pw_check_1_sym = (TextView) findViewById(R.id.sign_up_pw_check_1_sym);
                TextView pw_check_1_tv = (TextView) findViewById(R.id.sign_up_pw_check_1_tv);
                if(pw.getText().toString().equals(pw_check.getText().toString())) {
                    pw_check_1_sym.setText("✓");
                    pw_check_1_sym.setTextColor(Color.parseColor("#10851a"));
                    pw_check_1_tv.setTextColor(Color.parseColor("#10851a"));
                    pwCheck = true;
                } else {
                    pw_check_1_sym.setText("×");
                    pw_check_1_sym.setTextColor(Color.parseColor("#ae150d"));
                    pw_check_1_tv.setTextColor(Color.parseColor("#ae150d"));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        //email 유효성 검사
        final String emailValidation = "^[a-z0-9_+.-]+@([a-z0-9-]+\\.)+[a-z0-9]{2,4}$";
        email_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String input_email = email_edit.getText().toString().trim();

                if (input_email.matches(emailValidation) && s.length()>0 ) {
                    email = input_email;
                } else {
                    email = null;
                }

            }
        });

        //주소
        LinearLayout search_address = (LinearLayout) findViewById(R.id.sign_up_search_address);
        search_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddressActivity.class);
                startActivityForResult(intent, 101);//액티비티 띄우기
            }
        });


        //성별 클릭 이벤트(해당 컬럼을 누르면 눌리게)
        LinearLayout gender_one = (LinearLayout) findViewById(R.id.sign_up_man);
        LinearLayout gender_two = (LinearLayout) findViewById(R.id.sign_up_woman);
        LinearLayout gender_three = (LinearLayout) findViewById(R.id.sign_up_no_gender);
        final ImageView iv_gender_1 = (ImageView) findViewById(R.id.sign_up_gender_1);
        final ImageView iv_gender_2 = (ImageView) findViewById(R.id.sign_up_gender_2);
        final ImageView iv_gender_3 = (ImageView) findViewById(R.id.sign_up_gender_3);
        final Drawable round_o = getResources().getDrawable(R.drawable.round_checked);
        final Drawable round_x = getResources().getDrawable(R.drawable.round_unchecked);

        gender_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_gender_1.getBackground() == round_o ) {
                    //iv_gender_1.setBackground(round_x);
                }
                else {
                    iv_gender_1.setBackground(round_o); gender = "남자";
                    iv_gender_2.setBackground(round_x);
                    iv_gender_3.setBackground(round_x);
                }
            }
        });
        gender_two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_gender_2.getBackground() == round_o ) {
                    //iv_gender_2.setBackground(round_x);

                }
                else {
                    iv_gender_2.setBackground(round_o); gender = "여자";
                    iv_gender_1.setBackground(round_x);
                    iv_gender_3.setBackground(round_x);
                }
            }
        });
        gender_three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_gender_3.getBackground() == round_o ) {
                    //iv_gender_3.setBackground(round_x);
                }
                else {
                    iv_gender_3.setBackground(round_o); gender = "선택안함";
                    iv_gender_1.setBackground(round_x);
                    iv_gender_2.setBackground(round_x);
                }
            }
        });

        //추가입력 사항 클릭 이벤트(해당 컬럼을 누르면 눌리게/에디트텍스트 띄우기)
        LinearLayout option_one = (LinearLayout) findViewById(R.id.sign_up_option_id);
        LinearLayout option_two = (LinearLayout) findViewById(R.id.sign_up_option_event);
        final ImageView iv_option_1 = (ImageView) findViewById(R.id.sign_up_option_1);
        final ImageView iv_option_2 = (ImageView) findViewById(R.id.sign_up_option_2);

        option_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                option_id.setVisibility(View.VISIBLE);
                option_event.setVisibility(View.GONE);
                event = null;
                if (iv_option_1.getBackground() == round_o ) {
                    //iv_option_1.setBackground(round_x);
                }
                else {
                    iv_option_1.setBackground(round_o);
                    iv_option_2.setBackground(round_x);
                }
            }
        });
        option_two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                option_id.setVisibility(View.GONE);
                option_event.setVisibility(View.VISIBLE);
                recommenderId = null;
                if (iv_option_2.getBackground() == round_o ) {
                    //iv_option_2.setBackground(round_x);
                }
                else {
                    iv_option_2.setBackground(round_o);
                    iv_option_1.setBackground(round_x);
                }
            }
        });


        //이용약관동의 클릭 이벤트(해당 컬럼을 누르면 눌리게)
        LinearLayout consent_all = (LinearLayout) findViewById(R.id.sign_up_consent_all);
        LinearLayout consent_1 = (LinearLayout) findViewById(R.id.sign_up_consent_1);
        LinearLayout consent_2 = (LinearLayout) findViewById(R.id.sign_up_consent_2);
        LinearLayout consent_3 = (LinearLayout) findViewById(R.id.sign_up_consent_3);
        LinearLayout consent_4 = (LinearLayout) findViewById(R.id.sign_up_consent_4);
        LinearLayout consent_5 = (LinearLayout) findViewById(R.id.sign_up_consent_5);
        LinearLayout consent_6 = (LinearLayout) findViewById(R.id.sign_up_consent_6);
        LinearLayout consent_7 = (LinearLayout) findViewById(R.id.sign_up_consent_7);
        final ImageView iv_consent_all = (ImageView) findViewById(R.id.sign_up_check_all);
        final ImageView iv_consent_1= (ImageView) findViewById(R.id.sign_up_check_1);
        final ImageView iv_consent_2= (ImageView) findViewById(R.id.sign_up_check_2);
        final ImageView iv_consent_3= (ImageView) findViewById(R.id.sign_up_check_3);
        final ImageView iv_consent_4= (ImageView) findViewById(R.id.sign_up_check_4);
        final ImageView iv_consent_5= (ImageView) findViewById(R.id.sign_up_check_5);
        final ImageView iv_consent_6= (ImageView) findViewById(R.id.sign_up_check_6);
        final ImageView iv_consent_7= (ImageView) findViewById(R.id.sign_up_check_7);
        final Drawable check_o = getResources().getDrawable(R.drawable.check_checked);
        final Drawable check_x = getResources().getDrawable(R.drawable.check_unchecked);

        consent_all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_consent_all.getBackground() == check_o ) {
                    iv_consent_all.setBackground(check_x);
                    iv_consent_1.setBackground(check_x); consent1 = false;
                    iv_consent_2.setBackground(check_x); consent2 = false;
                    iv_consent_3.setBackground(check_x); acceptPrivacy = "N";
                    iv_consent_4.setBackground(check_x); isSMS = "N"; isEmail = "N";
                    iv_consent_5.setBackground(check_x);
                    iv_consent_6.setBackground(check_x);
                    iv_consent_7.setBackground(check_x); consent3 = false;
                }
                else { iv_consent_all.setBackground(check_o);
                    iv_consent_1.setBackground(check_o); consent1 = true;
                    iv_consent_2.setBackground(check_o); consent2 = true;
                    iv_consent_3.setBackground(check_o); acceptPrivacy = "Y";
                    iv_consent_4.setBackground(check_o); isSMS = "Y"; isEmail = "Y";
                    iv_consent_5.setBackground(check_o);
                    iv_consent_6.setBackground(check_o);
                    iv_consent_7.setBackground(check_o); consent3 = true;
                }
            }
        });
        consent_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_consent_1.getBackground() == check_o ) {
                    iv_consent_all.setBackground(check_x);
                    iv_consent_1.setBackground(check_x); consent1 = false;
                }
                else { iv_consent_1.setBackground(check_o); consent1 = true; }
            }
        });
        consent_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_consent_2.getBackground() == check_o ) {
                    iv_consent_all.setBackground(check_x);
                    iv_consent_2.setBackground(check_x); consent2 = false;
                }
                else { iv_consent_2.setBackground(check_o); consent2 = true; }
            }
        });
        consent_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_consent_3.getBackground() == check_o ) {
                    iv_consent_all.setBackground(check_x);
                    iv_consent_3.setBackground(check_x); acceptPrivacy = "N";
                }
                else { iv_consent_3.setBackground(check_o); acceptPrivacy = "Y"; }
            }
        });
        consent_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_consent_4.getBackground() == check_o ) {
                    iv_consent_all.setBackground(check_x);
                    iv_consent_4.setBackground(check_x); isSMS = "N"; isEmail = "N";
                    iv_consent_5.setBackground(check_x);
                    iv_consent_6.setBackground(check_x);
                }
                else if (iv_consent_4.getBackground() == check_x){
                    iv_consent_4.setBackground(check_o); isSMS = "Y"; isEmail = "Y";
                    iv_consent_5.setBackground(check_o);
                    iv_consent_6.setBackground(check_o);
                }
            }
        });
        consent_5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_consent_5.getBackground() == check_o ) {
                    iv_consent_all.setBackground(check_x);
                    iv_consent_5.setBackground(check_x); isSMS = "N";
                    if (iv_consent_6.getBackground() == check_x)
                    {iv_consent_4.setBackground(check_x);}
                }
                else if (iv_consent_5.getBackground() == check_x) {
                    iv_consent_5.setBackground(check_o); isSMS = "Y";
                    if (iv_consent_6.getBackground() == check_o)
                    {iv_consent_4.setBackground(check_o);}
                }
            }
        });
        consent_6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_consent_6.getBackground() == check_o ) {
                    iv_consent_all.setBackground(check_x);
                    iv_consent_6.setBackground(check_x); isEmail = "N";
                }
                else if (iv_consent_6.getBackground() == check_x) {
                    iv_consent_6.setBackground(check_o); isEmail = "Y";
                } else if (iv_consent_6.getBackground() == check_o && iv_consent_5.getBackground() == check_x){
                    iv_consent_6.setBackground(check_x); isEmail = "N";
                    iv_consent_4.setBackground(check_x);
                } else if (iv_consent_6.getBackground() == check_x && iv_consent_5.getBackground() == check_o){
                    iv_consent_6.setBackground(check_o); isEmail = "Y";
                    iv_consent_4.setBackground(check_o);
                }
            }
        });
        consent_7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iv_consent_7.getBackground() == check_o ) {
                    iv_consent_all.setBackground(check_x);
                    iv_consent_7.setBackground(check_x); consent3 = false;
                }
                else { iv_consent_7.setBackground(check_o); consent3 = true; }
            }
        });

        //가입하기 버튼 눌렀을 때(멘트는 address부터 수정해야함)
        Button signUpBtn = (Button) findViewById(R.id.sign_up_btn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId == null) {onClickHandler("아이디를 입력해 주세요");}
                else if (idCheck == false) {onClickHandler("아이디 중복확인을 확인해 주세요");}
                else if (password == null) {onClickHandler("비밀번호를 입력해 주세요");}
                else if (pwCheck == false) {onClickHandler("비밀번호를 한번 더 입력해 주세요");}
                else if (name == null) {onClickHandler("이름을 입력해 주세요");}
                else  if (email == null) {onClickHandler("이메일 형식을 확인해 주세요");}
                else  if (phoneNumber == null) {onClickHandler("휴대폰 인증을 완료 해 주세요");}
                else  if (address == null) {onClickHandler("주소를 입력해 주세요");}
                else if (consent1 == false) {onClickHandler("이용약관에 동의해 주세요");}
                else if (consent2 == false) {onClickHandler("재인정보처리방침에 동의해 주세요");}
                else if (consent3 == false) {onClickHandler("만 14세 이상 확인을 완료 해 주세요");}
                else { //다 통과하고 회원가입 완료
                    tryPostSignUp();
                }
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

    public void onClickHandler(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

        builder.setTitle(message);


        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) { }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            { alertDialog.dismiss(); }
        });

    }

    public void onClickFinish(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

        builder.setTitle(message);


        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) { }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            { alertDialog.dismiss(); finish(); }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView daum_result = (TextView) findViewById(R.id.sign_up_address);
        final EditText address_detail = (EditText) findViewById(R.id.sign_up_address_detail);

        if (requestCode == 101) {
            if (resultCode == 102) {
                String address_num = data.getStringExtra("address_num");
                String address_main = data.getStringExtra("address_main");
                String address_sub = data.getStringExtra("address_sub");
                daum_result.setText(address_main);
                daum_result.setTextColor(Color.parseColor("#333333"));
                address_detail.setText(address_sub);
                address_detail.setTextColor(Color.parseColor("#333333"));
                address_detail.setVisibility(View.VISIBLE);

            }
        }
    }
    private void tryGetIdCheck() {
        //showProgressDialog();
        final SignUpService signupService = new SignUpService(this);
        signupService.getIdCheck(userId);
    }


    private void tryPostSignUp() {
        //showProgressDialog();

        final SignUpService signupService = new SignUpService(this);
        SignUpBody signUpBody = new SignUpBody(userId, password, name, email, phoneNumber, address, birthday, gender, recommenderId, event, acceptPrivacy, isSMS, isEmail);
        signupService.postSignUp(signUpBody);
        //signupService.postSignUp(userId, password, name, email, phoneNumber, address, birthday, gender, recommenderId, event, acceptPrivacy, isSMS, isEmail);
    }


    @Override
    public void signUpSuccess(SignUpResponse signUpResponse) {
        hideProgressDialog();
        mMessage = signUpResponse.getMessage();
        mIsSuccess = signUpResponse.getIsSuccess();
        if (signUpResponse.getIsSuccess()) {
            onClickFinish("회원가입을 축하드립니다!\n당신의 일상에 컬리를 더해 보세요.");
        } else {
            onClickHandler(mMessage);
        }
    }

    @Override
    public void signUpFailure(String message) {

    }

    @Override
    public void idCheckSuccess(IdCheckResponse idCheckResponse) {
        final TextView id_2_sym = (TextView) findViewById(R.id.sign_up_id_2_sym);
        final TextView id_2_tv = (TextView) findViewById(R.id.sign_up_id_2_tv);
        final EditText id = (EditText) findViewById(R.id.sign_up_id);

        mIdCheckCode = idCheckResponse.getCode();
        if (idCheckResponse.getIsSuccess()) {
            if(mIdCheckCode == 402){
                //중복되는 아이디가 있을 때
                onClickHandler("동일한 아이디가 이미 등록되어 있습니다");
                id_2_sym.setText("×");
                id_2_sym.setTextColor(Color.parseColor("#ae150d"));
                id_2_tv.setTextColor(Color.parseColor("#ae150d"));
            } else if (mIdCheckCode == 200){
                //아이디로 사용할 수 있을 때(id 중복확인!)
                onClickHandler("사용하실 수 있는 아이디입니다!");
                userId = id.getText().toString().trim();
                id_2_sym.setText("✓");
                id_2_sym.setTextColor(Color.parseColor("#10851a"));
                id_2_tv.setTextColor(Color.parseColor("#10851a"));
                idCheck = true;
            }
        } else {

        }
    }

    @Override
    public void idCheckFailure(String message) {

    }
}