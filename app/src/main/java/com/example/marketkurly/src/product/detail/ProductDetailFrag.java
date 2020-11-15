package com.example.marketkurly.src.product.detail;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.marketkurly.R;
import com.example.marketkurly.src.product.ProductActivity;
import com.example.marketkurly.src.product.ProductService;
import com.example.marketkurly.src.product.detail.interfaces.ProductDetailActivityView;
import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.models.ProductResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.example.marketkurly.src.ApplicationClass.sSharedPreferences;

public class ProductDetailFrag extends Fragment implements ProductDetailActivityView {
    private View view;
    int productIdx;

    FirebaseStorage storage = FirebaseStorage.getInstance("gs://marketcurly-e978c.appspot.com");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_product_detail, container, false);

        productIdx = sSharedPreferences.getInt("productIdx", 0);
        tryGetProduct();

        return view;
    }
    private void tryGetProduct() {
        //showProgressDialog();

        final ProductDetailService productDetailService = new ProductDetailService(this);
        productDetailService.getProduct(productIdx);
    }

    @Override
    public void productDetailSuccess(ProductResponse productResponse) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.product_detail_iv);
        StorageReference gsReference = storage.getReferenceFromUrl(productResponse.getResult().getProductDetail());
        gsReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시
                Glide.with(view.getContext()).load(uri).into(imageView);
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