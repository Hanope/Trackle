package com.example.yep.myapplication.presenter;

import com.example.yep.myapplication.model.article.ArticleResult;

/**
 * Created by seowo on 2017-09-17.
 */

public interface ArticleCallback {
    void getArticle(ArticleResult.Article article);
}
