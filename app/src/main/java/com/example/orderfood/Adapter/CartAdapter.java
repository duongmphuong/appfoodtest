package com.example.orderfood.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Activity.DashboardActivity;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartRecyclerViewHolder>{

    public ArrayList<ObjectFood> cart_item;

    public Context mcontex;

    public CartAdapter(ArrayList<ObjectFood> cart_item, Context mcontex) {
        this.cart_item = cart_item;
        this.mcontex = mcontex;
    }
    SQLiteDatabase sqLitedb = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);
    Cursor cursor = null;

    @NonNull
    @Override
    public CartRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        return new CartAdapter.CartRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewHolder holder, int position) {

        ObjectFood currentItem = cart_item.get(position);
        if(currentItem == null){
            return;
        }

        // Khác null thực hiện
        Bitmap bitmap = BitmapFactory.decodeByteArray(currentItem.getImageFood(), 0, currentItem.getImageFood().length);
        holder.cart_image.setImageBitmap(bitmap);
        holder.cart_name.setText(currentItem.getNameFood());
        holder.cart_price.setText(currentItem.getPriceFood());
        holder.cart_number.setText(String.valueOf(currentItem.getNumber()));

        int priceFood = Integer.parseInt(holder.cart_price.getText().toString());
        holder.ic_minimus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number1 = Integer.parseInt(holder.cart_number.getText()+"");
                int totalPrice = 0;
                if(number1>1){
                    number1--;
                    totalPrice = number1 * priceFood;
                    holder.cart_number.setText(String.valueOf(number1));
                    holder.cart_price.setText(String.valueOf(totalPrice));
                }


                ContentValues values = new ContentValues();
                values.put("cart_number", number1);
                String sql = "cart_id = "+ currentItem.getId()+"";
                sqLitedb.update("tb_cart", values, sql, new String[]{});
            }
        });

        holder.ic_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number1 = Integer.parseInt(holder.cart_number.getText()+"");
                int totalPrice = 0;
                number1++;
                totalPrice = number1 * priceFood;

                holder.cart_number.setText(String.valueOf(number1));
                holder.cart_price.setText(String.valueOf(totalPrice));

                ContentValues values = new ContentValues();
                values.put("cart_number", number1);
                String sql = "cart_id = "+ currentItem.getId()+"";
                sqLitedb.update("tb_cart", values, sql, new String[]{});
            }
        });
        holder.cart_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "cart_id = "+ currentItem.getId()+"";
                sqLitedb.delete("tb_cart", sql, null);
                Intent intent = new Intent(mcontex, DashboardActivity.class);
                mcontex.startActivity(intent);
                Toast.makeText(mcontex,"Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(cart_item != null){
            return cart_item.size();
        }
        return 0;
    }

    public class CartRecyclerViewHolder extends RecyclerView.ViewHolder{

        public ImageView cart_image,ic_minimus, ic_plus, cart_close;
        public TextView cart_name, cart_number;
        public TextView cart_price;
        public ConstraintLayout cart_layoutItem;

        public CartRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            cart_image = itemView.findViewById(R.id.cart_image);
            cart_name = itemView.findViewById(R.id.cart_nameFood);
            cart_price = itemView.findViewById(R.id.cart_price);
            cart_layoutItem = itemView.findViewById(R.id.cart_layoutItem);
            ic_minimus = itemView.findViewById(R.id.ic_minimus);
            ic_plus = itemView.findViewById(R.id.ic_plus);
            cart_number = itemView.findViewById(R.id.cart_number);
            cart_close = itemView.findViewById(R.id.cart_close);
        }
    }

}
