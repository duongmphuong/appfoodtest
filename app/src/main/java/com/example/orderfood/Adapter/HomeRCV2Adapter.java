package com.example.orderfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Activity.ItemRCV2;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class HomeRCV2Adapter extends RecyclerView.Adapter<HomeRCV2Adapter.HomeRecyclerView2Holder> {

    private ArrayList<ObjectFood> mList_rcv;

    private Context mContextt;

    public HomeRCV2Adapter(ArrayList<ObjectFood> mList_rcv, Context mContextt) {
        this.mList_rcv = mList_rcv;
        this.mContextt = mContextt;
    }

    @NonNull
    @Override
    public HomeRecyclerView2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rcv2_item, parent, false);



        return new HomeRCV2Adapter.HomeRecyclerView2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerView2Holder holder, int position) {

        // Hiển thị dữ liệu lên List
        ObjectFood currentItem = mList_rcv.get(position);
        if(currentItem == null){
            return;
        }

        // Khác null thực hiện
        Bitmap bitmap = BitmapFactory.decodeByteArray(currentItem.getImageFood(), 0, currentItem.getImageFood().length);
        holder.rcv2_image.setImageBitmap(bitmap);
        holder.rcv2_name.setText(currentItem.getNameFood());
        holder.rcv2_price.setText(currentItem.getPriceFood());
        holder.item_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToItemRCV2(currentItem);
            }
        });


    }

    private void onClickGoToItemRCV2(ObjectFood currentItem) {
        Intent itentGotoItem_rcv2 = new Intent(mContextt, ItemRCV2.class);
        Bundle bundleItem_rcv2 = new Bundle();
        bundleItem_rcv2.putSerializable("item_rcv2", currentItem);
        itentGotoItem_rcv2.putExtras(bundleItem_rcv2);
        mContextt.startActivity(itentGotoItem_rcv2);
    }

    @Override
    public int getItemCount() {
        if(mList_rcv != null){
            return mList_rcv.size();
        }
        return 0;
    }


    public void clearContext(){
        mContextt = null;
    }

    public class HomeRecyclerView2Holder extends RecyclerView.ViewHolder{

        private ImageView rcv2_image;
        private TextView rcv2_name;
        private TextView rcv2_price;
        private ConstraintLayout item_select;

        public HomeRecyclerView2Holder(@NonNull View itemView) {
            super(itemView);

            // Ánh xạ
            rcv2_image = itemView.findViewById(R.id.home_rcv2_image);
            rcv2_name = itemView.findViewById(R.id.home_rcv2_name);
            rcv2_price = itemView.findViewById(R.id.home_rcv2_price);
            item_select = itemView.findViewById(R.id.home_rvc2_layoutItem);
        }
    }



}
