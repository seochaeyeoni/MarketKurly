package com.example.marketkurly.src.main.home.recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketkurly.R;
import com.example.marketkurly.src.main.home.recommend.Adapter.AdAdapter;
import com.example.marketkurly.src.main.home.recommend.Adapter.ProductAdapter;
import com.example.marketkurly.src.main.home.recommend.Adapter.SaleProductAdapter;
import com.example.marketkurly.src.main.home.recommend.Data.AdData;
import com.example.marketkurly.src.main.home.recommend.Data.ProductData;
import com.example.marketkurly.src.main.home.recommend.interfaces.HomeRecommendActivityView;
import com.example.marketkurly.src.main.home.recommend.models.HomeRecommendResponse;

import java.util.ArrayList;

public class HomeRecommendFrag extends Fragment implements HomeRecommendActivityView {
    private ArrayList<AdData> adArrayList;
    private ArrayList<ProductData> productArrayList;
    private ArrayList<ProductData> saleProductArrayList;
    private AdAdapter adAdapter;
    private ProductAdapter productAdapter;
    private SaleProductAdapter saleProductAdapter;
    private RecyclerView recyclerView1, recyclerView2, recyclerView3;
    private LinearLayoutManager linearLayoutManager;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home_recommend, container, false);
        tryPostHomeRecommend();

        recyclerView1 = (RecyclerView) view.findViewById(R.id.home_recommend_rv1);
        recyclerView1.setHasFixedSize(true); //일정한 크기 사용
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false); //LinearLayoutManager 는 리스트뷰(가로/세로)
        recyclerView1.setLayoutManager(linearLayoutManager);

        recyclerView2 = (RecyclerView) view.findViewById(R.id.home_recommend_rv2);
        recyclerView2.setHasFixedSize(true); //일정한 크기 사용
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false); //LinearLayoutManager 는 리스트뷰(가로/세로)
        recyclerView2.setLayoutManager(linearLayoutManager);

        recyclerView3 = (RecyclerView) view.findViewById(R.id.home_recommend_rv3);
        recyclerView3.setHasFixedSize(true); //일정한 크기 사용
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false); //LinearLayoutManager 는 리스트뷰(가로/세로)
        recyclerView3.setLayoutManager(linearLayoutManager);

        //PagerSnapHelper 추가
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView1);
        //Indicator 추가하기
        //boxoffice_recycler.addItemDecoration(new LinePagerIndicatorDecoration());

        SnapPagerScrollListener listener = new SnapPagerScrollListener(
                snapHelper,
                SnapPagerScrollListener.ON_SCROLL,
                true,
                new SnapPagerScrollListener.OnChangeListener() {
                    @Override
                    public void onSnapped(int position) {
                        //position 받아서 이벤트 처리
                    }
                }

        );

        //rv1
        adArrayList = new ArrayList<>();

        adAdapter = new AdAdapter(adArrayList);
        recyclerView1.setAdapter(adAdapter);

        for (int i=0;i<2;i++)
        {
            adArrayList.add(new AdData(R.drawable.home_recommend_ad1));
            adArrayList.add(new AdData(R.drawable.home_recommend_ad2));
            adArrayList.add(new AdData(R.drawable.home_recommend_ad3));
            adArrayList.add(new AdData(R.drawable.home_recommend_ad4));
        }


        return view;
    }

    private void tryPostHomeRecommend() {
        //showProgressDialog();

        final HomeRecommendService homeRecommendService = new HomeRecommendService(this);
        homeRecommendService.postHomeRecommend();
    }

    @Override
    public void homeRecommendSuccess(HomeRecommendResponse homeRecommendResponse) {
        //rv2
        productArrayList = new ArrayList<>();
        if (homeRecommendResponse.getIsSuccess()) {
            for (int i = 0; i < homeRecommendResponse.getResult().getRecommend().size(); i++) {
                productArrayList.add(new ProductData(homeRecommendResponse.getResult().getRecommend().get(i).getProductIdx(),
                        homeRecommendResponse.getResult().getRecommend().get(i).getPictureUrl(),
                        homeRecommendResponse.getResult().getRecommend().get(i).getProductName(),
                        homeRecommendResponse.getResult().getRecommend().get(i).getClientPrice(),
                        homeRecommendResponse.getResult().getRecommend().get(i).getOriginalPrice(),
                        String.valueOf(homeRecommendResponse.getResult().getRecommend().get(i).getSalePercent())));
            }
            productAdapter = new ProductAdapter(productArrayList);
            recyclerView2.setAdapter(productAdapter);
        }

        //rv3
        saleProductArrayList = new ArrayList<>();
        if (homeRecommendResponse.getIsSuccess()) {
            for (int i = 0; i < homeRecommendResponse.getResult().getSale().size(); i++) {
                saleProductArrayList.add(new ProductData(homeRecommendResponse.getResult().getSale().get(i).getProductIdx(),
                        homeRecommendResponse.getResult().getSale().get(i).getPictureUrl(),
                        homeRecommendResponse.getResult().getSale().get(i).getProductName(),
                        homeRecommendResponse.getResult().getSale().get(i).getClientPrice(),
                        homeRecommendResponse.getResult().getSale().get(i).getOriginalPrice(),
                        String.valueOf(homeRecommendResponse.getResult().getSale().get(i).getSalePercent())));
            }
            saleProductAdapter = new SaleProductAdapter(saleProductArrayList);
            recyclerView3.setAdapter(saleProductAdapter);
        }

    }

    @Override
    public void homeRecommendFailure(String message) {

    }
}