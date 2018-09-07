package io.skillstest.cdeveloper.skillstest.teamsofleague.details

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.skillstest.R
import io.skillstest.cdeveloper.skillstest.util.PARAM_TEAM_OF_LEAGUE

class TeamOfLeagueDetailsActivity : AppCompatActivity() {

    private var teamOfLeague: TeamOfLeague? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_of_league_details)

        teamOfLeague = intent.extras?.getParcelable(PARAM_TEAM_OF_LEAGUE)

        if (teamOfLeague == null) {
            finish()
            return
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }

        if (savedInstanceState == null) {
            val teamDetailsFragment = TeamOfLeagueDetailsFragment.newInstance(teamOfLeague!!)
            supportFragmentManager.beginTransaction().replace(R.id.teamsLeagueDetailsPane, teamDetailsFragment).commit()
        }
    }
}