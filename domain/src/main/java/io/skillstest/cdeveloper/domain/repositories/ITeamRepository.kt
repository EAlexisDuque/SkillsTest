package io.skillstest.cdeveloper.domain.repositories

import io.reactivex.Observable
import io.skillstest.cdeveloper.domain.model.TeamOfLeague

interface ITeamRepository {

    fun getTeamsOfLeague(leaguesName: String): Observable<List<TeamOfLeague>>

}