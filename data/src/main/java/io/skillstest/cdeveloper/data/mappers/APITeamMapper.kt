package io.skillstest.cdeveloper.data.mappers

import io.reactivex.functions.Function
import io.skillstest.cdeveloper.data.model.ApiTeamOfLeague
import io.skillstest.cdeveloper.domain.model.TeamOfLeague

object APITeamMapper : Function<ApiTeamOfLeague, TeamOfLeague> {

    override fun apply(t: ApiTeamOfLeague): TeamOfLeague {
        return TeamOfLeague(
                t.idTeam,
                t.strTeam,
                t.strStadium,
                t.strTeamBadge,
                t.strTeamJersey,
                t.intFormedYear,
                t.strDescriptionEN,
                t.strDescriptionES,
                t.strWebsite,
                t.strTwitter,
                t.strFacebook,
                t.strInstagram,
                t.strYoutube
        )
    }
}