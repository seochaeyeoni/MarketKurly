package com.example.marketkurly.src.select;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marketkurly.R;
import com.example.marketkurly.src.main.home.recommend.Data.ProductData;
import com.example.marketkurly.src.product.ProductActivity;
import com.example.marketkurly.src.select.interfaces.OnItemClick;
import com.example.marketkurly.src.select.models.BasketAddBody;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static com.example.marketkurly.src.select.models.BasketAddBody.*;

public class SelectAdapter extends BaseAdapter {

    LayoutInflater mLayoutInflater = null;
    private ArrayList<SelectData> arrayList;
    public ArrayList<BasketAdd> basketArrayList = new ArrayList<>();
    int total_price = 0; int price = 0;
    int mCount, mOptionIdx, mProductIdx;


    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SelectData getItem(int position) { return arrayList.get(position); }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = mLayoutInflater.inflate(R.layout.item_select, null);

        TextView tv_product_name = (TextView) view.findViewById(R.id.tv_product_name);
        TextView tv_product_price = (TextView) view.findViewById(R.id.tv_product_price);
        TextView tv_product_price_before = (TextView) view.findViewById(R.id.tv_product_price_before);
        final TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
        final Button plus_count = (Button) view.findViewById(R.id.plus_count_btn);
        final Button minus_count = (Button) view.findViewById(R.id.minus_count_btn);

        tv_product_name.setText(arrayList.get(position).getTv_option_name());
        tv_product_price.setText(String.valueOf(arrayList.get(position).getTv_price()));
        if (arrayList.get(position).getTv_price() == arrayList.get(position).getTv_price_before()) {
            //그러면 세일 전 가격은 필요없지!
            tv_product_price_before.setVisibility(View.GONE);
        } else {
            tv_product_price_before.setText(String.valueOf(arrayList.get(position).getTv_price_before()));
        }

        //취소선 긋기
        tv_product_price_before.setPaintFlags(tv_product_price_before.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);


        final int[] plusCount = {0};
        final int[] minusCount = {0};

        //plus 버튼 눌렀을 때
        plus_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusCount[0] += 1;
                if ( plusCount[0] <= minusCount[0]) {
                    plusCount[0] = 1 + minusCount[0];
                }
                tv_count.setText(String.valueOf(plusCount[0] - minusCount[0]));
                price = arrayList.get(position).getTv_price();
                // selectData arrayList에서 포지션으로 객체 가져와서 셋카운트
                arrayList.get(position).setCount(Integer.parseInt(tv_count.getText().toString()));
                increment();
                basketAddBodyDeliver();
            }
        });
        //minus 버튼 눌렀을 때
        minus_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusCount[0] += 1;
                if ( minusCount[0] <= plusCount[0] ){
                    tv_count.setText(String.valueOf(plusCount[0] - minusCount[0]));
                    price = arrayList.get(position).getTv_price()*(-1);
                    decrement();
                } else {
                    minusCount[0] = plusCount[0];
                    tv_count.setText(String.valueOf(0));
                }

                // selectData arrayList에서 포지션으로 객체 가져와서 셋카운트
                arrayList.get(position).setCount(Integer.parseInt(tv_count.getText().toString()));
//                //한 번 시도...
//                BasketAdd basketAdd = new BasketAdd();
//                basketAdd.count = Integer.parseInt(tv_count.getText().toString());
//                basketAdd.optionIdx = arrayList.get(position).getTv_option_id();
//                basketAdd.productIdx = arrayList.get(position).getTv_product_id();
//                basketArrayList.add(basketAdd);
                basketAddBodyDeliver();

            }
        });



        return view;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    private OnItemClick mCallback;

    public SelectAdapter(ArrayList<SelectData> arrayList, Context mContext, OnItemClick listener) {
        this.arrayList = arrayList;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mCallback = listener;
    }

    public void increment(){
       total_price += price;
       mCallback.onClick(total_price);
    }

    public void decrement(){
        total_price += price;
        mCallback.onClick(total_price);
    }

    public void basketAddBodyDeliver(){
        mCallback.onDelivery(arrayList);
    }


//    @NonNull
//    @Override
//    public SelectAdapter.CustomViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select, parent, false);
//        CustomViewHolders holders = new CustomViewHolders(view);
//        return holders;
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final CustomViewHolders holder, final int position) {
//
//
//
//
//        holder.tv_product_name.setText(arrayList.get(position).getTv_option_name());
//        holder.tv_product_price.setText(String.valueOf(arrayList.get(position).getTv_price()));
//        if (arrayList.get(position).getTv_price() == arrayList.get(position).getTv_price_before()) {
//            //그러면 세일 전 가격은 필요없지!
//            holder.tv_product_price_before.setVisibility(View.GONE);
//        } else {
//            holder.tv_product_price_before.setText(String.valueOf(arrayList.get(position).getTv_price_before()));
//        }
//
//
////        //아이템 클릭 시 상품상세 액티비티로 넘어가게끔(productIdx 같이 넘기고!)
////        holder.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                int id = arrayList.get(position).getTv_product_id();
////                Intent intent = new Intent(holder.itemView.getContext(), ProductActivity.class);
////                intent.putExtra("id",id);
////                holder.itemView.getContext().startActivity(intent);
////            }
////        });
//
//        //plus 버튼 눌렀을 때
//        holder.getBtnTest().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClick(v, position);
//                count += 1;
//                holder.tv_count.setText(String.valueOf(count));
//            }
//        });
//        //minus 버튼 눌렀을 때
//        holder.plus_count.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count -= 1;
//                if (count < 0) { count = 0; }
//                holder.tv_count.setText(String.valueOf(count));
//            }
//        });
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return (null != arrayList ? arrayList.size() : 0);
//    }
//
//    public void remove(int position) {
//        try {
//            arrayList.remove(position);
//            notifyItemRemoved(position);
//        } catch (IndexOutOfBoundsException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
//
//    public class CustomViewHolders extends RecyclerView.ViewHolder {
//
//        protected TextView tv_product_name;
//        protected TextView tv_product_price;
//        protected TextView tv_product_price_before;
//        protected TextView tv_count;
//
//
//        public CustomViewHolders(@NonNull View itemView) {
//            super(itemView);
//            this.tv_product_name
//            this.tv_product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
//            this.tv_product_price_before = (TextView) itemView.findViewById(R.id.tv_product_price_before);
//            this.tv_count = (TextView) itemView.findViewById(R.id.tv_count);
//            plus_count = (Button) itemView.findViewById(R.id.plus_count_btn);
//            minus_count = (Button) itemView.findViewById(R.id.minus_count_btn);
//
//            //취소선 긋기
//            tv_product_price_before.setPaintFlags(tv_product_price_before.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
//        }
//    }
}
