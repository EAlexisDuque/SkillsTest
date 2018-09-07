package io.skillstest.cdeveloper.data.endpoints

import io.reactivex.Observable
import io.skillstest.cdeveloper.data.model.ApiTeamsOfLeague
import io.skillstest.cdeveloper.data.model.ApiTournament
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkService {

    @GET("all_leagues.php")
    fun obtainLeagues(): Observable<ApiTournament>

    @GET("search_all_teams.php")
    fun obtainTeamsOfLeague(@Query(PARAM_ID) leaguesName: String): Observable<ApiTeamsOfLeague>


    companion object {
        const val PARAM_ID = "l"
    }
}