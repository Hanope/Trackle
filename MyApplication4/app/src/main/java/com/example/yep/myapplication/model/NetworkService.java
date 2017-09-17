package com.example.yep.myapplication.model;

import com.example.yep.myapplication.model.article.ArticleResult;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by seowo on 2017-09-15.
 */

public interface NetworkService {
    @GET("/api/topnews")
    Call<ArticleResult> getArticle();

    @GET("/api/search?")
    Call<ArticleResult> getSearch(@Query("keyword") String keyword);

    @GET("/api/tracking?")
    Call<MyPageResult> getTracking(@Query("keyword") String keyword);
}
