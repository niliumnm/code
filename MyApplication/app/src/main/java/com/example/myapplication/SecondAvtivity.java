package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SecondAvtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("activeState", "SecondActivityCreated");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("activeState", "SecondActivityStarted");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("activeState", "SecondActivityRestarted");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("activeState", "SecondActivityPaused");

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("activeState", "SecondActivityResumed");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("activeState", "SecondActivityStopped");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("activeState", "SecondActivityDestroyed");

    }
    public void closeActivity(View view){
        Log.e("activeState", "SecondActivityFinish");

        this.finish();
    }

}
