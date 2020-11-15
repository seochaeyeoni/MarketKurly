package com.example.marketkurly.src.main.home.recommend.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketkurly.R;
import com.example.marketkurly.src.main.home.recommend.Data.AdData;

import java.util.ArrayList;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.CustomViewHolders> {

    private ArrayList<AdData> arrayList;

    Context context;
    AdAdapter adAdapter;



    public AdAdapter(ArrayList<AdData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdAdapter.CustomViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad, parent, false);
        CustomViewHolders holders = new CustomViewHolders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolders holder, int position) {
        holder.iv_ad.setImageResource(arrayList.get(position).getIv_ad());
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

        protected ImageView iv_ad;

        public CustomViewHolders(@NonNull View itemView) {
            super(itemView);
            this.iv_ad = (ImageView) itemView.findViewById(R.id.iv_ad);

        }
    }
}
