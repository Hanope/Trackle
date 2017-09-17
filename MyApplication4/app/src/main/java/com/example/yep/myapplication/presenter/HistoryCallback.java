package com.example.yep.myapplication.presenter;

import com.example.yep.myapplication.model.MyPageResult;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-09-17.
 */

public interface HistoryCallback {
    void getHistory(ArrayList<MyPageResult.History> histories);
}
