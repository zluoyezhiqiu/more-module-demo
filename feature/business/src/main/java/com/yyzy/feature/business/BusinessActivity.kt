package com.yyzy.feature.business

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.yyzy.common.Navigation
import com.yyzy.common.util.task.repeatOnViewLifecycleOnCreated
import com.yyzy.data.model.SearchParametersType
import com.yyzy.feature.business.databinding.BusActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessActivity : AppCompatActivity() {

    private val mainViewModel: BusinessViewModel by viewModels()

    private val activityBinding by lazy {
        BusActivityBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityBinding.root)

        val params = intent.getStringExtra(Navigation.Arguments.BUS_NAME)
        lifecycle.addObserver(mainViewModel)
        repeatOnViewLifecycleOnCreated(mainViewModel.messageFlow) { content ->
            activityBinding.test.text = content +  params?.let {  SearchParametersType.parseValue(it) }?.toString()
        }
    }
}