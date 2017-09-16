package com.example.yep.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.internal.io.RealConnection;

public class Main extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private ArrayList<Main_Item> review_items;
    private Main_Adapter adapter;
    public RecyclerView recyclerview;
    private SwipeRefreshLayout swipe;

    private String json;
    String myJSON = null;
    JSONArray main_list = null;
    private String no = "0" ;

    private  ImageView btn_my_page;
    private ImageView btn_search;
    private EditText et_search;
    private RelativeLayout back;

    private static final String TAG_TAG = "tag";
    private static final String TAG_TITLE = "title";
    private static final String TAG_IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_main);
        btn_my_page = (ImageView)findViewById(R.id.btn_my_page);
        btn_search = (ImageView)findViewById(R.id.btn_search) ;
        et_search = (EditText)findViewById(R.id.search);
        back =(RelativeLayout)findViewById(R.id.back);


        //back

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide_keyboard();
            }
        });
        //my_page
        btn_my_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, My_Page.class);
                Log.e("noti",""+FirebaseInstanceId.getInstance().getToken());
                startActivity(intent);
            }
        });


        //adapter refresh
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide_keyboard();
                if(!et_search.getText().equals("")&& et_search.getText()!=null){

                }
            }
        });
//        recyclerview = (RecyclerView) findViewById(R.id.recycler);
//        recyclerview.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        recyclerview.setNestedScrollingEnabled(false);
//        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerview.setLayoutManager(layout);
//        review_items = new ArrayList<>();
//
//        adapter = new Main_Adapter(review_items, Main.this);
//        recyclerview.setAdapter(adapter);

    }


    void hide_keyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
    }
}
