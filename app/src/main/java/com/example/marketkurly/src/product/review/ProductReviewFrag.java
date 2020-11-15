package com.example.marketkurly.src.product.review;

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
import com.example.marketkurly.src.product.interfaces.ProductActivityView;
import com.example.marketkurly.src.product.models.ProductResponse;
import com.example.marketkurly.src.product.review.interfaces.ProductReviewActivityView;

import java.util.ArrayList;

import static com.example.marketkurly.src.ApplicationClass.sSharedPreferences;


public class ProductReviewFrag extends Fragment implements ProductReviewActivityView {

    private ArrayList<ReviewData> reviewArrayList;
    private ReviewAdapter reviewAdapter;
    private RecyclerView rvReview;
    private LinearLayoutManager linearLayoutManager;
    private View view;
    int productIdx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_product_review, container, false);

        productIdx = sSharedPreferences.getInt("productIdx", 0);
        tryGetProduct();

        rvReview = (RecyclerView) view.findViewById(R.id.product_review_rv);
        rvReview.setHasFixedSize(true); //일정한 크기 사용
        linearLayoutManager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() { //스크롤 안되게!
                return false;
            }
        };
        rvReview.setLayoutManager(linearLayoutManager);

        return view;
    }

    private void tryGetProduct() {
        //showProgressDialog();
        final ProductReviewService productReviewService = new ProductReviewService(this);
        productReviewService.getProduct(productIdx);
    }

    @Override
    public void productReviewSuccess(ProductResponse productResponse) {
        //rvReview
        reviewArrayList = new ArrayList<>();
        if (productResponse.getIsSuccess()) {
            for (int i = 0; i < productResponse.getResult().getReview().size(); i++) {
                reviewArrayList.add(new ReviewData(productResponse.getResult().getReview().get(i).getTitle(),
                        productResponse.getResult().getReview().get(i).getIsBest(),
                        productResponse.getResult().getReview().get(i).getLevel(),
                        productResponse.getResult().getReview().get(i).getUserName(),
                        productResponse.getResult().getReview().get(i).getIsPic(),
                        String.valueOf(productResponse.getResult().getReview().get(i).getCreatedAt())));
            }
            reviewAdapter = new ReviewAdapter(reviewArrayList);
            rvReview.setAdapter(reviewAdapter);
        }

    }


    @Override
    public void productFailure(String message) {

    }

}