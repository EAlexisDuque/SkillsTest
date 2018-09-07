package io.skillstest.cdeveloper.skillstest.teamsofleague.teams

import android.os.Bundle
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.skillstest.adapters.IItemClickListener

interface ITeamOfLegueContract {

    interface View {

        fun loadTeamsOfLeague(teamsOfLeague: List<TeamOfLeague>)

        fun showTeamsOfLeagueNotAvailable(hasTeams: Boolean)

        fun onItemClick(teamOfLeague: TeamOfLeague)

        fun getArguments(): Bundle?
    }

    interface Presenter : IItemClickListener<TeamOfLeague> {
        fun view(view: View)

        fun loadTeamsOfLeague(leaguesName: String)
    }
}