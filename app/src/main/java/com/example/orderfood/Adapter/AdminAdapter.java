package com.example.orderfood.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminRecyclerViewHolder>{

    ArrayList<ObjectFood> mList_rcv;

    public AdminAdapter(ArrayList<ObjectFood> mList_rcv) {
        this.mList_rcv = mList_rcv;
    }

    @NonNull
    @Override
    public AdminRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_rcv_item, parent, false);

        return new AdminAdapter.AdminRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminRecyclerViewHolder holder, int position) {

        ObjectFood currentItem = mList_rcv.get(position);
        if(currentItem == null){
            return;
        }
        // Khác null thực hiện
        Bitmap bitmap = BitmapFactory.decodeByteArray(currentItem.getImageFood(), 0, currentItem.getImageFood().length);
        holder.imageFood.setImageBitmap(bitmap);
        holder.nameFood.setText(currentItem.getNameFood());
        holder.detailFood.setText(currentItem.getDetailFood());
        holder.priceFood.setText(currentItem.getPriceFood());
    }

    @Override
    public int getItemCount() {
        if(mList_rcv != null){
            return mList_rcv.size();
        }
        return 0;
    }

    public class AdminRecyclerViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageFood;
        private TextView nameFood;
        private TextView detailFood;
        private TextView priceFood;

        public AdminRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFood = itemView.findViewById(R.id.admin_imageFoodd);
            nameFood = itemView.findViewById(R.id.admin_item_nameFood);
            detailFood = itemView.findViewById(R.id.admin_item_detail);
            priceFood = itemView.findViewById(R.id.admin_item_priceFood);
        }
    }

}
