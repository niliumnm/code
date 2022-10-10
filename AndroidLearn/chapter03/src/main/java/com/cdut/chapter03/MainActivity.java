package com.cdut.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdut.chapter03.Utils.DataUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonTest;
    private Button buttonEnable;
    private Button buttonDisable;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonDisable = findViewById(R.id.disable);
        buttonEnable = findViewById(R.id.enable);
        buttonTest = findViewById(R.id.test);
        textView = findViewById(R.id.textview);
        buttonTest.setOnClickListener(this);
        buttonEnable.setOnClickListener(this);
        buttonDisable.setOnClickListener(this);

        ImageView imageView = findViewById(R.id.imgview);
        imageView.setImageResource(R.drawable.apple);
    }

    @Override
    public void onClick(View v) {
        switch (((Button) v).getId()) {
            case R.id.enable:
                buttonTest.setEnabled(true);
                break;
            case R.id.disable:
                buttonTest.setEnabled(false);
                break;
            case R.id.test:
                String desc = String.format("%s 您点击了按钮 %s"  , DataUtil.getTime(), ((Button) v).getText());
                this.textView.setText(desc);
        }
    }
}