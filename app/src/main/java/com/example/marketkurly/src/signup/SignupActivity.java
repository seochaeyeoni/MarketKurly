package com.example.marketkurly.src.signup;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.marketkurly.R;

public class SignupActivity extends AppCompatActivity {

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
        EditText name = (EditText) findViewById(R.id.sign_up_name);
        EditText email = (EditText) findViewById(R.id.sign_up_email);
        EditText mobile = (EditText) findViewById(R.id.sign_up_mobile);
        //주소...해야함...
        EditText yyyy = (EditText) findViewById(R.id.sign_up_birth_yyyy);
        EditText mm = (EditText) findViewById(R.id.sign_up_birth_mm);
        EditText dd = (EditText) findViewById(R.id.sign_up_birth_dd);
        final EditText option_id = (EditText) findViewById(R.id.sign_up_option_id_edit);
        final EditText option_event = (EditText) findViewById(R.id.sign_up_option_event_edit);

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
        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String input_id = id.getText().toString().trim();
                TextView id_1_sym = (TextView) findViewById(R.id.sign_up_id_1_sym);
                TextView id_1_tv = (TextView) findViewById(R.id.sign_up_id_1_tv);
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
                    Log.d("pw",(String.valueOf(input_pw.length())));
                    pw_1_sym.setText("✓");
                    pw_1_sym.setTextColor(Color.parseColor("#10851a"));
                    pw_1_tv.setTextColor(Color.parseColor("#10851a"));
                } else {
                    pw_1_sym.setText("×");
                    pw_1_sym.setTextColor(Color.parseColor("#ae150d"));
                    pw_1_tv.setTextColor(Color.parseColor("#ae150d"));
                }
                if (input_pw.matches(pwValidation_1_1) | input_pw.matches(pwValidation_1_2) | input_pw.matches(pwValidation_1_3) && s.length()>0 ) {
                    pw_2_sym.setText("✓");
                    pw_2_sym.setTextColor(Color.parseColor("#10851a"));
                    pw_2_tv.setTextColor(Color.parseColor("#10851a"));
                } else {
                    pw_2_sym.setText("×");
                    pw_2_sym.setTextColor(Color.parseColor("#ae150d"));
                    pw_2_tv.setTextColor(Color.parseColor("#ae150d"));
                }
                if (input_pw.contains("000")|input_pw.contains("111")|input_pw.contains("222")|input_pw.contains("333")|input_pw.contains("444")|input_pw.contains("555")|input_pw.contains("666")|input_pw.contains("777")|input_pw.contains("888")|input_pw.contains("999")) {
                    pw_3_sym.setText("×");
                    pw_3_sym.setTextColor(Color.parseColor("#ae150d"));
                    pw_3_tv.setTextColor(Color.parseColor("#ae150d"));

                } else {
                    pw_3_sym.setText("✓");
                    pw_3_sym.setTextColor(Color.parseColor("#10851a"));
                    pw_3_tv.setTextColor(Color.parseColor("#10851a"));
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
        final String emailValidation = "^[a-z0-9_+.-]+@([a-z0-9-]+\\.)+[a-z0-9]{2,4}$"; //

        //성별 클릭 이벤트(해당 컬럼을 누르면 눌리게)
        LinearLayout gender_one = (LinearLayout) findViewById(R.id.sign_up_man);
        LinearLayout gender_two = (LinearLayout) findViewById(R.id.sign_up_woman);
        LinearLayout gender_three = (LinearLayout) findViewById(R.id.sign_up_no_gender);
        final RadioButton rb_one = (RadioButton) findViewById(R.id.sign_up_rb_1);
        final RadioButton rb_two = (RadioButton) findViewById(R.id.sign_up_rb_2);
        final RadioButton rb_three = (RadioButton) findViewById(R.id.sign_up_rb_3);

        gender_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_one.setChecked(true);
                rb_two.setChecked(false);
                rb_three.setChecked(false);
            }
        });
        gender_two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_one.setChecked(false);
                rb_two.setChecked(true);
                rb_three.setChecked(false);
            }
        });
        gender_three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_one.setChecked(false);
                rb_two.setChecked(false);
                rb_three.setChecked(true);
            }
        });

        //추가입력 사항 클릭 이벤트(해당 컬럼을 누르면 눌리게/에디트텍스트 띄우기)
        LinearLayout option_one = (LinearLayout) findViewById(R.id.sign_up_option_id);
        LinearLayout option_two = (LinearLayout) findViewById(R.id.sign_up_option_event);
        final RadioButton rb_four = (RadioButton) findViewById(R.id.sign_up_rb_4);
        final RadioButton rb_five = (RadioButton) findViewById(R.id.sign_up_rb_5);

        option_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                option_id.setVisibility(View.VISIBLE);
                option_event.setVisibility(View.GONE);
                rb_four.setChecked(true);
                rb_five.setChecked(false);
            }
        });
        option_two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                option_id.setVisibility(View.GONE);
                option_event.setVisibility(View.VISIBLE);
                rb_five.setChecked(false);
                rb_four.setChecked(true);
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
        final ToggleButton tg_all = (ToggleButton) findViewById(R.id.sign_up_tg_all);
        final ToggleButton tg_1 = (ToggleButton) findViewById(R.id.sign_up_tg_1);
        final ToggleButton tg_2 = (ToggleButton) findViewById(R.id.sign_up_tg_2);
        final ToggleButton tg_3 = (ToggleButton) findViewById(R.id.sign_up_tg_3);
        final ToggleButton tg_4 = (ToggleButton) findViewById(R.id.sign_up_tg_4);
        final ToggleButton tg_5 = (ToggleButton) findViewById(R.id.sign_up_tg_5);
        final ToggleButton tg_6 = (ToggleButton) findViewById(R.id.sign_up_tg_6);
        final ToggleButton tg_7 = (ToggleButton) findViewById(R.id.sign_up_tg_7);

        consent_all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tg_all.setChecked(true); tg_1.setChecked(true);
                tg_2.setChecked(true); tg_3.setChecked(true);
                tg_4.setChecked(true); tg_5.setChecked(true);
                tg_6.setChecked(true); tg_7.setChecked(true);
            }
        });
        consent_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tg_1.setChecked(true);
            }
        });
        consent_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tg_2.setChecked(true);
            }
        });
        consent_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tg_3.setChecked(true);
            }
        });
        consent_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tg_4.setChecked(true); tg_5.setChecked(true); tg_6.setChecked(true);
            }
        });
        consent_5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tg_5.setChecked(true);
            }
        });
        consent_6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tg_6.setChecked(true);
            }
        });
        consent_7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tg_7.setChecked(true);
            }
        });
        tg_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tg_all.isChecked()){
                    tg_all.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                    tg_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                    tg_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                    tg_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                    tg_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                    tg_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                    tg_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                    tg_7.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                } else {
                    tg_all.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                    tg_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                    tg_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                    tg_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                    tg_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                    tg_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                    tg_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                    tg_7.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                }
            }
        });


        tg_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tg_1.isChecked()){
                    tg_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                } else {
                    tg_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                }
            }
        });
        tg_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tg_2.isChecked()){
                    tg_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                } else {
                    tg_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                }
            }
        });
        tg_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tg_3.isChecked()){
                    tg_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                } else {
                    tg_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                }
            }
        });
        tg_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tg_4.isChecked()){
                    tg_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                } else {
                    tg_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                }
            }
        });
        tg_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tg_5.isChecked()){
                    tg_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                } else {
                    tg_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                }
            }
        });
        tg_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tg_6.isChecked()){
                    tg_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                } else {
                    tg_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
                }
            }
        });
        tg_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tg_7.isChecked()){
                    tg_7.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_checked));
                } else {
                    tg_7.setBackgroundDrawable(getResources().getDrawable(R.drawable.check_unchecked));
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


}