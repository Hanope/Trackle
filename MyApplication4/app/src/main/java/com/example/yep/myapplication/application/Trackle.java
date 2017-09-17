package com.example.yep.myapplication.application;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import android.util.Log;

import com.example.yep.myapplication.model.NetworkService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by seowo on 2017-09-15.
 */

public class Trackle extends Application {

    private static Trackle trackle;

    public static String baseUrl = "http://172.16.0.55:2222";

    private NetworkService networkService;

    public static Trackle getInstance(){
        return trackle; }

    public NetworkService getNetworkService(){ return networkService; }

    @Override
    public void onCreate() {
        super.onCreate();

        Trackle.trackle = this;
        buildService();
    }

    public void buildService(){
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);
    }

    // 값 불러오기
    public String getPreferences(){
        SharedPreferences pref = getSharedPreferences("trackle_token", MODE_PRIVATE);
        return pref.getString("Authorization", "");
    }

    // 값 저장하기
    public void savePreferences(String token){
        SharedPreferences pref = getSharedPreferences("trackle_token", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Authorization", token);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    public void removePreferences(){
        SharedPreferences pref = getSharedPreferences("indivus_token", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("Authorization");
        editor.commit();
    }
}
