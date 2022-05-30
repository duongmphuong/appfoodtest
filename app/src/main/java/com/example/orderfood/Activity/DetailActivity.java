package com.example.orderfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderfood.Adapter.DetailAdapter;
import com.example.orderfood.Database.DBhelper;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ImageView btn_goBack;
    private RecyclerView rcv1_Detail;
    private DetailAdapter rcv_detailAdapter;

    //Database
    DBhelper db = new DBhelper(this);
    SQLiteDatabase sqLitedb = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);
    Cursor cursor = null, cursor1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        ObjectFood item_rcv3 = (ObjectFood) bundle.get("objectFood");
        TextView nameFood = findViewById(R.id.detail_imageName);
        nameFood.setText(item_rcv3.getNameFood());

        ImageView imageFood = findViewById(R.id.detail_image);
        Bitmap bitmap = BitmapFactory.decodeByteArray(item_rcv3.getImageFood(), 0, item_rcv3.getImageFood().length);
        imageFood.setImageBitmap(bitmap);
        TextView detailFood = findViewById(R.id.detail_imageDetail);
        detailFood.setText(item_rcv3.getDetailFood());

        TextView priceFood = findViewById(R.id.detail_moneyDetail);
        priceFood.setText(item_rcv3.getPriceFood());

        btn_goBack = findViewById(R.id.ic_back);
        btn_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGoBack = new Intent(DetailActivity.this, DashboardActivity.class);
                startActivity(intentGoBack);

            }
        });

        try{
            db.createCart();
        }
        catch (Exception exception){

        }
        TextView btn_addCart1 = findViewById(R.id.btn_addCart1);
        btn_addCart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.insertCart(item_rcv3);
                Toast.makeText(DetailActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();

            }
        });


        //Recycle view
        ArrayList<ObjectFood> mList_detail = new ArrayList<ObjectFood>();
        String sql = "SELECT * FROM tb_food";
        cursor = sqLitedb.rawQuery(sql, null);
        mList_detail.clear();
        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String detail = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            mList_detail.add(new ObjectFood(image, name, price, detail));

            rcv1_Detail = findViewById(R.id.detail_rcv1);
            rcv_detailAdapter = new DetailAdapter(mList_detail, this);
            rcv1_Detail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            rcv1_Detail.setAdapter(rcv_detailAdapter);

        }
    }

}