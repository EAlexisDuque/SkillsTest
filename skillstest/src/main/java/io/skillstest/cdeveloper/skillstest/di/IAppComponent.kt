package io.skillstest.cdeveloper.skillstest.di

import dagger.Component
import io.skillstest.cdeveloper.skillstest.teamsofleague.ListTeamsOfLeagueActivity
import io.skillstest.cdeveloper.skillstest.teamsofleague.details.TeamOfLeagueDetailsFragment
import io.skillstest.cdeveloper.skillstest.teamsofleague.teams.TeamsLeagueFragment
import io.skillstest.cdeveloper.skillstest.leagues.ListLeaguesActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface IAppComponent {

    fun inject(target: ListLeaguesActivity)

    fun inject(target: ListTeamsOfLeagueActivity)

    fun inject(tarjet: TeamsLeagueFragment)

    fun inject(target: TeamOfLeagueDetailsFragment)
}