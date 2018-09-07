package io.skillstest.cdeveloper.data.repositories

import io.reactivex.Observable
import io.skillstest.cdeveloper.data.endpoints.INetworkService
import io.skillstest.cdeveloper.data.mappers.APITeamsMapper
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.domain.repositories.ITeamRepository

class TeamOfLeagueRepository(private val INetworkService: INetworkService) : ITeamRepository {

    override fun getTeamsOfLeague(leaguesName: String): Observable<List<TeamOfLeague>> {
        return INetworkService.obtainTeamsOfLeague(leaguesName).map {
            APITeamsMapper.apply(it)
        }
    }
}