package com.example.yep.myapplication.presenter;

import android.util.Log;

import com.example.yep.myapplication.application.Trackle;
import com.example.yep.myapplication.model.MyPageResult;
import com.example.yep.myapplication.model.NetworkService;
import com.example.yep.myapplication.model.article.ArticleResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-09-17.
 */

public class ArticlesPresenter {
    NetworkService service;
    String token;

    public ArticlesPresenter() {
        service = Trackle.getInstance().getNetworkService();
        token = Trackle.getInstance().getPreferences();
    }

    public void getArticles(final MainCallback callback){
        Call<ArticleResult> articleResultCall = service.getArticle();
        articleResultCall.enqueue(new Callback<ArticleResult>() {
            @Override
            public void onResponse(Call<ArticleResult> call, Response<ArticleResult> response) {
                if(response.isSuccessful()){
                    callback.getArticles(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArticleResult> call, Throwable t) {

            }
        });
    }
    public void getSearch(final MainCallback callback, String keyword){
        Call<ArticleResult> articleResultCall = service.getSearch(keyword);
        articleResultCall.enqueue(new Callback<ArticleResult>() {
            @Override
            public void onResponse(Call<ArticleResult> call, Response<ArticleResult> response) {
                if(response.isSuccessful()){
                    callback.getSearch(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArticleResult> call, Throwable t) {

            }
        });
    }

    public void getHistories(final HistoryCallback callback){
        Call<MyPageResult> myPageResultCall = service.getTracking("부산 여중생");
        myPageResultCall.enqueue(new Callback<MyPageResult>() {
            @Override
            public void onResponse(Call<MyPageResult> call, Response<MyPageResult> response) {
                if(response.isSuccessful()){
                    callback.getHistory(response.body().histories);
                }
                else{
                    int statusCode = response.code();
                    Log.i("code : ", statusCode + "");
                }
            }

            @Override
            public void onFailure(Call<MyPageResult> call, Throwable t) {

            }
        });
    }
}
