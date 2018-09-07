package io.skillstest.cdeveloper.skillstest.teamsofleague.details

import io.skillstest.cdeveloper.domain.model.TeamOfLeague


interface ITeamOfLeagueDetailsContract {

    interface View {

        fun showTeamWebSite(linkWebSite: String)

        fun showTeamDescription(description: String)

        fun showFundationYear(yearFundation: String)

        fun showTeamBadge(visible: Boolean, url: String)

        fun showTeamJersey(visible: Boolean, url: String)

        fun showTwitterSocialButtom(visible: Boolean, url: String)

        fun showFacebookSocialButtom(visible: Boolean, url: String)

        fun showInstagramSocialButtom(visible: Boolean, url: String)

        fun showYouTubeSocialButtom(visible: Boolean, url: String)

        fun openUrl(url: String)
    }

    interface Presenter {

        fun view(view: View)

        fun loadTeamOfLeagueObject(teamOfLeague: TeamOfLeague)

        fun openUrlOnWebBrowser(url: String)
    }
}