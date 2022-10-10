package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("activeState", "MainActivityCreated");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("activeState", "MainActivityStarted");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("activeState", "MainActivityRestarted");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("activeState", "MainActivityPaused");

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("activeState", "MainActivityResumed");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("activeState", "MainActivityStopped");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("activeState", "MainActivityDestroyed");

    }
    //响应按钮
//    public void sendMessage(View view) {
//        System.out.println("点击了");
//    }
}