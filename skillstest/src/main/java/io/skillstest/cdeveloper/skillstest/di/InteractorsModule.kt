package io.skillstest.cdeveloper.skillstest.di

import dagger.Module
import dagger.Provides
import io.skillstest.cdeveloper.data.di.RepositoryModule
import io.skillstest.cdeveloper.domain.interactors.GetLeaguesInteractor
import io.skillstest.cdeveloper.domain.interactors.GetTeamsOfLeaguesInteractor
import io.skillstest.cdeveloper.domain.interactors.baseinteractor.IUseCase
import io.skillstest.cdeveloper.domain.model.League
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.domain.repositories.ILeagueRepository
import io.skillstest.cdeveloper.domain.repositories.ITeamRepository
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class InteractorsModule {

    @Provides
    @Singleton
    fun provideGetLeaguesUseCase(legueRepository: ILeagueRepository):
            IUseCase<List<League>, Any?> = GetLeaguesInteractor(legueRepository)

    @Provides
    @Singleton
    fun provideGetTeamsOfLeaguesUseCase(teamOfLeagueRepository: ITeamRepository):
            IUseCase<List<TeamOfLeague>, String> = GetTeamsOfLeaguesInteractor(teamOfLeagueRepository)
}