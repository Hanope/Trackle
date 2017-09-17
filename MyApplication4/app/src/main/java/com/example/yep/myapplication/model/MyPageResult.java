package com.example.yep.myapplication.model;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-09-16.
 */

public class MyPageResult {
    public ArrayList<History> histories;

    public class History{
        String date;
        String link;
        String title;

        public String getDate() {
            return date;
        }

        public String getLink() {
            return link;
        }

        public String getTitle() {
            return title;
        }
    }
}
