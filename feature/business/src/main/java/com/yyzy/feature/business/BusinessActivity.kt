package com.yyzy.feature.business

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yyzy.common.Constants
import com.yyzy.feature.business.databinding.FeatureActivityBusinessBinding

@Route(path = Constants.Activity.BUSINESS_ACTIVITY)
class BusinessActivity : AppCompatActivity() {

    private val activityBinding by lazy {
        FeatureActivityBusinessBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(activityBinding.root)
    }
}