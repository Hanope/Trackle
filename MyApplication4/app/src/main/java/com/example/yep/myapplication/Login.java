package com.example.yep.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.R.id.content;

public class Login extends AppCompatActivity {


    private EditText edit_id, edit_pw;
    private TextView tv_login;
    private  RelativeLayout back ;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_login);

        findid();

    }



    void findid() {
        back = (RelativeLayout)findViewById(R.id.back);
        edit_id = (EditText)findViewById(R.id.edit_input_id);
        edit_pw = (EditText)findViewById(R.id.edit_input_pw);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide_keyboard();
                edit_id.clearFocus();
                edit_pw.clearFocus();
            }
        });
        tv_login = (TextView)findViewById(R.id.btn_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Main.class);
                startActivity(intent);
                finish();
            }
        });


    }

    void hide_keyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_id.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(edit_pw.getWindowToken(), 0);
    }
}