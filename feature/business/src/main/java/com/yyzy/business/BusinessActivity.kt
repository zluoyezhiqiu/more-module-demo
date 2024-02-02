package com.yyzy.business

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yyzy.main.feature.business.databinding.FeatureActivityBusinessBinding

class BusinessActivity : AppCompatActivity() {

    private val activityBinding by lazy {
        FeatureActivityBusinessBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityBinding.root)
    }
}