package io.skillstest.cdeveloper.domain.repositories

import io.reactivex.Observable
import io.skillstest.cdeveloper.domain.model.League

interface ILeagueRepository {

    fun getLeagues(): Observable<List<League>>

}