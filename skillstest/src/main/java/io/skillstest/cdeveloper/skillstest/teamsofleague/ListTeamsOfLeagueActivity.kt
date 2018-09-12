package io.skillstest.cdeveloper.skillstest.teamsofleague

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.skillstest.App
import io.skillstest.cdeveloper.skillstest.R
import io.skillstest.cdeveloper.skillstest.teamsofleague.details.TeamOfLeagueDetailsActivity
import io.skillstest.cdeveloper.skillstest.teamsofleague.details.TeamOfLeagueDetailsFragment
import io.skillstest.cdeveloper.skillstest.teamsofleague.teams.TeamsLeagueFragment
import io.skillstest.cdeveloper.skillstest.util.PARAM_LEAGUES_NAME
import io.skillstest.cdeveloper.skillstest.util.PARAM_TEAM_OF_LEAGUE
import kotlinx.android.synthetic.main.activity_list_teams_of_league.*
import javax.inject.Inject

class ListTeamsOfLeagueActivity : AppCompatActivity(), IListTeamsOfLeagueContract.View, TeamsLeagueFragment.Companion.OnTeamItemCLickListener {

    @Inject
    lateinit var presenter: IListTeamsOfLeagueContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_teams_of_league)
        setSupportActionBar(toolbarTeams)

        (application as? App)?.component()?.inject(this)
        presenter.view(this)

        val leaguesName = intent.extras?.getString(PARAM_LEAGUES_NAME, "") ?: ""

        if (savedInstanceState == null) {
            val myFragment = TeamsLeagueFragment.newInstance(leaguesName)
            supportFragmentManager.beginTransaction().replace(R.id.teamsLeaguePane, myFragment).commit()
        }
    }

    override fun isModeDualPane(): Boolean {
        val detailsPane = findViewById<FrameLayout>(R.id.teamsLeagueDetailsPane)
        return detailsPane != null && detailsPane.visibility == View.VISIBLE
    }

    override fun showTeamOfLeaguesDetails(teamOfLeague: TeamOfLeague, isDualPane: Boolean) {
        if (isDualPane) {
            val teamDetailFragment = TeamOfLeagueDetailsFragment.newInstance(teamOfLeague)
             supportFragmentManager.beginTransaction()
                    .apply {
                        replace(R.id.teamsLeagueDetailsPane, teamDetailFragment)
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        commitNow()
                    }
        } else {
            val intent = Intent(applicationContext, TeamOfLeagueDetailsActivity::class.java)
            intent.putExtra(PARAM_TEAM_OF_LEAGUE, teamOfLeague)
            startActivity(intent)
        }
    }

    override fun onTeamItemClick(teamOfLeague: TeamOfLeague) {
        presenter.teamItemClicked(teamOfLeague)
    }
}
