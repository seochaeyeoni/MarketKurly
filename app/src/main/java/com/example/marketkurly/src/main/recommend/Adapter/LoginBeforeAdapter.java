package com.example.marketkurly.src.main.recommend.Adapter;

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
import com.example.marketkurly.src.main.recommend.Data.DummyData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class LoginBeforeAdapter extends RecyclerView.Adapter<LoginBeforeAdapter.CustomViewHolders> {

    private ArrayList<DummyData> arrayList;

    public LoginBeforeAdapter(ArrayList<DummyData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public LoginBeforeAdapter.CustomViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_recommend, parent, false);
        CustomViewHolders holders = new CustomViewHolders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolders holder, final int position) {

        holder.iv_product.setImageResource(arrayList.get(position).getIv_product());
        holder.tv_product_name.setText(arrayList.get(position).getTv_product_name());
        holder.tv_product_price.setText(arrayList.get(position).getTv_product_price());
        if (arrayList.get(position).getTv_product_price() == arrayList.get(position).getTv_product_price_before()) {
            //그러면 세일 전 가격은 필요없지!
            holder.tv_product_price_before.setVisibility(View.GONE);
        } else {
            holder.tv_product_price_before.setText(arrayList.get(position).getTv_product_price_before());
        }
        if (arrayList.get(position).getTv_sale_percent().equals("0")) {
            //세일 퍼센트 보여주는 부분 기본 속성을 INVISIBLE 으로 해 놓자. 그럼 여기서 할 일X
        } else {
            //세일 퍼센트 보여주는 부분 VISIBLE 로 변경
            holder.sale_box.setVisibility(View.VISIBLE);
            holder.tv_product_sale_percent.setText(arrayList.get(position).getTv_sale_percent());
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

        protected ImageView iv_product;
        protected TextView tv_product_name;
        protected TextView tv_product_price;
        protected TextView tv_product_price_before;
        protected TextView tv_product_sale_percent;
        protected LinearLayout sale_box;

        public CustomViewHolders(@NonNull View itemView) {
            super(itemView);
            this.iv_product = (ImageView) itemView.findViewById(R.id.iv_product);
            this.tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
            this.tv_product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
            this.tv_product_price_before = (TextView) itemView.findViewById(R.id.tv_product_price_before);
            this.tv_product_sale_percent = (TextView) itemView.findViewById(R.id.tv_product_sale_percent);

            this.sale_box = (LinearLayout) itemView.findViewById(R.id.box_sale_percent);

            //취소선 긋기
            tv_product_price_before.setPaintFlags(tv_product_price_before.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
