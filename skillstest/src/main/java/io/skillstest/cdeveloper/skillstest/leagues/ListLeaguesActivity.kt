package io.skillstest.cdeveloper.skillstest.leagues

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import io.skillstest.cdeveloper.domain.model.League
import io.skillstest.cdeveloper.skillstest.teamsofleague.ListTeamsOfLeagueActivity
import io.skillstest.cdeveloper.skillstest.R
import io.skillstest.cdeveloper.skillstest.adapters.LeaguesRecyclerViewAdapter
import io.skillstest.cdeveloper.skillstest.App
import io.skillstest.cdeveloper.skillstest.util.PARAM_LEAGUES_NAME
import io.skillstest.cdeveloper.skillstest.util.hide
import kotlinx.android.synthetic.main.activity_list_leagues.*
import kotlinx.android.synthetic.main.content_list_leagues.*
import javax.inject.Inject

class ListLeaguesActivity : AppCompatActivity(), ILeagueContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: ILeagueContract.Presenter

    private lateinit var adapter: LeaguesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_leagues)
        setSupportActionBar(toolbar)

        (application as? App)?.component()?.inject(this)
        presenter.view(this)

        adapter = LeaguesRecyclerViewAdapter(leaguesIItemClickListener = presenter)
        recyclerLeagues.adapter = adapter
        recyclerLeagues.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        swipeRefreshLeague?.setOnRefreshListener(this)

        savedInstanceState?.let {
            adapter.clearLeagues()
            adapter.addAllLeagues(savedInstanceState.getParcelableArrayList(PARAM_LIST_LEAGUES)
                    ?: ArrayList())
        }
    }

    override fun onResume() {
        super.onResume()

        if (adapter.itemCount == 0)
            presenter.loadLeagues()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(PARAM_LIST_LEAGUES, adapter.listOfLeagues())

    }

    override fun onRefresh() {
        presenter.loadLeagues()
    }

    override fun loadLeagues(leagues: List<League>) {
        adapter.clearLeagues()
        adapter.addAllLeagues(leagues)
        swipeRefreshLeague.hide()
    }

    override fun showLeaguesNotAvailable(hasLeagues: Boolean) {
        Toast.makeText(this, resources.getString(R.string.leagues_not_available), Toast.LENGTH_LONG).show()
        swipeRefreshLeague.hide()
    }

    override fun showLeagueNotAvailable(hasTeams: Boolean) {
        if (adapter.itemCount <= 0) {
            contentNotAvailable.visibility = if (hasTeams) View.GONE else View.VISIBLE
        }
        swipeRefreshLeague.hide()
    }

    override fun searchLeaguesAvailable() {
        swipeRefreshLeague.hide()
    }

    override fun searchTeamsOfLeagueAvailable() {
        swipeRefreshLeague.hide()
    }

    override fun leagueItemClick(leagueName: String) {
        startActivity(Intent(applicationContext, ListTeamsOfLeagueActivity::class.java).apply {
            putExtra(PARAM_LEAGUES_NAME, leagueName)
        })
    }

    companion object {
        const val PARAM_LIST_LEAGUES = "listLeagues"
    }
}
