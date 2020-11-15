package com.example.marketkurly.src.product.info;

import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.marketkurly.R;
import com.example.marketkurly.src.product.ProductService;
import com.example.marketkurly.src.product.info.interfaces.ProductInfoActivityView;
import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.models.ProductResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.example.marketkurly.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.marketkurly.src.ApplicationClass.sSharedPreferences;

public class ProductInfoFrag extends Fragment implements ProductInfoActivityView {
    private View view;
    int productIdx;

    FirebaseStorage storage = FirebaseStorage.getInstance("gs://marketcurly-e978c.appspot.com");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_product_info, container, false);

        productIdx = sSharedPreferences.getInt("productIdx", 0);
        tryGetProduct();

        return view;
    }

    private void tryGetProduct() {
        //showProgressDialog();
        final ProductInfoService productInfoService = new ProductInfoService(this);
        productInfoService.getProduct(productIdx);
    }

    @Override
    public void productInfoSuccess(ProductResponse productResponse) {
        LinearLayout linearLayout1 = (LinearLayout) view.findViewById(R.id.product_info_1);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.product_info_2);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.product_info_3);
        LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.product_info_4);
        LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.product_info_5);
        LinearLayout linearLayout6 = (LinearLayout) view.findViewById(R.id.product_info_6);
        LinearLayout linearLayout7 = (LinearLayout) view.findViewById(R.id.product_info_7);
        LinearLayout linearLayout8 = (LinearLayout) view.findViewById(R.id.product_info_8);
        LinearLayout linearLayout9 = (LinearLayout) view.findViewById(R.id.product_info_9);
        LinearLayout linearLayout10 = (LinearLayout) view.findViewById(R.id.product_info_10);

        //없음으로 되어 있는 컬럼 일일이 다 GONE 으로
        if(productResponse.getResult().getProductInfo().getAllergy().equals("없음")) {
            linearLayout6.setVisibility(View.GONE);
        }
        if(productResponse.getResult().getProductInfo().getWeight().equals("없음")) {
            linearLayout2.setVisibility(View.GONE);
        }
        if(productResponse.getResult().getProductInfo().getOrigin().equals("없음")) {
            linearLayout4.setVisibility(View.GONE);
        }
        if(productResponse.getResult().getProductInfo().getExpiration().equals("없음")) {
            linearLayout7.setVisibility(View.GONE);
        }
        if(productResponse.getResult().getProductInfo().getRecordInfo().equals("없음")) {
            linearLayout8.setVisibility(View.GONE);
        }
        if(productResponse.getResult().getProductInfo().getGuidance().equals("없음")) {
            linearLayout9.setVisibility(View.GONE);
        }
        if(productResponse.getResult().getProductInfo().getCalories().equals("없음")) {
            linearLayout10.setVisibility(View.GONE);
        }

        final ImageView image = (ImageView) view.findViewById(R.id.product_info_image);
        final ImageView exp_image = (ImageView) view.findViewById(R.id.product_info_exp_image);
        TextView name = (TextView) view.findViewById(R.id.product_info_name);
        TextView comment = (TextView) view.findViewById(R.id.product_info_comment);
        TextView price = (TextView) view.findViewById(R.id.product_info_price);
        TextView sale_percent = (TextView) view.findViewById(R.id.product_info_sale_percent);
        TextView price_before = (TextView) view.findViewById(R.id.product_info_price_before);
        TextView level = (TextView) view.findViewById(R.id.product_info_level);
        TextView profit = (TextView) view.findViewById(R.id.product_info_profit);
        TextView profit_price = (TextView) view.findViewById(R.id.product_info_profit_price);
        TextView sale_unit = (TextView) view.findViewById(R.id.product_info_sale_unit);
        TextView weight = (TextView) view.findViewById(R.id.product_info_weight);
        TextView shipping = (TextView) view.findViewById(R.id.product_info_shipping);
        TextView origin = (TextView) view.findViewById(R.id.product_info_origin);
        TextView packing_type = (TextView) view.findViewById(R.id.product_info_packing_type);
        TextView allergy = (TextView) view.findViewById(R.id.product_info_allergy);
        TextView expiration = (TextView) view.findViewById(R.id.product_info_expiration);
        TextView record = (TextView) view.findViewById(R.id.product_info_record);
        TextView guidance = (TextView) view.findViewById(R.id.product_info_guidance);
        TextView calories = (TextView) view.findViewById(R.id.product_info_calories);

        LinearLayout user_comment = (LinearLayout) view.findViewById(R.id.product_info_user_comment);
        TextView nonuser_comment = (TextView) view.findViewById(R.id.product_info_nonuser_comment);
        TextView user_price = (TextView) view.findViewById(R.id.product_info_is_user);
        TextView sale_percent_icon = (TextView) view.findViewById(R.id.product_info_sale_percent_icon);
        ImageButton price_before_icon = (ImageButton) view.findViewById(R.id.product_info_price_icon);

        //다 값 넣어준다
        //토큰 값이 있을 때만 getUserInfo 가 넘어온다.
        String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
        if (jwtToken != null) { //토큰 값이 있을 때
            level.setText(productResponse.getResult().getUserInfo().getLevel());
            profit.setText(String.valueOf(productResponse.getResult().getUserInfo().getProfit()));
            profit_price.setText(productResponse.getResult().getUserInfo().getProfitPrice());
            //회원정보 띄워주고, 비회원 화면 가리기
            user_comment.setVisibility(View.VISIBLE);
            nonuser_comment.setVisibility(View.GONE);
        } else {
            user_comment.setVisibility(View.GONE);
            nonuser_comment.setVisibility(View.VISIBLE);
        }
        //세일을 할 때는 '회원할인가', 할인율, 원래가격 띄워주기
        if (productResponse.getResult().getProductInfo().getSalePercent()!=0) {
            user_price.setVisibility(View.VISIBLE);
            sale_percent.setVisibility(View.VISIBLE);
            sale_percent_icon.setVisibility(View.VISIBLE);
            price_before.setVisibility(View.VISIBLE);
            price_before_icon.setVisibility(View.VISIBLE);
        } else {
            user_price.setVisibility(View.GONE);
            sale_percent.setVisibility(View.GONE);
            sale_percent_icon.setVisibility(View.GONE);
            price_before.setVisibility(View.GONE);
            price_before_icon.setVisibility(View.GONE);
        }
        name.setText(productResponse.getResult().getProductInfo().getProductName());
        comment.setText(productResponse.getResult().getProductInfo().getProductComment());
        price.setText(productResponse.getResult().getProductInfo().getClientPrice());
        sale_percent.setText(String.valueOf(productResponse.getResult().getProductInfo().getSalePercent()));
        price_before.setText(productResponse.getResult().getProductInfo().getOriginalPrice());

        sale_unit.setText(productResponse.getResult().getProductInfo().getSalesUnit());
        weight.setText(productResponse.getResult().getProductInfo().getWeight());
        shipping.setText(productResponse.getResult().getProductInfo().getShipping());
        origin.setText(productResponse.getResult().getProductInfo().getOrigin());
        packing_type.setText(productResponse.getResult().getProductInfo().getPackingType());
        allergy.setText(productResponse.getResult().getProductInfo().getAllergy().replace("\\n","\n"));
        expiration.setText(productResponse.getResult().getProductInfo().getExpiration());
        record.setText(productResponse.getResult().getProductInfo().getRecordInfo());
        guidance.setText(productResponse.getResult().getProductInfo().getGuidance().replace("\\n","\n"));
        calories.setText(productResponse.getResult().getProductInfo().getCalories());

        //취소선 긋기
        price_before.setPaintFlags(price_before.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

        StorageReference gsReference1 = storage.getReferenceFromUrl(productResponse.getResult().getProductInfo().getMainPic());
        gsReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시
                Glide.with(view.getContext()).load(uri).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
            }
        });
        StorageReference gsReference2 = storage.getReferenceFromUrl(productResponse.getResult().getProductInfo().getExplanationPic());
        gsReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시
                Glide.with(view.getContext()).load(uri).into(exp_image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
            }
        });



    }

    @Override
    public void productFailure(String message) {

    }
}