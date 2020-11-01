package com.example.marketkurly.src.main.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.marketkurly.src.main.home.best.HomeBestFrag;
import com.example.marketkurly.src.main.home.event.HomeEventFrag;
import com.example.marketkurly.src.main.home.frugalproduct.HomeFrugalProductFrag;
import com.example.marketkurly.src.main.home.newproduct.HomeNewProductFrag;
import com.example.marketkurly.src.main.home.recommend.HomeRecommendFrag;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public int mCount;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int count) {
        super(fm);
        this.mCount = count;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeRecommendFrag homeRecommendFrag = new HomeRecommendFrag();
                return homeRecommendFrag;
            case 1:
                HomeNewProductFrag homeNewProductFrag = new HomeNewProductFrag();
                return homeNewProductFrag;
            case 2:
                HomeBestFrag homeBestFrag = new HomeBestFrag();
                return homeBestFrag;
            case 3:
                HomeFrugalProductFrag homeFrugalProductFrag = new HomeFrugalProductFrag();
                return homeFrugalProductFrag;
            case 4:
                HomeEventFrag homeEventFrag = new HomeEventFrag();
                return homeEventFrag;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mCount;
    }
}
