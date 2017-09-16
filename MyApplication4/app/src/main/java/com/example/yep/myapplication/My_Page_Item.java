package com.example.yep.myapplication;

import android.content.Context;

/**
 * Created by yep on 2017. 9. 16..
 */

public class My_Page_Item {



    String date;
    String title;
    Context context;

    public My_Page_Item(Context context,  String date , String title) {
        this.context = context;
        this.date = date;
        this.title =title;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}

