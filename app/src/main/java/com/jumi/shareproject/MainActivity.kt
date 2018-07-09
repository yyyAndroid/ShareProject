package com.jumi.shareproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dwwd.dwlibrary.compoment.ShTitleBar;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val titleBar = ShTitleBar(this)
        setContentView(titleBar)
    }
}
