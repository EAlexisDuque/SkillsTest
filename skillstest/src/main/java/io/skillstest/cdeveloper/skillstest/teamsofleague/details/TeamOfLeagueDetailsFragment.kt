package io.skillstest.cdeveloper.skillstest.teamsofleague.details


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.skillstest.R
import io.skillstest.cdeveloper.skillstest.App
import io.skillstest.cdeveloper.skillstest.util.PARAM_TEAM_OF_LEAGUE
import io.skillstest.cdeveloper.skillstest.util.loadImageFromUrl
import kotlinx.android.synthetic.main.fragment_team_of_league_details.*
import javax.inject.Inject

class TeamOfLeagueDetailsFragment : Fragment(), ITeamOfLeagueDetailsContract.View {

    @Inject
    lateinit var presenter: ITeamOfLeagueDetailsContract.Presenter
    private var teamOfLeague: TeamOfLeague? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_of_league_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.application as? App)?.component()?.inject(this)
        presenter.view(this)

        teamOfLeague = arguments?.getParcelable(PARAM_TEAM_OF_LEAGUE)

        teamLeagueNameDetail.text = teamOfLeague?.name ?: ""


        teamOfLeague.let {
            if (it != null) {
                presenter.loadTeamOfLeagueObject(it)
            }
        }
    }

    override fun showTeamDescription(description: String) {
        if (!description.equals(""))
            teamLeagueDescriptionDetail.text = description
        else
            teamLeagueDescriptionDetail.visibility = View.GONE
    }

    override fun showTeamBadge(visible: Boolean, url: String) {
        layoutTeamBadge.visibility = if (visible) View.VISIBLE else View.GONE
        if (visible) imgTeamLeagueBadge.loadImageFromUrl(url)
    }

    override fun showTeamJersey(visible: Boolean, url: String) {
        layoutTeamJersey.visibility = if (visible) View.VISIBLE else View.GONE
        if (visible) imgTeamLeagueJersey.loadImageFromUrl(url)
    }

    override fun showFundationYear(yearFundation: String) {
        if (!yearFundation.equals(""))
            teamLeagueFoundationYearDetail.text = yearFundation
        else
            layoutFoundationYear.visibility = View.GONE
    }

    override fun showTwitterSocialButtom(visible: Boolean, url: String) {
        if (visible)
            imgTeamTwitterSocial.setOnClickListener { presenter.openUrlOnWebBrowser(url) }
        else
            imgTeamTwitterSocial.visibility = View.GONE
    }

    override fun showFacebookSocialButtom(visible: Boolean, url: String) {
        if (visible)
            imgTeamFacebookSocial.setOnClickListener { presenter.openUrlOnWebBrowser(url) }
        else
            imgTeamFacebookSocial.visibility = View.GONE
    }

    override fun showInstagramSocialButtom(visible: Boolean, url: String) {
        if (visible)
            imgTeamInstagramSocial.setOnClickListener { presenter.openUrlOnWebBrowser(url) }
        else
            imgTeamInstagramSocial.visibility = View.GONE
    }

    override fun showYouTubeSocialButtom(visible: Boolean, url: String) {
        if (visible)
            imgTeamYoutubeSocial.setOnClickListener { presenter.openUrlOnWebBrowser(url) }
        else
            imgTeamYoutubeSocial.visibility = View.GONE
    }

    override fun showTeamWebSite(linkWebSite: String) {
        layoutWebsite.visibility = if (linkWebSite != "") {
            teamLeagueWebsiteDetail.text = linkWebSite
            teamLeagueWebsiteDetail.setOnClickListener { presenter.openUrlOnWebBrowser(linkWebSite) }
            View.VISIBLE
        } else
            View.GONE
    }

    override fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://$url")))
    }

    companion object {
        fun newInstance(teamOfLeague: TeamOfLeague): TeamOfLeagueDetailsFragment {
            return TeamOfLeagueDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAM_TEAM_OF_LEAGUE, teamOfLeague)
                }
            }
        }
    }
}