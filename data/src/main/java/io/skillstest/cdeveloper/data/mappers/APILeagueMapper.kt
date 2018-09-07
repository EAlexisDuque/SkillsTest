package io.skillstest.cdeveloper.data.mappers

import io.reactivex.functions.Function
import io.skillstest.cdeveloper.data.model.ApiLeague
import io.skillstest.cdeveloper.domain.model.League

object APILeagueMapper : Function<ApiLeague, League> {

    override fun apply(t: ApiLeague): League {
        return League(
                t.idLeague,
                t.strLeague,
                t.strLeagueAlternate ?: "",
                t.strSport)
    }
}