package com.yyzy.main.core.testing.util

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

const val WAIT_TIME_OUT = 3_000L

fun clickTestView(viewId: Int) {
    Espresso.onView(withId(viewId))?.perform(click())
}

fun clickTestViewByText(viewId: Int, text: String) {
    Espresso.onView(
        allOf(withId(viewId), withText(text))
    )?.perform(click())
}

fun clickTestViewByDescription(viewId: Int, description: String) {
    Espresso.onView(
        allOf(withId(viewId), withContentDescription(description))
    )?.perform(click())
}

fun clickTestViewAndCheck(
    viewId: Int,
    viewMatcher: Matcher<in View> = isDisplayed()
) {
    Espresso.onView(withId(viewId))
        .perform(click())
        .check(matches(viewMatcher))
}