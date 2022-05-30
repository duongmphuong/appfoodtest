package com.example.orderfood.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.orderfood.Model.Person;
import com.example.orderfood.R;

import java.util.ArrayList;


public class PersonFragment extends Fragment {
    private ConstraintLayout btn_edit_profile;
    private ConstraintLayout btn_edit_password;
    private ConstraintLayout btn_edit_notification;
    private ConstraintLayout btn_edit_map;
    private ConstraintLayout btn_history;
    private ConstraintLayout btn_logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_person, container, false);


        btn_edit_profile = view.findViewById(R.id.edit_profile);
        btn_edit_password = view.findViewById(R.id.edit_password);
        btn_edit_notification = view.findViewById(R.id.edit_notification);
        btn_edit_map = view.findViewById(R.id.edit_map);
        btn_history = view.findViewById(R.id.edit_history);
        btn_logout = view.findViewById(R.id.layout_logout);


        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGoToHistory = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intentGoToHistory);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog_confirmLogout = new AlertDialog.Builder(getActivity());
                dialog_confirmLogout.setMessage("Bạn có muốn đăng xuất không ?");
                dialog_confirmLogout.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                //không đăng xuất
                dialog_confirmLogout.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialog_confirmLogout.show();

            }
        });
        return view;
    }

}