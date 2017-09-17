package com.example.yep.myapplication.presenter;

import com.example.yep.myapplication.model.article.ArticleResult;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-09-17.
 */

public interface MainCallback {
    void getArticles(ArticleResult articles);
    void getSearch(ArticleResult articles);
}
