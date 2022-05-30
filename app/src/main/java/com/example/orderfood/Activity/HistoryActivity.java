package com.example.orderfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.orderfood.Adapter.CartAdapter;
import com.example.orderfood.Adapter.DeliveryAdapter;
import com.example.orderfood.Adapter.HistoryAdapter;
import com.example.orderfood.Database.DBhelper;
import com.example.orderfood.MainActivity;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.Model.Person;
import com.example.orderfood.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView rcv_history;
    private HistoryAdapter historyAdapter;
    ImageView goBack;


    SQLiteDatabase sqLitedb = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);
    Cursor cursor = null;
    DBhelper db = new DBhelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ArrayList<ObjectFood> mList_history = new ArrayList<>();

        String sql = "SELECT * FROM tb_cart where CART_STATUS = 2";
        cursor = sqLitedb.rawQuery(sql, null);
        mList_history.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            int number =  cursor.getInt(3);
            byte[] image = cursor.getBlob(4);
            mList_history.add(new ObjectFood(id, image, name, price, number));
       }

        rcv_history = findViewById(R.id.history_rcv);
        historyAdapter = new HistoryAdapter(mList_history);
        rcv_history.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv_history.setAdapter(historyAdapter);



        //Quay lai
        goBack = findViewById(R.id.ic_backhome);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(HistoryActivity.this, DashboardActivity.class);
                startActivity(goBack);
            }
        });


    }
}