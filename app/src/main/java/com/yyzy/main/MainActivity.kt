package com.yyzy.main

import android.os.Bundle
import androidx.navigation.NavType
import androidx.navigation.activity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.yyzy.common.Navigation
import com.yyzy.common.base.BaseActivity
import com.yyzy.data.model.SearchParameters
import com.yyzy.data.model.SearchParametersType
import com.yyzy.feature.business.BusinessActivity
import com.yyzy.feature.business.HomeFragment
import com.yyzy.feature.setting.SettingFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavigation()
    }

    private fun setNavigation(){
        //https://developer.android.com/guide/navigation/design/kotlin-dsl?hl=zh-cn#activity
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navController.graph = navController.createGraph(
            startDestination = Navigation.Routes.HOME
        ) {
            fragment<HomeFragment>(Navigation.Routes.HOME) {
                label = resources.getString(R.string.frag_home)
            }
            fragment<SettingFragment>("${Navigation.Routes.SETTING}/{${Navigation.Arguments.SETTING_ID}}") {
                label = resources.getString(R.string.frag_setting)
                argument(Navigation.Arguments.SETTING_ID) {
                    type = NavType.StringType
                    defaultValue = "null"
                    nullable = true
                }
            }
            activity(Navigation.Routes.ACTIVITY_BUS) {
                label = getString(R.string.activity_bus)
                activityClass = BusinessActivity::class
                deepLink {
                    uriPattern = "http://www.example.com/plants/"
                    action = "android.intent.action.MY_ACTION"
                    mimeType = "image/*"
                }
                argument(Navigation.Arguments.BUS_NAME) {
                    type = SearchParametersType
                    defaultValue = SearchParameters("main-cactus", emptyList())
                }
            }
        }
    }
}
