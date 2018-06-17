package com.jumi.shareproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jumi.shareproject.compoment.ShTitleBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShTitleBar titleBar = new ShTitleBar(this);
        setContentView(titleBar);
    }
}
