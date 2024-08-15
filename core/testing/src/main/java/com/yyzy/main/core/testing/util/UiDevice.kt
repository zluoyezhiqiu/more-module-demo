package com.yyzy.main.core.testing.util

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat

const val PACKAGE_NAME_TESTING = "com.yyzy.main"
const val LAUNCH_TIMEOUT = 5000L

fun UiDevice.launcherTestApp() {
    val launcherPackage: String = launcherPackageName
    assertThat(launcherPackage, notNullValue())
    wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT)
    val context = ApplicationProvider.getApplicationContext<Context>()
    context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME_TESTING)?.apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(this)
    }
    wait(Until.hasObject(By.pkg(PACKAGE_NAME_TESTING).depth(0)), LAUNCH_TIMEOUT)
}

fun getAppUiSelector(idName: String, packageName: String = PACKAGE_NAME_TESTING): UiSelector =
    UiSelector().resourceId("$packageName:id/$idName")

fun UiDevice.getUiObjectById(idName: String): UiObject =
    findObject(getAppUiSelector(idName))

fun UiDevice.getUiObject2ById(idName: String): UiObject2? =
    findObject(By.res(PACKAGE_NAME_TESTING, idName))

fun UiDevice.getUiObject2(idName: String, text: String, isDesc: Boolean = false): UiObject2? =
    findObject(getBySelector(idName, text, isDesc))

fun UiDevice.getBySelector(idName: String): BySelector =
    By.res(PACKAGE_NAME_TESTING, idName)

fun UiDevice.getBySelector(idName: String, text: String, isDesc: Boolean = false): BySelector =
    By.res(PACKAGE_NAME_TESTING, idName).run {
        if (isDesc) descContains(text) else textContains(text)
    }

fun UiDevice.clickViewByText(text: String) {
    findObject(By.text(text))?.click()
}

fun UiDevice.clickViewById(viewId: String) {
    findObject(By.res(PACKAGE_NAME_TESTING, viewId))?.click()
}

fun UiDevice.clickViewByIdWithDesc(
    viewId: String,
    contentDescription: String
) {
    findObject(
        By.res(PACKAGE_NAME_TESTING, viewId)
            .descContains(contentDescription)
    )?.click()
}

fun UiDevice.waitViewShow(idName: String, time: Long = LAUNCH_TIMEOUT): Boolean =
    wait(Until.hasObject(By.res(PACKAGE_NAME_TESTING, idName)), time)

fun UiDevice.waitViewShowWithDesc(desc: String, time: Long = LAUNCH_TIMEOUT): Boolean =
    wait(Until.hasObject(By.desc(desc)), time)

fun finishAllActivities() {
    InstrumentationRegistry.getInstrumentation().runOnMainSync {
        val activities =
            ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
        for (activity in activities) {
            activity.finish()
        }
    }
}