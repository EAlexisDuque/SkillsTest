package io.skillstest.cdeveloper.skillstest.teamsofleague

import io.skillstest.cdeveloper.domain.model.TeamOfLeague

interface IListTeamsOfLeagueContract {

    interface View {

        fun showTeamOfLeaguesDetails(teamOfLeague: TeamOfLeague, isDualPane: Boolean)

        fun isModeDualPane(): Boolean
    }

    interface Presenter {

        fun view(view: View)

        fun teamItemClicked(teamOfLeague: TeamOfLeague)

    }
}