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
import android.widget.TextView;

import static android.R.id.content;

public class Login extends AppCompatActivity {


    private EditText edit_id, edit_pw;
    private TextView tv_login;

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
        edit_id = (EditText)findViewById(R.id.edit_input_id);
        edit_pw = (EditText)findViewById(R.id.edit_input_pw);
        tv_login = (TextView)findViewById(R.id.btn_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, AAA.class);
                startActivity(intent);
            }
        });


    }

    void hide_keyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_id.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(edit_pw.getWindowToken(), 0);
    }
}