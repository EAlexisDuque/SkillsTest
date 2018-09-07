package io.skillstest.cdeveloper.skillstest

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.skillstest.cdeveloper.skillstest.leagues.ListLeaguesActivity
import io.skillstest.cdeveloper.skillstest.support.*
import io.skillstest.cdeveloper.skillstest.teamsofleague.ListTeamsOfLeagueActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(ListLeaguesActivity::class.java)

    val leaguesArray = arrayOf("Spanish La Liga", "Formula 1")
    val teamsArray = arrayOf("Barcelona", "Toro Rosso")
    val textArray = arrayOf("Founded in 1899", "back Berger's")
    var repeat = 0

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("io.skillstest.cdeveloper.skillstest", appContext.packageName)
    }

    @Test
    fun testOfNavigationView() {
        seeListOfLeaguesPortrait(leaguesArray[repeat], teamsArray[repeat], textArray[repeat])
    }

    private fun seeListOfLeaguesPortrait(leagueName: String, teamName: String, text: String) {
        waitSeconds(1f)
        isElementDisplayedInRecyclerView(R.id.recyclerLeagues, leagueName)
        waitSeconds(1f)
        clickWithText(leagueName)
        waitSeconds(1f)
        clickWithText(teamName)
        isTextDisplayed(text)
        waitSeconds(1f)
        backPressed()
        waitSeconds(.3f)
        backPressed()
        repeat++
        if (repeat < leaguesArray.size)
            seeListOfLeaguesPortrait(leaguesArray[repeat], teamsArray[repeat], textArray[repeat])
    }
}
