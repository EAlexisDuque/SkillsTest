package io.skillstest.cdeveloper.skillstest.teamsofleague

import io.skillstest.cdeveloper.domain.model.TeamOfLeague

class ListTeamsOfLeaguePresenter : IListTeamsOfLeagueContract.Presenter {

    private var view: IListTeamsOfLeagueContract.View? = null

    override fun view(view: IListTeamsOfLeagueContract.View) {
        this.view = view
    }

    override fun teamItemClicked(teamOfLeague: TeamOfLeague) {
        view?.showTeamOfLeaguesDetails(teamOfLeague, view?.isModeDualPane() ?: false)
    }
}