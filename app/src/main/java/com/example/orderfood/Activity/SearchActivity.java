package com.example.orderfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderfood.Adapter.CartAdapter;
import com.example.orderfood.Adapter.SearchAdapter;
import com.example.orderfood.Database.DBhelper;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ImageView ic_backHome;
    private TextView nameFoodForSearch;
    private RecyclerView rcv_searchItem;
    private SearchAdapter searchAdapter;

    SQLiteDatabase sqLitedb = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);
    Cursor cursor = null, cursor1 = null;
    DBhelper db = new DBhelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String nameFood = getIntent().getStringExtra("nameFood");
        nameFoodForSearch = findViewById(R.id.nameFoodForSearch);
        nameFoodForSearch.setText(nameFood);

//        // Hiển thị
        ObjectFood ob_food = new ObjectFood();
        ArrayList<ObjectFood> mListSearch = new ArrayList<ObjectFood>();
        String sql = "SELECT * FROM tb_food WHERE FOOD_NAME LIKE '%"+nameFoodForSearch.getText().toString()+"%'";
        cursor = sqLitedb.rawQuery(sql, null);
        mListSearch.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String detail = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            mListSearch.add(new ObjectFood(id, image, name, price, detail));
        }

        rcv_searchItem = findViewById(R.id.rcv_searchItem);
        searchAdapter =  new SearchAdapter(this, mListSearch);
        rcv_searchItem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv_searchItem.setAdapter(searchAdapter);
        Fragment fragment = null;


        ic_backHome = findViewById(R.id.ic_backHome);
        ic_backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });



    }
}