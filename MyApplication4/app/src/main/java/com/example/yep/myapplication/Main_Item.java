package com.example.yep.myapplication;

import android.content.Context;

/**
 * Created by yep on 2017. 9. 16..
 */

public class Main_Item {


    String image;
    String tag;
    String title;
    Context context;

    public Main_Item(Context context,  String image , String tag, String title) {
        this.context = context;
        this.image =image;
        this.tag = tag;
        this.title =title;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

