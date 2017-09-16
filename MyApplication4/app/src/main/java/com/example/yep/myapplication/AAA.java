package com.example.yep.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

//import gas.trackle.model.article.ArticleResult;

public class AAA extends AppCompatActivity {

    RecyclerView article_recycler;
//    ArticleAdapter article_adapter;

    LinearLayoutManager layoutManager;

//    ArticleResult article_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aa);

//        article_recycler = (RecyclerView)findViewById(R.id.articles_recycler);
//        article_recycler.setHasFixedSize(false);

//        layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        article_recycler.setLayoutManager(layoutManager);

        //서버 통신으로 기사 받아오기
        //
        //
        //

//        article_adapter = new ArticleAdapter(this, article_result);
//        article_recycler.setAdapter(article_adapter);
    }
}
