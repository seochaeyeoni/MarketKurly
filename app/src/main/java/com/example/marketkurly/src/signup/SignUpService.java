package com.example.marketkurly.src.signup;

import android.util.Log;

import com.example.marketkurly.src.signup.interfaces.SignUpActivityView;
import com.example.marketkurly.src.signup.interfaces.SignUpRetrofitInterface;
import com.example.marketkurly.src.signup.models.IdCheckResponse;
import com.example.marketkurly.src.signup.models.SignUpResponse;
import com.example.marketkurly.src.signup.models.SignUpBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marketkurly.src.ApplicationClass.getRetrofit;

class SignUpService {
    private final SignUpActivityView mSignUpActivityView;

    SignUpService(final SignUpActivityView signupActivityView) {
        this.mSignUpActivityView = signupActivityView;
    }

    void getIdCheck(String userId) {
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.getIdCheck(userId).enqueue(new Callback<IdCheckResponse>() {
            @Override
            public void onResponse(Call<IdCheckResponse> call, Response<IdCheckResponse> response) {
                Log.d("response","1");
                final IdCheckResponse idCheckResponse = response.body();
                if (idCheckResponse == null) {
                    mSignUpActivityView.idCheckFailure(null);
                    Log.d("response","2");
                    return;

                }

                mSignUpActivityView.idCheckSuccess(idCheckResponse);
                Log.d("response","3");
            }

            @Override
            public void onFailure(Call<IdCheckResponse> call, Throwable t) {
                mSignUpActivityView.idCheckFailure(null);
                Log.d("response", String.valueOf(t));
            }
        });
    }

    void postSignUp(SignUpBody signUpBody) {

//        String userId, String password, String name, String email, String phoneNumber,
//                String address, String birthday, String gender, String recommenderId,
//                String event, String acceptPrivacy, String isSMS, String isEmail

//        JSONObject object = new JSONObject();
//        try {
//            object.put("userId", userId);
//            object.put("password", password);
//            object.put("name", name);
//            object.put("email", email);
//            object.put("phoneNumber", phoneNumber);
//            object.put("address", address);
//            object.put("birthday", birthday);
//            object.put("gender", gender);
//            object.put("recommenderId", recommenderId);
//            object.put("event", event);
//            object.put("acceptPrivacy", acceptPrivacy);
//            object.put("isSMS", isSMS);
//            object.put("isEmail", isEmail);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.postSignUp(signUpBody).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    mSignUpActivityView.signUpFailure(null);
                    return;
                }

                mSignUpActivityView.signUpSuccess(signUpResponse);
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.signUpFailure(null);
            }
        });
    }
}