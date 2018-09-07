package io.skillstest.cdeveloper.skillstest.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.skillstest.cdeveloper.domain.interactors.baseinteractor.IUseCase
import io.skillstest.cdeveloper.domain.model.League
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.skillstest.teamsofleague.IListTeamsOfLeagueContract
import io.skillstest.cdeveloper.skillstest.teamsofleague.ListTeamsOfLeaguePresenter
import io.skillstest.cdeveloper.skillstest.teamsofleague.details.ITeamOfLeagueDetailsContract
import io.skillstest.cdeveloper.skillstest.teamsofleague.details.TeamOfLeagueDetailsPresenter
import io.skillstest.cdeveloper.skillstest.leagues.ILeagueContract
import io.skillstest.cdeveloper.skillstest.leagues.LeaguePresenter
import io.skillstest.cdeveloper.skillstest.teamsofleague.teams.TeamOfLeaguePresenter
import io.skillstest.cdeveloper.skillstest.teamsofleague.teams.ITeamOfLegueContract
import javax.inject.Singleton

@Module(includes = [InteractorsModule::class])
class AppModule(val application: Application) {

    /**--------------------------------------- CONTEXT ------------------------------------------**/
    @Provides
    @Singleton
    fun provideAppContext(): Context = application

    /**--------------------------------------- LEAGUES ------------------------------------------**/
    @Provides
    fun provideLeaguePresenter(getLeaguesInteractor: IUseCase<List<League>, Any?>):
            ILeagueContract.Presenter = LeaguePresenter(getLeaguesInteractor)

    /**----------------------------------- TEAMS OF LEAGUE --------------------------------------**/
    @Provides
    fun provideTeamsOfLeaguePresenter(getTeamsInteractor: IUseCase<List<TeamOfLeague>, String>):
            ITeamOfLegueContract.Presenter = TeamOfLeaguePresenter(getTeamsInteractor)

    @Provides
    @Singleton
    fun provideListTeamsOfLeagueActivityPresenter(): IListTeamsOfLeagueContract.Presenter =
            ListTeamsOfLeaguePresenter()

    @Provides
    @Singleton
    fun provideTeamOfLeagueDetailsPresenter(): ITeamOfLeagueDetailsContract.Presenter =
            TeamOfLeagueDetailsPresenter()
}