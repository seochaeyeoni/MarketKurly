package com.example.marketkurly.src.product.inquiry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketkurly.R;
import com.example.marketkurly.src.product.ProductService;
import com.example.marketkurly.src.product.inquiry.interfaces.ProductInquiryActivityView;
import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.models.ProductResponse;


import java.util.ArrayList;

import static com.example.marketkurly.src.ApplicationClass.sSharedPreferences;

public class ProductInquiryFrag extends Fragment implements ProductInquiryActivityView {
    private ArrayList<InquiryData> inquiryArrayList;
    private InquiryAdapter inquiryAdapter;
    private RecyclerView rvInquiry;
    private LinearLayoutManager linearLayoutManager;
    private View view;
    int productIdx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_product_inquiry, container, false);

        productIdx = sSharedPreferences.getInt("productIdx", 0);
        tryGetProduct();

        rvInquiry = (RecyclerView) view.findViewById(R.id.product_inquiry_rv);
        rvInquiry.setHasFixedSize(true); //일정한 크기 사용
        linearLayoutManager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() { //스크롤 안되게!
                return false;
            }
        };
        rvInquiry.setLayoutManager(linearLayoutManager);

        return view;
    }

    private void tryGetProduct() {
        //showProgressDialog();
        final ProductInquiryService productInquiryService = new ProductInquiryService(this);
        productInquiryService.getProduct(productIdx);
    }

    @Override
    public void productInquirySuccess(ProductResponse productResponse) {
        //rvInquiry
        inquiryArrayList = new ArrayList<>();
        if (productResponse.getIsSuccess()) {
            for (int i = 0; i < productResponse.getResult().getInquiry().size(); i++) {
                inquiryArrayList.add(new InquiryData(productResponse.getResult().getInquiry().get(i).getInquiryTitle(),
                        productResponse.getResult().getInquiry().get(i).getUserName(),
                        productResponse.getResult().getInquiry().get(i).getIsLocked(),
                        productResponse.getResult().getInquiry().get(i).getCreatedAt(),
                        productResponse.getResult().getInquiry().get(i).getIsAnswered()));
            }
            inquiryAdapter = new InquiryAdapter(inquiryArrayList);
            rvInquiry.setAdapter(inquiryAdapter);
        }

    }


    @Override
    public void productFailure(String message) {

    }

}