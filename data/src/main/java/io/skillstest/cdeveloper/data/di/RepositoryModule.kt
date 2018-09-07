package io.skillstest.cdeveloper.data.di

import dagger.Module
import dagger.Provides
import io.skillstest.cdeveloper.data.endpoints.INetworkService
import io.skillstest.cdeveloper.data.repositories.LeagueRepository
import io.skillstest.cdeveloper.data.repositories.TeamOfLeagueRepository
import io.skillstest.cdeveloper.domain.repositories.ILeagueRepository
import io.skillstest.cdeveloper.domain.repositories.ITeamRepository
import javax.inject.Singleton

@Module(includes = [ServiceModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLeagueRepository(INetworkService: INetworkService): ILeagueRepository = LeagueRepository(INetworkService)

    @Provides
    @Singleton
    fun provideTeamOfLeagueRepository(INetworkService: INetworkService): ITeamRepository = TeamOfLeagueRepository(INetworkService)
}