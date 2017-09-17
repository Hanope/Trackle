package com.example.yep.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.yep.myapplication.application.Trackle;
import com.example.yep.myapplication.model.MyPageResult;
import com.example.yep.myapplication.model.NetworkService;
import com.example.yep.myapplication.model.SearchData;
import com.example.yep.myapplication.presenter.ArticlesPresenter;
import com.example.yep.myapplication.presenter.HistoryCallback;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class My_Page extends AppCompatActivity {

    RecyclerView mypage_recycler;
    LinearLayoutManager linear_layout;
    My_Page_Adapter my_page_adapter;

    ArticlesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_my_page);

        presenter = new ArticlesPresenter();
        presenter.getHistories(new HistoryCallback() {
            @Override
            public void getHistory(ArrayList<MyPageResult.History> histories) {
                mypage_recycler = (RecyclerView)findViewById(R.id.mypage_recycler);
                linear_layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                my_page_adapter = new My_Page_Adapter(histories, getApplicationContext());

                mypage_recycler.setAdapter(my_page_adapter);
                mypage_recycler.setLayoutManager(linear_layout);
            }
        });
    }

}
