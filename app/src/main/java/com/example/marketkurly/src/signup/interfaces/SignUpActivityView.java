package com.example.marketkurly.src.signup.interfaces;

import com.example.marketkurly.src.signup.models.IdCheckResponse;
import com.example.marketkurly.src.signup.models.SignUpBody;
import com.example.marketkurly.src.signup.models.SignUpResponse;

public interface SignUpActivityView {

    void signUpSuccess(SignUpResponse signUpResponse);

    void signUpFailure(String message);

    void idCheckSuccess(IdCheckResponse idCheckResponse);

    void idCheckFailure(String message);
}
