package com.example.marketkurly.src.basket;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.marketkurly.R;
import com.example.marketkurly.src.basket.interfaces.BasketOnItemClick;
import com.example.marketkurly.src.basket.models.BasketInfoResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BasketNormalAdapter extends BaseAdapter {


    FirebaseStorage storage = FirebaseStorage.getInstance("gs://marketcurly-e978c.appspot.com");

    LayoutInflater mLayoutInflater = null;
    private ArrayList<BasketInfoResponse.NormalProduct> arrayList;
    int price = 0; int price_before = 0;
    int price_current = 0; int price_current_before = 0;
    int price_one = 0; int price_before_one = 0;
    int mCount, mOptionIdx, mProductIdx;
    boolean checked = true;


    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public BasketInfoResponse.NormalProduct getItem(int position) {
        return arrayList.get(position);
    }


    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final View view = mLayoutInflater.inflate(R.layout.item_basket, null);

        TextView product_name = (TextView) view.findViewById(R.id.basket_product_name);
        TextView option_name = (TextView) view.findViewById(R.id.basket_option_name);
        final ImageView image = (ImageView) view.findViewById(R.id.basket_image);
        final TextView product_price = (TextView) view.findViewById(R.id.basket_price);
        final TextView product_price_before = (TextView) view.findViewById(R.id.basket_price_before);
        final TextView product_price_before_tv = (TextView) view.findViewById(R.id.basket_price_before_tv);
        final TextView count = (TextView) view.findViewById(R.id.basket_count);
        final Button plus_count = (Button) view.findViewById(R.id.plus_count_btn);
        final Button minus_count = (Button) view.findViewById(R.id.minus_count_btn);
        ImageButton x_btn = (ImageButton) view.findViewById(R.id.basket_delete_btn);
        final ImageView check = (ImageView) view.findViewById(R.id.basket_check);
        final Drawable check_o = view.getResources().getDrawable(R.drawable.check_checked);
        final Drawable check_x = view.getResources().getDrawable(R.drawable.check_unchecked);


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

        product_name.setText(arrayList.get(position).getProductName());
        option_name.setText(arrayList.get(position).getOptionName());
        product_price.setText(String.valueOf(arrayList.get(position).getClientPrice()*arrayList.get(position).getNeedCount()));
        count.setText(String.valueOf(arrayList.get(position).getNeedCount()));
        basketChangeBodyDeliver();
        if (arrayList.get(position).getClientPrice() == arrayList.get(position).getOriginalPrice()) {
            //그러면 세일 전 가격은 필요없지!
            product_price_before.setVisibility(View.GONE);
            product_price_before_tv.setVisibility(View.GONE);
        } else {
            product_price_before.setText(String.valueOf(arrayList.get(position).getOriginalPrice()*arrayList.get(position).getNeedCount()));
        }

        //취소선 긋기
        product_price_before.setPaintFlags(product_price_before.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        final int[] plusCount = {0};
        final int[] minusCount = {0};
        final int count_init = Integer.parseInt(count.getText().toString());
        final int price_init = (arrayList.get(position).getClientPrice())*(arrayList.get(position).getNeedCount());
        final int price_init_before = (arrayList.get(position).getOriginalPrice())*(arrayList.get(position).getNeedCount());



        //check 버튼 눌렀을 때
        //check 버튼 초기화
        check.setBackground(check_o);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price_current = Integer.parseInt(String.valueOf(arrayList.get(position).getClientPrice()*arrayList.get(position).getCount()));
                price_current_before = Integer.parseInt(String.valueOf(arrayList.get(position).getOriginalPrice()*arrayList.get(position).getCount()));
                if (check.getBackground() == check_o) {
                    check.setBackground(check_x);
                    arrayList.get(position).setChecked(false);
                    checked = arrayList.get(position).getChecked();
                    // 이래도 장바구니 수량이 수정되는 게 아니다!
                    //arrayList.get(position).setCount(0);
                } else {
                    check.setBackground(check_o);
                    arrayList.get(position).setChecked(true);
                    checked = arrayList.get(position).getChecked();
                    // selectData arrayList에서 포지션으로 객체 가져와서 셋카운트
                    //arrayList.get(position).setCount(Integer.parseInt(count.getText().toString()));
                }
                isCheckedPrice();
                productCountChange();
                basketChangeBodyDeliver();
            }
        });

        //plus 버튼 눌렀을 때
        plus_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusCount[0] += 1;
                count.setText(String.valueOf(count_init + plusCount[0] - minusCount[0]));
                // selectData arrayList에서 포지션으로 객체 가져와서 셋카운트
                arrayList.get(position).setCount(Integer.parseInt(String.valueOf(count.getText())));
                //하나당 가격
                price = arrayList.get(position).getClientPrice();
                price_before = arrayList.get(position).getOriginalPrice();
                price_one = arrayList.get(position).getClientPrice();
                price_before_one = arrayList.get(position).getOriginalPrice();
                price_current = Integer.parseInt(String.valueOf(price_one*arrayList.get(position).getCount()));
                price_current_before = Integer.parseInt(String.valueOf(price_before_one*arrayList.get(position).getCount()));
                //각 아이템의 가격들도 바꿔준다
                product_price.setText(String.valueOf(price_current));
                product_price_before.setText(String.valueOf(price_current_before));
                increment();
                basketChangeBodyDeliver();
            }
        });
        //minus 버튼 눌렀을 때
        minus_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusCount[0] += 1;
                price_one = arrayList.get(position).getClientPrice();
                price_before_one = arrayList.get(position).getOriginalPrice();
                if (minusCount[0] < count_init + plusCount[0]) {
                    count.setText(String.valueOf(count_init + plusCount[0] - minusCount[0]));
                    price = arrayList.get(position).getClientPrice()*(-1);
                    price_before = arrayList.get(position).getOriginalPrice()*(-1);
                    // selectData arrayList에서 포지션으로 객체 가져와서 셋카운트
                    arrayList.get(position).setCount(Integer.parseInt(String.valueOf(count.getText())));
                    decrement();
                } else {
                    minusCount[0] = count_init +  plusCount[0] - 1;
                    count.setText(String.valueOf(1));
                    // selectData arrayList에서 포지션으로 객체 가져와서 셋카운트
                    arrayList.get(position).setCount(Integer.parseInt(String.valueOf(count.getText())));
                }
                price_current = Integer.parseInt(String.valueOf(price_one*arrayList.get(position).getCount()));
                price_current_before = Integer.parseInt(String.valueOf(price_before_one*arrayList.get(position).getCount()));
                basketChangeBodyDeliver();
                //각 아이템의 가격들도 바꿔준다
                product_price.setText(String.valueOf(price_current));
                product_price_before.setText(String.valueOf(price_current_before));
            }
        });

        //x 버튼 눌렀을 때
        x_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDialog("삭제하시겠습니까?", view.getContext(), position);
            }
        });


        return view;
    }


    private BasketOnItemClick mCallback;

    public BasketNormalAdapter(ArrayList<BasketInfoResponse.NormalProduct> arrayList, Context mContext, BasketOnItemClick listener) {
        this.arrayList = arrayList;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mCallback = listener;
    }

    public void increment() {
        mCallback.onClickNormal(price, price_before);

    }

    public void decrement() {
        mCallback.onClickNormal(price, price_before);

    }

    public void isCheckedPrice() {
        if (checked) {
            mCallback.onClickNormal(price_current, price_current_before);
        } else {
            mCallback.onClickNormal(-price_current, -price_current_before);
        }
    }

    public void productCountChange() {
        if (checked) {
            mCallback.onProductCountDelivery(1);
        } else {
            mCallback.onProductCountDelivery(-1);
        }
    }

    public void basketChangeBodyDeliver(){
        mCallback.onNormalDelivery(arrayList);
    }


    public void onClickDialog(String message, Context context, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(message);


        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                this.notify();
                alertDialog.dismiss();
                mOptionIdx = arrayList.get(position).getOptionIdx();
            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

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
