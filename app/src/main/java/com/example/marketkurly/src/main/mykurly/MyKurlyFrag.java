package com.example.marketkurly.src.main.mykurly;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.marketkurly.R;
import com.example.marketkurly.src.login.LoginActivity;
import com.example.marketkurly.src.main.MainActivity;

public class MyKurlyFrag extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_my_kurly, container, false);

        //로그인/회원가입 버튼 클릭시 로그인 액티비티로 이동
        Button button = (Button) view.findViewById(R.id.mykurly_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return view;
    }
}