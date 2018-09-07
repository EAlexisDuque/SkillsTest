package io.skillstest.cdeveloper.data.repositories

import io.reactivex.Observable
import io.skillstest.cdeveloper.data.endpoints.INetworkService
import io.skillstest.cdeveloper.data.mappers.APITournamentMapper
import io.skillstest.cdeveloper.domain.model.League
import io.skillstest.cdeveloper.domain.repositories.ILeagueRepository

class LeagueRepository(private val INetworkService: INetworkService) : ILeagueRepository {

    override fun getLeagues(): Observable<List<League>> {
        return INetworkService.obtainLeagues().map {
            APITournamentMapper.apply(it)
        }
    }
}
