package io.skillstest.cdeveloper.skillstest.leagues

import io.skillstest.cdeveloper.domain.model.League
import io.skillstest.cdeveloper.skillstest.adapters.IItemClickListener

interface ILeagueContract {

    interface View {

        fun loadLeagues(leagues: List<League>)

        fun leagueItemClick(leagueName: String)

        fun showLeaguesNotAvailable(hasLeagues: Boolean)

        fun showLeagueNotAvailable(hasTeams: Boolean)

        fun searchLeaguesAvailable()

        fun searchTeamsOfLeagueAvailable()
    }

    interface Presenter : IItemClickListener<League> {

        fun view(view: View)

        fun loadLeagues()

    }
}