package com.example.orderfood.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.orderfood.Activity.TabLogin;
import com.example.orderfood.Activity.TabSignUp;

public class LoginAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalsTabs;

    public LoginAdapter(FragmentManager fm, Context context, int totalsTabs){
        super(fm);
        this.context = context;
        this.totalsTabs = totalsTabs;
    }

    @Override
    public int getCount() {
        return totalsTabs;
        // trả ra số tab co
    }

    public Fragment getItem (int position){
        switch (position){
            case 0:
                TabLogin tabLogin = new TabLogin();
                return tabLogin;
            case 1:
                TabSignUp tabSignUp = new TabSignUp();
                return tabSignUp;
            default:
                return null;
        }
    }
}
