package com.example.orderfood.Adapter;

import android.content.ContentValues;
import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder>{

    public ArrayList<ObjectFood> mList_Search;
    public Context mcontext;

    public SearchAdapter(Context context, ArrayList<ObjectFood> mList_Search) {
        this.mcontext = context;
        this.mList_Search = mList_Search;
    }

    SQLiteDatabase sqLitedb = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_rcv1_item, parent, false);

        return new SearchAdapter.SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {

        ObjectFood currentItem = mList_Search.get(position);
        if(currentItem == null){
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(currentItem.getImageFood(), 0, currentItem.getImageFood().length);
        holder.imageFood.setImageBitmap(bitmap);
        holder.nameFood.setText(currentItem.getNameFood());
        holder.priceFood.setText(currentItem.getPriceFood());
        holder.detail.setText(currentItem.getDetailFood());

        holder.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ContentValues values = new ContentValues();
                    values.put("cart_name", currentItem.getNameFood());
                    values.put("cart_price", currentItem.getPriceFood());
                    values.put("cart_image", currentItem.getImageFood());
                    sqLitedb.insert("tb_cart", null, values);
                    Toast.makeText(mcontext, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(mcontext, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList_Search != null){
            return mList_Search.size();
        }
        return 0;
    }

    public class SearchHolder extends RecyclerView.ViewHolder{
        private ImageView imageFood;

        private TextView nameFood;

        private TextView priceFood;

        private TextView addCart;

        private TextView detail;
        public SearchHolder(@NonNull View itemView) {
            super(itemView);

            imageFood = itemView.findViewById(R.id.detail_itemImage);
            nameFood = itemView.findViewById(R.id.detail_itemNameFood);
            priceFood = itemView.findViewById(R.id.detail_itemDetail);
            addCart = itemView.findViewById(R.id.btn_addCart);
            detail = itemView.findViewById(R.id.detail_money);
        }
    }
}
