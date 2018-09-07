package io.skillstest.cdeveloper.data.mappers

import io.reactivex.functions.Function
import io.skillstest.cdeveloper.data.model.ApiTeamsOfLeague
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.domain.model.TeamsOfLeague

object APITeamsMapper : Function<ApiTeamsOfLeague, List<TeamOfLeague>> {

    override fun apply(t: ApiTeamsOfLeague): List<TeamOfLeague> {
        return TeamsOfLeague( t.teams.map {
            APITeamMapper.apply(it)
        }).teams
    }
}