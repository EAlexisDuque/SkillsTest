package io.skillstest.cdeveloper.skillstest.teamsofleague.teams

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.skillstest.R
import io.skillstest.cdeveloper.skillstest.adapters.TeamsOfLeaguesRecyclerViewAdapter
import io.skillstest.cdeveloper.skillstest.App
import io.skillstest.cdeveloper.skillstest.util.PARAM_LEAGUES_NAME
import io.skillstest.cdeveloper.skillstest.util.hide
import kotlinx.android.synthetic.main.fragment_teams_league.*
import javax.inject.Inject

class TeamsLeagueFragment : Fragment(), ITeamOfLegueContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: ITeamOfLegueContract.Presenter
    private lateinit var leaguesName: String
    private lateinit var adapter: TeamsOfLeaguesRecyclerViewAdapter
    private lateinit var mTeamClickItemListener: OnTeamItemCLickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as App).component().inject(this)
        presenter.view(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leaguesName = arguments?.getString(PARAM_LEAGUES_NAME) ?: ""
        tvNameOfLeague.text = leaguesName

        adapter = TeamsOfLeaguesRecyclerViewAdapter(teamOfLeaguesIItemClickListener = presenter)
        rvTeamsOfLeague.adapter = adapter
        rvTeamsOfLeague.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        swipeRefreshTeamsOfLeague?.setOnRefreshListener(this)

        savedInstanceState?.let {
            adapter.addAllTeamsOfLeagues(savedInstanceState.getParcelableArrayList(PARAM_TEAM_LIST)
                    ?: ArrayList())
        }
    }

    override fun onResume() {
        super.onResume()

        if (adapter.itemCount <= 0)
            presenter.loadTeamsOfLeague(leaguesName)
    }

    override fun onRefresh() {
        presenter.loadTeamsOfLeague(leaguesName)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mTeamClickItemListener = context as OnTeamItemCLickListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${context.toString()} $ERROR_ITEM_CLICK_EVENT_NO_IMPLEMENT")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(PARAM_TEAM_LIST, adapter.listOfTeams())
        super.onSaveInstanceState(outState)
    }

    override fun loadTeamsOfLeague(teamsOfLeague: List<TeamOfLeague>) {
        adapter.clearTeamsOfLeagues()
        adapter.addAllTeamsOfLeagues(teamsOfLeague)
        swipeRefreshTeamsOfLeague.hide()
    }

    override fun showTeamsOfLeagueNotAvailable(hasTeams: Boolean) {
        if (adapter.itemCount <= 0)
            contentNotAvailable?.visibility = if (hasTeams) View.GONE else View.VISIBLE
        swipeRefreshTeamsOfLeague.hide()
    }

    override fun onItemClick(teamOfLeague: TeamOfLeague) {
        mTeamClickItemListener.onTeamItemClick(teamOfLeague)
    }

    companion object {
        const val PARAM_TEAM_LIST = "teamList"
        const val ERROR_ITEM_CLICK_EVENT_NO_IMPLEMENT = "must implement OnTeamItemCLickListener"

        fun newInstance(leaguesName: String): TeamsLeagueFragment {
            return TeamsLeagueFragment().apply {
                arguments = Bundle().apply {
                    putString("leaguesName", leaguesName)
                }
            }
        }

        interface OnTeamItemCLickListener {
            fun onTeamItemClick(teamOfLeague: TeamOfLeague)
        }
    }
}
