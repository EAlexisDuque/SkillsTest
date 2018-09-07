package io.skillstest.cdeveloper.domain.interactors

import io.reactivex.Observable
import io.skillstest.cdeveloper.domain.model.League
import io.skillstest.cdeveloper.domain.repositories.ILeagueRepository
import io.skillstest.cdeveloper.domain.interactors.baseinteractor.UseCase

class GetLeaguesInteractor(private val leagueRepository: ILeagueRepository) :
        UseCase<List<League>, Any?>() {

    override fun buildUseCase(params: Any?): Observable<List<League>> {
        return leagueRepository.getLeagues()
    }
}
