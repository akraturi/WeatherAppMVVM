package com.akraturi.howisweather.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.akraturi.howisweather.R
import utils.AppLogger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppLogger.logCurrentMethodName("MainActivity")
    }
}
