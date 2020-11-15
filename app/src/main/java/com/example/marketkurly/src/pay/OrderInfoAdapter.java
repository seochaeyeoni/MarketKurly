package com.example.marketkurly.src.pay;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.marketkurly.R;
import com.example.marketkurly.src.pay.models.PayScreenResponse;
import com.example.marketkurly.src.select.SelectData;
import com.example.marketkurly.src.select.interfaces.OnItemClick;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class OrderInfoAdapter extends BaseAdapter {

    FirebaseStorage storage = FirebaseStorage.getInstance("gs://marketcurly-e978c.appspot.com");

    LayoutInflater mLayoutInflater = null;
    private ArrayList<PayScreenResponse.OrderInfo> arrayList;


    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public PayScreenResponse.OrderInfo getItem(int position) { return arrayList.get(position); }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = mLayoutInflater.inflate(R.layout.item_order_info, null);

        TextView name = (TextView) view.findViewById(R.id.order_info_name);
        TextView count = (TextView) view.findViewById(R.id.order_info_count);
        TextView price = (TextView) view.findViewById(R.id.order_info_price);
        TextView price_before = (TextView) view.findViewById(R.id.order_info_price_before);
        final ImageView image = (ImageView) view.findViewById(R.id.order_info_iv);

        StorageReference gsReference = storage.getReferenceFromUrl(arrayList.get(position).getProductImg());
        gsReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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

        name.setText(arrayList.get(position).getProductName());
        count.setText(String.format("%s개", String.valueOf(arrayList.get(position).getOptionCount())));
        price.setText(String.format("%s원", String.valueOf(arrayList.get(position).getClientPrice())));
        if (arrayList.get(position).getClientPrice() == arrayList.get(position).getOriginalPrice()) {
            //그러면 세일 전 가격은 필요없지!
            price_before.setVisibility(View.GONE);
        } else {
            price_before.setText(String.format("%s원", String.valueOf(arrayList.get(position).getOriginalPrice())));
        }

        //취소선 긋기
        price_before.setPaintFlags(price_before.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);


        return view;
    }


    public OrderInfoAdapter(ArrayList<PayScreenResponse.OrderInfo> arrayList, Context mContext) {
        this.arrayList = arrayList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

}
