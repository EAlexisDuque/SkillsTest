package io.skillstest.cdeveloper.domain.interactors

import io.reactivex.Observable
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.domain.repositories.ITeamRepository
import io.skillstest.cdeveloper.domain.interactors.baseinteractor.UseCase

class GetTeamsOfLeaguesInteractor(private val teamRepository: ITeamRepository) : UseCase<List<TeamOfLeague>, String>() {

    override fun buildUseCase(params: String): Observable<List<TeamOfLeague>> {
        return teamRepository.getTeamsOfLeague(params)
    }
}
