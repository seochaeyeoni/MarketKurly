package com.example.marketkurly.src.pay;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marketkurly.R;
import com.example.marketkurly.src.pay.models.CouponResponse;
import com.example.marketkurly.src.pay.models.PayScreenResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class CouponAdapter extends BaseAdapter {

    FirebaseStorage storage = FirebaseStorage.getInstance("gs://marketcurly-e978c.appspot.com");

    LayoutInflater mLayoutInflater = null;
    private ArrayList<CouponResponse.Result> arrayList;


    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public CouponResponse.Result getItem(int position) { return arrayList.get(position); }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = mLayoutInflater.inflate(R.layout.item_coupon, null);

        TextView discount = (TextView) view.findViewById(R.id.coupon_discount);
        TextView discount_tv = (TextView) view.findViewById(R.id.coupon_discount_tv);
        TextView title = (TextView) view.findViewById(R.id.coupon_title);
        TextView subtitle = (TextView) view.findViewById(R.id.coupon_subtitle);
        TextView expiration = (TextView) view.findViewById(R.id.coupon_expiration);
        //int hintColor = ContextCompat.getColor(convertView.getContext(), R.color.edit_text_hint);

        discount.setText(String.valueOf(arrayList.get(position).getDiscount()));
        title.setText(String.format("[%s]%s", arrayList.get(position).getCouponName(),
                arrayList.get(position).getContents()));
        subtitle.setText(arrayList.get(position).getContents());
        expiration.setText(arrayList.get(position).getExpiration());
        if (arrayList.get(position).getIsAvailable().equals("Y")) {}
        else {
            discount_tv.setVisibility(View.GONE);
            discount.setText("사용불가");
            discount.setTextColor(Color.parseColor("#dddddd"));
            title.setTextColor(Color.parseColor("#dddddd"));
            subtitle.setTextColor(Color.parseColor("#dddddd"));
            expiration.setTextColor(Color.parseColor("#dddddd"));
        }

        return view;
    }


    public CouponAdapter(ArrayList<CouponResponse.Result> arrayList, Context mContext) {
        this.arrayList = arrayList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

}
