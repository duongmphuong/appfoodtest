package com.example.orderfood.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orderfood.Adapter.DeliveryAdapter;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class DeliveryFragment extends Fragment {

    private RecyclerView rcv1_item;
    private DeliveryAdapter delivery_Adapter;
    SQLiteDatabase sqLitedb = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);
    Cursor cursor = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_info, container, false);

        ArrayList<ObjectFood> item_rcv3 = new ArrayList<>();

        String sql = "SELECT * FROM tb_cart where CART_STATUS = 2";
        cursor = sqLitedb.rawQuery(sql, null);
        item_rcv3.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            int number =  cursor.getInt(3);
            byte[] image = cursor.getBlob(4);
            item_rcv3.add(new ObjectFood(id, image, name, price, number));
        }

        rcv1_item = view.findViewById(R.id.delivery_rcv);
        delivery_Adapter = new DeliveryAdapter(item_rcv3,getActivity());
        rcv1_item.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rcv1_item.setAdapter(delivery_Adapter);



        return view;
    }
}