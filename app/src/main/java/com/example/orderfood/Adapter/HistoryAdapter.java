package com.example.orderfood.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Database.DBhelper;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder>{

    public ArrayList<ObjectFood> mList_history;

    public HistoryAdapter(ArrayList<ObjectFood> mList_history) {
        this.mList_history = mList_history;
    }

    SQLiteDatabase sqLitedb = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);

        return new HistoryAdapter.HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {

        ObjectFood currentItem = mList_history.get(position);
        if(currentItem == null){
            return;
        }
        holder.history_nameFood.setText(currentItem.getNameFood());
        Bitmap bitmap = BitmapFactory.decodeByteArray(currentItem.getImageFood(), 0, currentItem.getImageFood().length);
        holder.history_imageFood.setImageBitmap(bitmap);
        holder.history_quantity.setText(String.valueOf(currentItem.getNumber()));
        int number = currentItem.getNumber();
        float price = Float.parseFloat(currentItem.getPriceFood());
        float total = number*price;
        holder.history_totalPrice.setText(String.valueOf(total));
    }

    @Override
    public int getItemCount() {
        return mList_history.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder{

        public ImageView history_imageFood;
        public TextView history_nameFood, history_quantity;
        public TextView history_totalPrice;
        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            history_imageFood = itemView.findViewById(R.id.history_imageFood);
            history_nameFood = itemView.findViewById(R.id.history_nameFood);
            history_quantity = itemView.findViewById(R.id.history_quantity);
            history_totalPrice = itemView.findViewById(R.id.history_totalPrice);
        }
    }
}
