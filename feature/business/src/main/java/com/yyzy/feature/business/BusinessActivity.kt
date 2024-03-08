package com.yyzy.feature.business

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.yyzy.common.util.task.repeatOnViewLifecycleOnCreated
import com.yyzy.feature.business.databinding.BusActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessActivity : AppCompatActivity() {

    private val mainViewModel: BusinessViewModel by viewModels()

    private val activityBinding by lazy {
        BusActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityBinding.root)
        lifecycle.addObserver(mainViewModel)
        repeatOnViewLifecycleOnCreated(mainViewModel.messageFlow) { content ->
            activityBinding.test.text = content
        }
    }
}