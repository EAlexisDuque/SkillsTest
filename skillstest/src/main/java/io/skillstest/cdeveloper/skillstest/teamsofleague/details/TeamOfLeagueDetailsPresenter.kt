package io.skillstest.cdeveloper.skillstest.teamsofleague.details

import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.skillstest.util.isNotNull

class TeamOfLeagueDetailsPresenter : ITeamOfLeagueDetailsContract.Presenter {

    private var view: ITeamOfLeagueDetailsContract.View? = null

    override fun view(view: ITeamOfLeagueDetailsContract.View) {
        this.view = view
    }

    override fun loadTeamOfLeagueObject(teamOfLeague: TeamOfLeague) {
        view?.showTeamDescription(teamOfLeague.descriptionEN
                ?: teamOfLeague.descriptionES ?: "")
        view?.showTwitterSocialButtom(teamOfLeague.twitter?.isNotNull()
                ?: false, teamOfLeague.twitter ?: "")
        view?.showFacebookSocialButtom(teamOfLeague.facebook?.isNotNull()
                ?: false, teamOfLeague.facebook ?: "")
        view?.showInstagramSocialButtom(teamOfLeague.instagram?.isNotNull()
                ?: false, teamOfLeague.instagram ?: "")
        view?.showYouTubeSocialButtom(teamOfLeague.youtube?.isNotNull()
                ?: false, teamOfLeague.youtube ?: "")
        view?.showTeamBadge(teamOfLeague.teamBadge?.isNotNull()
                ?: false, teamOfLeague.teamBadge ?: "")
        view?.showTeamJersey(teamOfLeague.teamJersey?.isNotNull()
                ?: false, teamOfLeague.teamJersey ?: "")
        view?.showFundationYear(teamOfLeague.formedYear?.toString() ?: "")
        view?.showTeamWebSite(teamOfLeague.website ?: "")
    }

    override fun openUrlOnWebBrowser(url: String) {
        view?.openUrl(url)
    }
}