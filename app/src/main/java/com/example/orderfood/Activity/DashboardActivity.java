package com.example.orderfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.orderfood.R;

public class DashboardActivity extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation; // import thư viện


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //ánh xạ
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //Add các Fragment
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_dashboard));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_cart));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_info));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_person));

        //Sự kiện khi bấm thanh navigation
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        // giao diện trang chủ
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        // giao diện thông báo
                        fragment = new NotificationFragment();
                        break;
                    case 3:
                        // giao diện giỏ hàng
                        fragment = new CartFragment();
                        break;
                    case 4:
                        fragment = new DeliveryFragment();
                        break;
                    case 5:
                        // giao diện người dùng
                        fragment = new PersonFragment();
                        break;
                }
                //Load fragment
                loadFragment(fragment);
            }
        });

        bottomNavigation.show(1, true); // hiển thị giao diện này trước
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // Chuyển đổi các frament
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

}