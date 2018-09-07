package io.skillstest.cdeveloper.skillstest.teamsofleague.teams

import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.domain.interactors.baseinteractor.IUseCase

class TeamOfLeaguePresenter(private val getTeamsOfLeaguesInteractor: IUseCase<List<TeamOfLeague>, String>) : ITeamOfLegueContract.Presenter {

    private var view: ITeamOfLegueContract.View? = null
    private var listTeamOfLeague: List<TeamOfLeague>? = null

    override fun view(view: ITeamOfLegueContract.View) {
        this.view = view
    }

    override fun loadTeamsOfLeague(leaguesName: String) {

        if (listTeamOfLeague == null)
            getTeamsOfLeaguesInteractor.invoke(leaguesName, {
                it.let {
                    listTeamOfLeague = it
                    view?.showTeamsOfLeagueNotAvailable(it.isNotEmpty())
                    view?.loadTeamsOfLeague(it)
                }
            }, {
                view?.showTeamsOfLeagueNotAvailable(false)
            })
        else {
            listTeamOfLeague?.let {
                view?.showTeamsOfLeagueNotAvailable(it.isNotEmpty())
                view?.loadTeamsOfLeague(it)
            }
        }
    }

    override fun itemCLick(t: TeamOfLeague) {
        view?.onItemClick(t)
    }
}