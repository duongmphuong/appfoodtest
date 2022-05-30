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

import com.example.orderfood.Activity.DetailActivity;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class HomeRCV3Adapter extends RecyclerView.Adapter<HomeRCV3Adapter.HomeRecyclerView3Holder> {

    public ArrayList<ObjectFood> mList_rcv;

    private Context mContext;

    public HomeRCV3Adapter(ArrayList<ObjectFood> mList_rcv3, Context mContext) {
        this.mList_rcv = mList_rcv3;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HomeRecyclerView3Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rcv3_item, parent, false);

        return new HomeRCV3Adapter.HomeRecyclerView3Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerView3Holder holder, int position) {

        final ObjectFood currentItem = mList_rcv.get(position);
        if(currentItem == null){
            return;
        }

        // Khác null thực hiện
        Bitmap bitmap = BitmapFactory.decodeByteArray(currentItem.getImageFood(), 0, currentItem.getImageFood().length);
        holder.rcv3_image.setImageBitmap(bitmap);
        holder.rcv3_name.setText(currentItem.getNameFood());
        holder.rcv3_details.setText(currentItem.getDetailFood());

        holder.rcv3_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(currentItem);
            }
        });


    }

    public void clearContext(){
        mContext = null;
    }

    private void onClickGoToDetail(ObjectFood currentItem) {
        Intent intentGoToDetail = new Intent(mContext, DetailActivity.class);
        Bundle bundleDetail = new Bundle();
        bundleDetail.putSerializable("objectFood", currentItem);
        intentGoToDetail.putExtras(bundleDetail);
        mContext.startActivity(intentGoToDetail);
    }


    @Override
    public int getItemCount() {
        if(mList_rcv != null){
            return mList_rcv.size();
        }
        return 0;
    }

    public class HomeRecyclerView3Holder extends RecyclerView.ViewHolder{

        public ImageView rcv3_image;
        public TextView rcv3_name;
        public TextView rcv3_details;
        public TextView rcv3_price;
        public ConstraintLayout rcv3_layout;

        public HomeRecyclerView3Holder(@NonNull View itemView) {
            super(itemView);
            rcv3_image = itemView.findViewById(R.id.home_rcv3_image);
            rcv3_name = itemView.findViewById(R.id.home_rcv3_name);
            rcv3_details = itemView.findViewById(R.id.home_rcv3_details);
            rcv3_layout = itemView.findViewById(R.id.home_rvc3_layoutItem);

        }
    }
}