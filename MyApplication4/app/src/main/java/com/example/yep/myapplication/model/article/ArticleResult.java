package com.example.yep.myapplication.model.article;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by seowo on 2017-09-16.
 */

public class ArticleResult {
    public ArrayList<Article> articles;

    public class Article implements Serializable {
        public String title;

        public String name; //기사 내용
        public String image;

        public String company;

        public String keyword;
        public String date;

        public String author;


        public String getTitle() {
            return title;
        }

        public String getName() {
            return name;
        }

        public String getDate() { return date; }

        public String getImage() {
            return image;
        }

        public String getKeyword() {
            return keyword;
        }
    }
}