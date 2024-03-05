package com.yyzy.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.yyzy.common.Constants
import com.yyzy.common.util.startNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.test).setOnClickListener {
            Constants.Activity.BUSINESS_ACTIVITY.startNavigation()
        }
        mainViewModel.print()
    }
}