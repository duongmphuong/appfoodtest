package com.example.orderfood.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.orderfood.Adapter.HomeBanner;
import com.example.orderfood.Adapter.HomeRCV1Adapter;
import com.example.orderfood.Adapter.HomeRCV2Adapter;
import com.example.orderfood.Adapter.HomeRCV3Adapter;
import com.example.orderfood.Interface.ChangeItemRCV3;
import com.example.orderfood.Model.HomeRecyclerview1;
import com.example.orderfood.Model.ImageSlider;
import com.example.orderfood.Model.ObjectFood;
import com.example.orderfood.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment implements ChangeItemRCV3 {

    // Code xử lý silder image
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private HomeBanner homeBanner;

    //Đổ dữ liệu lên recyclerview
    private RecyclerView rcv1_Data, rcv2_Data, rcv3_Data;
    private HomeRCV1Adapter rcv1_Adapter;
    private HomeRCV2Adapter rcv2_Adapter;
    private HomeRCV3Adapter rcv3_Adapter;
    private ImageView btn_search;

    private EditText nameFood;

    ArrayList<ObjectFood> item_rcv3;

    //database
    Cursor cursor = null;
    SQLiteDatabase db_orderFood = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.orderfood/databases/OrderFoodN02.sqlite", null);



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        //Ánh xạ
        viewPager = view.findViewById(R.id.home_imageSilder);
        circleIndicator = view.findViewById(R.id.home_circleIndicator);
        rcv1_Data = view.findViewById(R.id.home_rcv1);
        rcv2_Data = view.findViewById(R.id.home_rcv2);
        rcv3_Data = view.findViewById(R.id.home_rcv3);
        btn_search = view.findViewById(R.id.btn_search);
        nameFood = view.findViewById(R.id.home_search);


        //Banner
        homeBanner = new HomeBanner(getActivity(), getListImage());
        viewPager.setAdapter(homeBanner);
        circleIndicator.setViewPager(viewPager);



        //Recycleview 1
        ArrayList<HomeRecyclerview1> item_rcv1 = new ArrayList<>();
        item_rcv1.add(new HomeRecyclerview1(R.drawable.cat1,"Burger"));
        item_rcv1.add(new HomeRecyclerview1(R.drawable.cat2,"Pizza"));
        item_rcv1.add(new HomeRecyclerview1(R.drawable.cat3,"Cơm"));
        item_rcv1.add(new HomeRecyclerview1(R.drawable.cat4,"Gà chiên"));
        item_rcv1.add(new HomeRecyclerview1(R.drawable.cat5,"Đồ uống"));
        item_rcv1.add(new HomeRecyclerview1(R.drawable.cat6,"Combo"));

        //Load
        rcv1_Data = view.findViewById(R.id.home_rcv1);
        rcv1_Adapter = new HomeRCV1Adapter(item_rcv1, this, getActivity());
        rcv1_Data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rcv1_Data.setAdapter(rcv1_Adapter);


        //Recycleview 2
        ArrayList<ObjectFood> item_rcv2 = new ArrayList<>();
        String sql = "SELECT * FROM tb_food LIMIT 5";
        cursor = db_orderFood.rawQuery(sql, null);
        item_rcv2.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String db_nameFood = cursor.getString(1);
            String db_price = cursor.getString(2);
            byte[] db_imageFood = cursor.getBlob(4);
            item_rcv2.add(new ObjectFood(db_imageFood, db_nameFood, db_price));
        }

        //Load
        rcv2_Data = view.findViewById(R.id.home_rcv2);
        rcv2_Adapter = new HomeRCV2Adapter(item_rcv2, getActivity());
        rcv2_Data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rcv2_Data.setAdapter(rcv2_Adapter);



        //Recycleview 3
        item_rcv3 = new ArrayList<>();
        //Load
        rcv3_Data = view.findViewById(R.id.home_rcv3);
        rcv3_Adapter = new HomeRCV3Adapter(item_rcv3, getActivity());
        rcv3_Data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rcv3_Data.setAdapter(rcv3_Adapter);


        //Search
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGoToSearch = new Intent(getActivity(), SearchActivity.class);
                intentGoToSearch.putExtra("nameFood", nameFood.getText().toString());
                startActivity(intentGoToSearch);
            }
        });

        return view;
    }

    //Đổ dữ liệu banner
    private List<ImageSlider> getListImage() {
        List<ImageSlider> list = new ArrayList<>();
        list.add(new ImageSlider(R.drawable.banner1));
        list.add(new ImageSlider(R.drawable.banner2));
        list.add(new ImageSlider(R.drawable.banner3));
        list.add(new ImageSlider(R.drawable.banner4));
        return list;
    }

    @Override
    public void ChangItem(int index, ArrayList<ObjectFood> item) {
        rcv3_Adapter = new HomeRCV3Adapter(item, getActivity());
        rcv3_Adapter.notifyDataSetChanged();
        rcv3_Data.setAdapter(rcv3_Adapter);
    }

    //Clear context

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(rcv3_Adapter != null){
            rcv3_Adapter.clearContext();
        }

        if (rcv2_Adapter != null){
            rcv2_Adapter.clearContext();
        }
    }
}