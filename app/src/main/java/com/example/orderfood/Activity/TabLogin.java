package com.example.orderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.orderfood.Admin.AdminActivity;
import com.example.orderfood.Database.DBhelper;
import com.example.orderfood.R;

public class TabLogin extends Fragment {
    EditText login_phoneNumber, login_passWord;
    TextView login_forGot;
    Button btn_login;
    DBhelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_login, container, false);

        login_phoneNumber = root.findViewById(R.id.login_phoneNumber);
        login_passWord = root.findViewById(R.id.login_passWord);
        login_forGot = root.findViewById(R.id.login_forGot);
        btn_login = root.findViewById(R.id.btn_login);
        db = new DBhelper(getActivity());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = login_phoneNumber.getText().toString();
                String passWord = login_passWord.getText().toString();

                if(phoneNumber.equals(" ") || passWord.equals(" ") ){
                    Toast.makeText(getActivity(), "Vui lòng kiểm tra lại thông tin tài khoản!",Toast.LENGTH_SHORT).show();
                }else{
                    boolean check = db.checkUserNamePassword(phoneNumber,passWord);
                    if(check == true ){
                        if(phoneNumber.equals("0123456789"))
                        {
                            Toast.makeText(getActivity(), "Đăng nhập với tư cách là admin", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(getActivity(), AdminActivity.class);
                            startActivity(intent1);
                        }
                        else {
                            Intent intent2 = new Intent(getActivity(), DashboardActivity.class);
                            startActivity(intent2);
                        }
                    }else {
                        Toast.makeText(getActivity(), "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



        return root;
    }

}
