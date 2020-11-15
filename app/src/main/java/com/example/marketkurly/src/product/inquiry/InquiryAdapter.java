package com.example.marketkurly.src.product.inquiry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketkurly.R;

import java.util.ArrayList;

public class InquiryAdapter extends RecyclerView.Adapter<InquiryAdapter.CustomViewHolders> {

    private ArrayList<InquiryData> arrayList;

    public InquiryAdapter(ArrayList<InquiryData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public InquiryAdapter.CustomViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_inquiry, parent, false);
        CustomViewHolders holders = new CustomViewHolders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolders holder, final int position) {
        holder.tv_title.setText(arrayList.get(position).getTv_title());
        holder.tv_user_name.setText(arrayList.get(position).getTv_user_name());
        if (arrayList.get(position).getIsLocked().equals("Y")) {
            holder.tv_isLocked.setVisibility(View.VISIBLE);
        }
        if (arrayList.get(position).getIsAnswered().equals("답변완료")) {
            holder.tv_isAnswered.setVisibility(View.VISIBLE);
        }
        holder.tv_created_date.setText(arrayList.get(position).getTv_created_date());

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

        protected TextView tv_title;
        protected TextView tv_user_name;
        protected ImageView tv_isLocked;
        protected TextView tv_created_date;
        protected ImageView tv_isAnswered;

        public CustomViewHolders(@NonNull View itemView) {
            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.inquiry_title);
            this.tv_user_name = (TextView) itemView.findViewById(R.id.inquiry_user_name);
            this.tv_isLocked = (ImageView) itemView.findViewById(R.id.inquiry_is_locked);
            this.tv_created_date = (TextView) itemView.findViewById(R.id.inquiry_created_date);
            this.tv_isAnswered = (ImageView) itemView.findViewById(R.id.inquiry_is_answered);
            

            //취소선 긋기
            //tv_product_price_before.setPaintFlags(tv_product_price_before.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
