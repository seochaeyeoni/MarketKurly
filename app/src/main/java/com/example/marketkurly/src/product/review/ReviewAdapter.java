package com.example.marketkurly.src.product.review;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marketkurly.R;
import com.example.marketkurly.src.main.home.recommend.Data.ProductData;
import com.example.marketkurly.src.product.ProductActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.CustomViewHolders> {

    private ArrayList<ReviewData> arrayList;

    FirebaseStorage storage = FirebaseStorage.getInstance("gs://marketcurly-e978c.appspot.com");



    public ReviewAdapter(ArrayList<ReviewData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ReviewAdapter.CustomViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_review, parent, false);
        CustomViewHolders holders = new CustomViewHolders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolders holder, final int position) {
        holder.tv_title.setText(arrayList.get(position).getTv_title());
        holder.tv_level.setText(arrayList.get(position).getTv_level());
        holder.tv_user_name.setText(arrayList.get(position).getTv_user_name());
        holder.tv_created_date.setText(arrayList.get(position).getTv_created_date());
        if(arrayList.get(position).getIsBest().equals("Y")) {
            holder.best.setVisibility(View.VISIBLE);
        }


    }



    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position) {
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }


    public class CustomViewHolders extends RecyclerView.ViewHolder {

        protected ImageView best;
        protected TextView tv_title;
        protected TextView tv_level;
        protected TextView tv_user_name;
        protected TextView tv_created_date;

        public CustomViewHolders(@NonNull View itemView) {
            super(itemView);
            this.best = (ImageView) itemView.findViewById(R.id.review_best);
            this.tv_title = (TextView) itemView.findViewById(R.id.review_title);
            this.tv_level = (TextView) itemView.findViewById(R.id.review_level);
            this.tv_user_name = (TextView) itemView.findViewById(R.id.review_user_name);
            this.tv_created_date = (TextView) itemView.findViewById(R.id.review_created_date);


            //취소선 긋기
            //tv_product_price_before.setPaintFlags(tv_product_price_before.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
