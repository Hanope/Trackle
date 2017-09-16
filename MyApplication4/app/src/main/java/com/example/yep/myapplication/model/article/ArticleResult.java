package com.example.yep.myapplication.model.article;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-09-16.
 */

public class ArticleResult {
    public ArrayList<Article> articles;

    public class Article{
        public int id;

        public String title;

        public String content;
        public String image;

        public String company;

        public String content_img;
        public String content_text;

        public String reporter;
        public String date;

        public String keyword;
    }
}