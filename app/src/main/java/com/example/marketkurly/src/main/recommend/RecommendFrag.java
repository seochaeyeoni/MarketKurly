package com.example.marketkurly.src.main.recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketkurly.R;
import com.example.marketkurly.src.main.home.recommend.Data.ProductData;
import com.example.marketkurly.src.main.home.recommend.SnapPagerScrollListener;
import com.example.marketkurly.src.main.recommend.Adapter.GoodCommentAdapter;
import com.example.marketkurly.src.main.recommend.Adapter.LoginBeforeAdapter;
import com.example.marketkurly.src.main.recommend.Adapter.RelatedAdapter;
import com.example.marketkurly.src.main.recommend.Data.DummyData;
import com.example.marketkurly.src.main.recommend.Data.GoodCommentData;
import com.example.marketkurly.src.main.recommend.interfaces.RecommendActivityView;
import com.example.marketkurly.src.main.recommend.models.RecommendResponse;

import java.util.ArrayList;

public class RecommendFrag extends Fragment implements RecommendActivityView {
    private ArrayList<DummyData> loginBeforeArrayList;
    private ArrayList<ProductData> relatedArrayList;
    private ArrayList<GoodCommentData> goodCommentArrayList;
    private LoginBeforeAdapter loginBeforeAdapter;
    private RelatedAdapter relatedAdapter;
    private GoodCommentAdapter goodCommentAdapter;
    private RecyclerView rvLoginBefore, rvRelated, rvGoodComment;
    private LinearLayoutManager linearLayoutManager;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_recommend, container, false);
        tryGetRecommend();

        rvLoginBefore = (RecyclerView) view.findViewById(R.id.recommend_rv1_dummy);
        rvLoginBefore.setHasFixedSize(true); //일정한 크기 사용
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false); //LinearLayoutManager 는 리스트뷰(가로/세로)
        rvLoginBefore.setLayoutManager(linearLayoutManager);

        rvRelated = (RecyclerView) view.findViewById(R.id.recommend_rv1);
        rvRelated.setHasFixedSize(true); //일정한 크기 사용
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false); //LinearLayoutManager 는 리스트뷰(가로/세로)
        rvRelated.setLayoutManager(linearLayoutManager);

        rvGoodComment = (RecyclerView) view.findViewById(R.id.recommend_rv2);
        rvGoodComment.setHasFixedSize(true); //일정한 크기 사용
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false); //LinearLayoutManager 는 리스트뷰(가로/세로)
        rvGoodComment.setLayoutManager(linearLayoutManager);

        //PagerSnapHelper 추가
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvLoginBefore);
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



        return view;
    }

    private void tryGetRecommend() {
        //showProgressDialog();

        final RecommendService recommendService = new RecommendService(this);
        recommendService.getRecommend();
    }

    @Override
    public void recommendSuccess(RecommendResponse recommendResponse) {
        LinearLayout login_before = (LinearLayout) view.findViewById(R.id.recommend_login_before);
        LinearLayout user_related = (LinearLayout) view.findViewById(R.id.recommend_user_related);

        if (recommendResponse.getIsSuccess()) {
            if (recommendResponse.getResult().getUserName().equals("고객")) {
                //로그인이 안 되어 있는 경우
                login_before.setVisibility(View.VISIBLE);
                user_related.setVisibility(View.GONE);

                //rvLoginBefore(더미데이터)
                loginBeforeArrayList = new ArrayList<>();
                loginBeforeAdapter = new LoginBeforeAdapter(loginBeforeArrayList);
                rvLoginBefore.setAdapter(loginBeforeAdapter);
                for (int i = 0; i < 2; i++) {
                    loginBeforeArrayList.add(new DummyData(R.drawable.login_before_1, "[간식엔] 크림가득 부드러운 만쥬", "4500원", "4500원", "0"));
                    loginBeforeArrayList.add(new DummyData(R.drawable.login_before_2, "[맘스케익] 프리미엄 오믈렛 4종", "10,800원", "12,000원", "10"));
                    loginBeforeArrayList.add(new DummyData(R.drawable.login_before_3, "[VEZZLY] 부드럽고 진한 수플레 치즈 케익", "15,000원", "15,000원", "0"));
                    loginBeforeArrayList.add(new DummyData(R.drawable.login_before_4, "[메종프랭크] 큐브 쿠키&크림치즈케익", "5500원", "5500원", "0"));
                    loginBeforeArrayList.add(new DummyData(R.drawable.login_before_5, "[나폴레옹] 고구마 무스", "24,000원", "24,000원", "0"));
                    loginBeforeArrayList.add(new DummyData(R.drawable.login_before_6, "[선물세트] 맘스케익 프리미엄 오믈렛", "10,200원", "12,000원", "15"));
                }

            } else {
                //로그인이 되어 있는 경우(유저 정보O)
                login_before.setVisibility(View.GONE);
                user_related.setVisibility(View.VISIBLE);

                //유저 이름이 뜨게끔
                TextView user_name = (TextView) view.findViewById(R.id.recommend_user_name);
                user_name.setText(recommendResponse.getResult().getUserName());

                //rvRelated
                relatedArrayList = new ArrayList<>();
                for (int i = 0; i < recommendResponse.getResult().getRelated().size(); i++) {
                    relatedArrayList.add(new ProductData(recommendResponse.getResult().getRelated().get(i).getProductIdx(),
                            recommendResponse.getResult().getRelated().get(i).getPictureUrl(),
                            recommendResponse.getResult().getRelated().get(i).getProductName(),
                            recommendResponse.getResult().getRelated().get(i).getClientPrice(),
                            recommendResponse.getResult().getRelated().get(i).getOriginalPrice(),
                            String.valueOf(recommendResponse.getResult().getRelated().get(i).getSalePercent())));
                }
                relatedAdapter = new RelatedAdapter(relatedArrayList);
                rvRelated.setAdapter(relatedAdapter);

            }

            //rvGoodComment
            //BestComment review, name 각각 어레이리스트 만들어서 넘기기
            goodCommentArrayList = new ArrayList<>();
            ArrayList reviewArrayList = new ArrayList();
            ArrayList nameArrayList = new ArrayList();
            for (int i = 0; i < recommendResponse.getResult().getGoodComment().size(); i++) {
                for (int j=0; j < recommendResponse.getResult().getGoodComment().get(i).getBestComment().size(); j++) {
                    reviewArrayList.add(recommendResponse.getResult().getGoodComment().get(i).getBestComment().get(j).getReview());
                    nameArrayList.add(recommendResponse.getResult().getGoodComment().get(i).getBestComment().get(j).getName());
                }
                goodCommentArrayList.add(new GoodCommentData(recommendResponse.getResult().getGoodComment().get(i).getProductIdx(),
                        recommendResponse.getResult().getGoodComment().get(i).getPictureUrl(),
                        recommendResponse.getResult().getGoodComment().get(i).getProductName(),
                        recommendResponse.getResult().getGoodComment().get(i).getClientPrice(),
                        recommendResponse.getResult().getGoodComment().get(i).getOriginalPrice(),
                        String.valueOf(recommendResponse.getResult().getGoodComment().get(i).getSalePercent()),
                        reviewArrayList, nameArrayList));
            }
            goodCommentAdapter = new GoodCommentAdapter(goodCommentArrayList);
            rvGoodComment.setAdapter(goodCommentAdapter);


        }


    }

    @Override
    public void recommendFailure(String message) {

    }
}