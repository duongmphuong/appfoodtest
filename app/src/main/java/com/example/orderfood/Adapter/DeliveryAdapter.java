package com.example.orderfood.Adapter;

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

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryHolder>{

    private ArrayList<ObjectFood> ob_food;

    public Context mcontex;

    public DeliveryAdapter(ArrayList<ObjectFood> ob_food, Context mcontex) {
        this.ob_food = ob_food;
        this.mcontex = mcontex;
    }

    SQLiteDatabase sqlitedb = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);



    @NonNull
    @Override
    public DeliveryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item, parent, false);

        return new DeliveryAdapter.DeliveryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryHolder holder, int position) {

        ObjectFood currentItem = ob_food.get(position);
        if(currentItem == null){
            return;
        }

        // Khác null thực hiện
        Bitmap bitmap = BitmapFactory.decodeByteArray(currentItem.getImageFood(), 0, currentItem.getImageFood().length);
        holder.delivery_image.setImageBitmap(bitmap);
        holder.delivery_name.setText(currentItem.getNameFood());
        holder.delivery_totalPrice.setText(currentItem.getPriceFood());
        holder.delivery_quantity.setText(String.valueOf(currentItem.getNumber()));

        float total = currentItem.getNumber()*Float.parseFloat(currentItem.getPriceFood());

        holder.delivery_totalPrice.setText(String.valueOf(total));

        holder.btn_danhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String index = "CART_ID = "+ currentItem.getId()+"";
                sqlitedb.delete("tb_cart", index, null);
                Toast.makeText(mcontex,"Cảm ơn bạn đã đặt hàng!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(ob_food != null){
            return ob_food.size();
        }
        return 0;
    }

    public class DeliveryHolder extends RecyclerView.ViewHolder{

        public ImageView delivery_image;
        public TextView delivery_name, btn_danhGia;
        public TextView delivery_totalPrice;
        public TextView delivery_quantity;

        public DeliveryHolder(@NonNull View itemView) {
            super(itemView);

            delivery_image = itemView.findViewById(R.id.delivery_imageFood);
            delivery_name = itemView.findViewById(R.id.delivery_nameFood);
            delivery_totalPrice = itemView.findViewById(R.id.delivery_totalPrice);
            delivery_quantity = itemView.findViewById(R.id.delivery_quantity);
            btn_danhGia = itemView.findViewById(R.id.btn_danhGia);
        }
    }
}
