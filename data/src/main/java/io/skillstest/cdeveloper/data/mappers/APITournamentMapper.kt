package io.skillstest.cdeveloper.data.mappers

import io.reactivex.functions.Function
import io.skillstest.cdeveloper.data.model.ApiTournament
import io.skillstest.cdeveloper.domain.model.League
import io.skillstest.cdeveloper.domain.model.Tournament

object APITournamentMapper : Function<ApiTournament, List<League>> {

    override fun apply(t: ApiTournament): List<League> {
        return Tournament(t.leagues.map {
            APILeagueMapper.apply(it)
        }).leagues
    }
}