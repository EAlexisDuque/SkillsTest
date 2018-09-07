package io.skillstest.cdeveloper.skillstest.support

import android.support.test.espresso.AmbiguousViewMatcherException
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import io.skillstest.cdeveloper.skillstest.teamsofleague.ListTeamsOfLeagueActivity
import org.junit.Assert


fun isElementDisplayedInRecyclerView(recyclerId: Int, elementText: String) {
    onView(withId(recyclerId)).perform(RecyclerViewActions
            .actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withSubstring(elementText)),
                    scrollTo()))
    onView(withSubstring(elementText)).check(matches(isDisplayed()))
}

fun clickWithText(text: String) {
    onView(withSubstring(text)).perform(click())
}

fun waitSeconds(seconds: Float) {
    Thread.sleep(seconds.toLong() * 1000)
}

fun isTextDisplayed(text: String): Boolean {
    var isDisplayed = true

    onView(withSubstring(text))
            .withFailureHandler { error, _ ->
                isDisplayed = error is AmbiguousViewMatcherException
            }
            .check(matches(isDisplayed()))

    Assert.assertTrue(isDisplayed)

    return isDisplayed
}

fun backPressed() {
    onView(isRoot()).perform(pressBack())
}