package com.example.yep.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Splash extends AppCompatActivity {
    private ImageView main;
    private NetworkInfo mobile;
    private NetworkInfo wifi;
    private ConnectivityManager manager;

    private Intent getintent;
    private Intent internet;
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Splash.this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        getintent = getIntent();
//        internet = getIntent();
//        if (wifi.isConnected() || mobile.isConnected()) {

            setContentView(R.layout.l_splash);
            main = (ImageView) findViewById(R.id.loding_logo);
//            Glide.with(Splash.this).load("http://175.126.38.139/img/itchap/splash_img.png").asBitmap().into(main);
            Handler hd = new Handler();
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash.this, Login.class);
                    if(getintent != null){
                        intent.putExtra("logout",getintent.getBooleanExtra("logout", false));
                    }
                    startActivity(intent);
                    finish();
                }
            }, 3000); // 3초 후에 hd Handler 실행
//        }else{
//            moveTaskToBack(true);
//            finish();
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }

    }
}





