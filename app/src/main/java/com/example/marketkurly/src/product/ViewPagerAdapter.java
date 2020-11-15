package com.example.marketkurly.src.product;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.marketkurly.src.main.home.best.HomeBestFrag;
import com.example.marketkurly.src.main.home.event.HomeEventFrag;
import com.example.marketkurly.src.main.home.frugalproduct.HomeFrugalProductFrag;
import com.example.marketkurly.src.main.home.newproduct.HomeNewProductFrag;
import com.example.marketkurly.src.main.home.recommend.HomeRecommendFrag;
import com.example.marketkurly.src.product.detail.ProductDetailFrag;
import com.example.marketkurly.src.product.image.ProductImageFrag;
import com.example.marketkurly.src.product.info.ProductInfoFrag;
import com.example.marketkurly.src.product.inquiry.ProductInquiryFrag;
import com.example.marketkurly.src.product.review.ProductReviewFrag;

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
                ProductInfoFrag productInfoFrag = new ProductInfoFrag();
                return productInfoFrag;
            case 1:
                ProductImageFrag productImageFrag = new ProductImageFrag();
                return productImageFrag;
            case 2:
                ProductDetailFrag productDetailFrag = new ProductDetailFrag();
                return productDetailFrag;
            case 3:
                ProductReviewFrag productReviewFrag = new ProductReviewFrag();
                return productReviewFrag;
            case 4:
                ProductInquiryFrag productInquiryFrag = new ProductInquiryFrag();
                return productInquiryFrag;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mCount;
    }
}
