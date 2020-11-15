package com.example.marketkurly.src.main.home.recommend.Adapter;

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

public class SaleProductAdapter extends RecyclerView.Adapter<SaleProductAdapter.CustomViewHolders> {

    private ArrayList<ProductData> arrayList;

    FirebaseStorage storage = FirebaseStorage.getInstance("gs://marketcurly-e978c.appspot.com");



    public SaleProductAdapter(ArrayList<ProductData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SaleProductAdapter.CustomViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        CustomViewHolders holders = new CustomViewHolders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolders holder, final int position) {

        StorageReference gsReference = storage.getReferenceFromUrl(arrayList.get(position).getIv_product_url());
        gsReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시
                Glide.with(holder.itemView.getContext()).load(uri).into(holder.iv_product);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
            }
        });
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

        //아이템 클릭 시 상품상세 액티비티로 넘어가게끔(productIdx 같이 넘기고!)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = arrayList.get(position).getTv_product_id();
                Intent intent = new Intent(holder.itemView.getContext(), ProductActivity.class);
                intent.putExtra("id",id);
                holder.itemView.getContext().startActivity(intent);
            }
        });
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
