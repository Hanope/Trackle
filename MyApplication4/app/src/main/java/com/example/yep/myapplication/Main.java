package com.example.yep.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yep.myapplication.model.article.ArticleResult;
import com.example.yep.myapplication.presenter.ArticlesPresenter;
import com.example.yep.myapplication.presenter.MainCallback;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class Main extends AppCompatActivity {

    EditText search_edit;
    ImageButton search_btn;

    private Main_Adapter main_adapter;
    public RecyclerView articles_recycler;
    private LinearLayoutManager layout_manager;

    private  ImageView my_page;
    private static final String TAG_TAG = "tag";
    private static final String TAG_TITLE = "title";
    private static final String TAG_IMAGE = "image";

    ArticlesPresenter presenter;

    public Main() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_main);

        presenter = new ArticlesPresenter();
        articles_recycler = (RecyclerView)findViewById(R.id.recycler);
        layout_manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        search_edit = (EditText)findViewById(R.id.search);
        search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                    if(search_edit.getText().length() == 0){
                        Toast.makeText(getApplicationContext(), "검색어를 입력해주세요", Toast.LENGTH_SHORT);
                    }
                    else{
                        search_btn.performClick();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(search_edit.getWindowToken(), 0);
                    }
                }
                return false;
            }
        });
        search_btn = (ImageButton)findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "검색 중 입니다", Toast.LENGTH_LONG).show();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search_edit.getWindowToken(), 0);

                presenter.getSearch(new MainCallback() {
                    @Override
                    public void getArticles(ArticleResult articles) {
                        //null
                    }

                    @Override
                    public void getSearch(ArticleResult articles) {
                        main_adapter.setData(null);
                        main_adapter.setData(articles.articles);

                        main_adapter.notifyDataSetChanged();
                    }
                }, search_edit.getText().toString());
            }
        });

        presenter.getArticles(new MainCallback() {
            @Override
            public void getArticles(ArticleResult articles) {
                main_adapter = new Main_Adapter(getApplicationContext(), articles.articles);
                articles_recycler.setLayoutManager(layout_manager);
                articles_recycler.setAdapter(main_adapter);
            }

            @Override
            public void getSearch(ArticleResult articles) {
                //null
            }
        });
        my_page = (ImageView)findViewById(R.id.my_page);
        my_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("news");
                FirebaseInstanceId.getInstance().getToken();

                Intent intent = new Intent(Main.this, My_Page.class);
                Log.e("notify",""+FirebaseInstanceId.getInstance().getToken());
                startActivity(intent);
            }
        });

    }
}
