package com.example.orderfood.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderfood.Activity.LoginActivity;
import com.example.orderfood.Adapter.AdminAdapter;
import com.example.orderfood.Database.DBhelper;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    private ImageView btn_chooseImage;
    private TextView nameFood;
    private TextView detailFood;
    private TextView addFood;
    private TextView priceFood;
    private TextView btn_logout;
    final int CHOOSE_IMAGE = 307;
    byte[] imageFood;


    private RecyclerView admin_rcv;
    private AdminAdapter admin_rcvAdapter;
    ArrayList<ObjectFood> mList_rcv;

    //Database
    ObjectFood ob_food = new ObjectFood();
    DBhelper db = new DBhelper(this);
    Cursor cursor = null;
    Bitmap bitmap = null;
    SQLiteDatabase db_orderFood = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //Ánh xạ
        btn_chooseImage = findViewById(R.id.admin_imageFood);
        nameFood = findViewById(R.id.admin_nameFood);
        detailFood = findViewById(R.id.admin_detailFood);
        addFood = findViewById(R.id.btn_addFood);
        priceFood = findViewById(R.id.admin_priceFood);
        btn_logout = findViewById(R.id.admin_logout);

        // Sự kiện chọn ảnh
        btn_chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AdminActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        CHOOSE_IMAGE
                );
            }
        });

        try{
            db.createFood();
        }
        catch (Exception exception){

        }
        //Chuyển sang login
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGotoLogin = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intentGotoLogin);
            }
        });


        // Thêm ảnh
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ob_food.setNameFood(nameFood.getText() + "");
                    ob_food.setPriceFood(priceFood.getText() + "");
                    ob_food.setDetailFood(detailFood.getText() + "");
                    db.insert_Food(ob_food);
                    Toast.makeText(AdminActivity.this, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AdminActivity.this, "Thêm món ăn thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });




        ArrayList<ObjectFood> item_Food = new ArrayList<>();
        String sql = "SELECT * FROM tb_food";
        cursor = db_orderFood.rawQuery(sql, null);
        item_Food.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String db_nameFood = cursor.getString(1);
            String db_price = cursor.getString(2);
            String db_detail = cursor.getString(3);
            byte[] db_imageFood = cursor.getBlob(4);
            item_Food.add(new ObjectFood(db_imageFood, db_nameFood, db_price, db_detail));
        }

        admin_rcv = findViewById(R.id.admin_rcv);
        admin_rcvAdapter = new AdminAdapter(item_Food);
        admin_rcv.setLayoutManager(new LinearLayoutManager(AdminActivity.this, LinearLayoutManager.VERTICAL, false));
        admin_rcv.setAdapter(admin_rcvAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CHOOSE_IMAGE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent itentGoToLibary = new Intent(Intent.ACTION_PICK);
                itentGoToLibary.setType("image/*");
                startActivityForResult(itentGoToLibary, CHOOSE_IMAGE);
            }
            else{
                Toast.makeText(getApplicationContext(), "Bạn không thể truy cập thư viện ảnh", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK && data != null){

            Uri selectedImage = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

                imageFood = bos.toByteArray();

                ob_food.setImageFood(imageFood);

                btn_chooseImage.setImageBitmap(bitmap);

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}