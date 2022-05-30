package com.example.orderfood.Activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orderfood.Adapter.Notification1_Adapter;
import com.example.orderfood.Adapter.Notification2_Adapter;
import com.example.orderfood.Model.Notification_rcv;
import com.example.orderfood.R;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {

    private RecyclerView notifi_rcv1;
    private RecyclerView notifi_rcv2;

    private Notification1_Adapter rcv1_Adapter;
    private Notification2_Adapter rcv2_Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_notification, container, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL); // hiển thị Layout

        //RecyclerView1
        ArrayList<Notification_rcv> item_rcv1 = new ArrayList<>();
        item_rcv1.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro",
                "Thông báo từ cửa hàng Archiiro, thông báo từ cửa hàng Archiiro, thông báo từ cửa hàng Archiiro"));
        item_rcv1.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro", "Thông báo từ cửa hàng Archiiro"));
        item_rcv1.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro", "Thông báo từ cửa hàng Archiiro"));
        item_rcv1.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro", "Thông báo từ cửa hàng Archiiro"));

        //Load

        notifi_rcv1 = view.findViewById(R.id.notification_rcv1); // id recycleview1
        rcv1_Adapter = new Notification1_Adapter(item_rcv1);
        notifi_rcv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        notifi_rcv1.setAdapter(rcv1_Adapter);
        notifi_rcv1.addItemDecoration(itemDecoration); // thêm dòng kẻ phân biệt các item


        // RecyclerView2
        ArrayList<Notification_rcv> item_rcv2 = new ArrayList<>();
        item_rcv2.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro",
                "Thông báo từ cửa hàng Archiiro, thông báo từ cửa hàng Archiiro, thông báo từ cửa hàng Archiiro"));
        item_rcv2.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro", "Thông báo từ cửa hàng Archiiro"));
        item_rcv2.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro", "Thông báo từ cửa hàng Archiiro"));
        item_rcv2.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro", "Thông báo từ cửa hàng Archiiro"));
        item_rcv2.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro", "Thông báo từ cửa hàng Archiiro"));
        item_rcv2.add(new Notification_rcv(R.drawable.notification_store1,"Thông báo từ cửa hàng Archiiro", "Thông báo từ cửa hàng Archiiro"));

        //Load
        notifi_rcv2 = view.findViewById(R.id.notification_rcv2); // id recycleview1
        rcv2_Adapter = new Notification2_Adapter(item_rcv2);
        notifi_rcv2.setLayoutManager(new LinearLayoutManager(getActivity()));
        notifi_rcv2.setAdapter(rcv2_Adapter);
        notifi_rcv2.addItemDecoration(itemDecoration); // thêm dòng kẻ phân biệt các item




        return view;
    }
}