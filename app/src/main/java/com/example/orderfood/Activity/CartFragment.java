package com.example.orderfood.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.orderfood.Adapter.CartAdapter;
import com.example.orderfood.Database.DBhelper;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private RecyclerView rcv_cart;
    private CartAdapter rcv_cartAdapter;
    Button btn_confirmOrder;

    SQLiteDatabase sqLitedb = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);
    Cursor cursor = null, cursor1 = null;
    DBhelper db = new DBhelper(getActivity());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_cart, container, false);

        ObjectFood ob_food = new ObjectFood();
        ArrayList<ObjectFood> mList_cart = new ArrayList<ObjectFood>();
        String sql = "SELECT * FROM tb_cart WHERE cart_status = '1' ";
        cursor = sqLitedb.rawQuery(sql, null);
        mList_cart.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            int number =  cursor.getInt(3);
            byte[] image = cursor.getBlob(4);
            mList_cart.add(new ObjectFood(id, image, name, price, number));
        }

        //Hien thi list
        rcv_cart = view.findViewById(R.id.cart_rcv);
        rcv_cartAdapter = new CartAdapter(mList_cart, getActivity());
        rcv_cart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rcv_cart.setAdapter(rcv_cartAdapter);
        Fragment fragment = null;

        // Su kien
        btn_confirmOrder = view.findViewById(R.id.btn_confirmOrder);
        btn_confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("cart_status", 2);
                sqLitedb.update("tb_cart", values, "CART_STATUS = 1", null);
                Intent intent = new Intent(getActivity(),DashboardActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"Đặt hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}