package com.example.orderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.orderfood.Database.DBhelper;
import com.example.orderfood.R;

public class TabSignUp extends Fragment {
    EditText signup_phoneNumber, signup_passWord, signup_conFirmPass;
    Button btn_signIn;
    DBhelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_signup, container, false);

        signup_phoneNumber = root.findViewById(R.id.signUp_phoneNumber);
        signup_passWord = root.findViewById(R.id.signUp_passWord);
        signup_conFirmPass = root.findViewById(R.id.signUp_conFirmPassWord);
        btn_signIn = root.findViewById(R.id.btn_signUp);
        db = new DBhelper(getActivity());

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String signup_phoneNumber1 = signup_phoneNumber.getText().toString();
                String signup_passWord1 = signup_passWord.getText().toString();
                String signup_confirmPass1 = signup_conFirmPass.getText().toString();

                if(signup_phoneNumber1.equals("")||signup_passWord1.equals("")||signup_confirmPass1.equals("")){
                    Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông tin tài khoản !",Toast.LENGTH_SHORT).show();

                }else {
                    if (signup_passWord1.equals(signup_confirmPass1)) {
                        boolean checkUser = db.checkUsername(signup_phoneNumber1);
                        if(checkUser==false) {
                            Boolean insert = db.insertData(signup_phoneNumber1,signup_passWord1);
                            if(insert == true){
                                Toast.makeText(getActivity(), "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getActivity(), "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Mật khẩu không trùng nhau!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return root;
    }


}
