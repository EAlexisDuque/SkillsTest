package io.skillstest.cdeveloper.data

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactVerification
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.RequestResponsePact
import io.reactivex.observers.TestObserver
import io.skillstest.cdeveloper.data.endpoints.INetworkService
import io.skillstest.cdeveloper.data.model.ApiTournament
import io.skillstest.cdeveloper.data.service.base.BasePactTest
import io.skillstest.cdeveloper.data.service.base.support.Response
import org.json.JSONObject
import org.junit.Test
import java.io.UnsupportedEncodingException

class ExampleUnitTest : BasePactTest<INetworkService>() {

    override var service: INetworkService? = null
    override var type: Class<INetworkService>? = INetworkService::class.java
    override var response: String? = Response.TEAMS_RESPONSE
    override var url: String? = "all_leagues.php"
    override var method: String? = METHOD_GET

    @Pact(consumer = CONSUMER)
    @Throws(UnsupportedEncodingException::class)
    fun createPact(builder: PactDslWithProvider): RequestResponsePact {
        return builder
                .uponReceiving(GET_LEAGUES_DESCRIPTION)
                .path(url)
                .method(method)
                .willRespondWith()
                .status(STATUS_OK)
                .headers(mapOf(CONTENT_TYPE to APPLICATION_JSON))
                .body(JSON_CONTRACT)
                .toPact()
    }

    @Test
    @PactVerification(PROVIDER)
    fun shouldGetTeams() {
        val testObserver = TestObserver<ApiTournament>()
        service?.obtainLeagues()?.subscribeWith(testObserver)
        testObserver.assertNoErrors()
    }

    companion object {
        const val STATUS_OK = 200
        const val PROVIDER = "provider_web"
        const val CONSUMER = "consumer_android"
        const val METHOD_GET = "GET"
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json"
        const val GET_LEAGUES_DESCRIPTION = "Used to get leagues"
        val JSON_CONTRACT = JSONObject("""
            {"leagues":[{"idLeague":"4328","strLeague":"English Premier League","strSport":"Soccer","strLeagueAlternate":"Premier League"},{"idLeague":"4329","strLeague":"English League Championship","strSport":"Soccer","strLeagueAlternate":"Championship, Sky Bet Championship"},{"idLeague":"4330","strLeague":"Scottish Premier League","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4331","strLeague":"German Bundesliga","strSport":"Soccer","strLeagueAlternate":"Bundesliga, Fußball-Bundesliga"},{"idLeague":"4332","strLeague":"Italian Serie A","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4334","strLeague":"French Ligue 1","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4335","strLeague":"Spanish La Liga","strSport":"Soccer","strLeagueAlternate":"La Liga Santander"},{"idLeague":"4336","strLeague":"Greek Superleague Greece","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4337","strLeague":"Dutch Eredivisie","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4338","strLeague":"Belgian Jupiler League","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4339","strLeague":"Turkish Super Lig","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4340","strLeague":"Danish Superliga","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4344","strLeague":"Portuguese Primeira Liga","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4346","strLeague":"American Major League Soccer","strSport":"Soccer","strLeagueAlternate":"MLS, Major League Soccer"},{"idLeague":"4347","strLeague":"Swedish Allsvenskan","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4350","strLeague":"Mexican Primera League","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4351","strLeague":"Brazilian Brasileirao","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4354","strLeague":"Ukrainian Premier League","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4355","strLeague":"Russian Football Premier League","strSport":"Soccer","strLeagueAlternate":"Чемпионат России по футболу"},{"idLeague":"4356","strLeague":"Australian A-League","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4358","strLeague":"Eliteserien","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4359","strLeague":"Chinese Super League","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4367","strLeague":"_No League","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4370","strLeague":"Formula 1","strSport":"Motorsport","strLeagueAlternate":"F1, Formula One, Formula1, Formula-1"},{"idLeague":"4371","strLeague":"Formula E","strSport":"Motorsport","strLeagueAlternate":"Formula-E"},{"idLeague":"4372","strLeague":"BTCC","strSport":"Motorsport","strLeagueAlternate":"British Touring Car Championship"},{"idLeague":"4373","strLeague":"IndyCar Series","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4380","strLeague":"NHL","strSport":"Ice Hockey","strLeagueAlternate":"National Hockey League"},{"idLeague":"4381","strLeague":"UK Elite Ice Hockey League","strSport":"Ice Hockey","strLeagueAlternate":""},{"idLeague":"4387","strLeague":"NBA","strSport":"Basketball","strLeagueAlternate":""},{"idLeague":"4388","strLeague":"NBA D-League","strSport":"Basketball","strLeagueAlternate":"NBA G League"},{"idLeague":"4391","strLeague":"NFL","strSport":"American Football","strLeagueAlternate":"NFL"},{"idLeague":"4393","strLeague":"NASCAR Sprint Cup Series","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4394","strLeague":"Italian Serie B","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4395","strLeague":"Scottish Championship","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4396","strLeague":"English League 1","strSport":"Soccer","strLeagueAlternate":"League One, Sky Bet League One"},{"idLeague":"4397","strLeague":"English League 2","strSport":"Soccer","strLeagueAlternate":"League Two, Sky Bet League Two"},{"idLeague":"4398","strLeague":"Italian Lega Pro","strSport":"Soccer","strLeagueAlternate":"Serie C"},{"idLeague":"4399","strLeague":"German 2. Bundesliga","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4400","strLeague":"Spanish Adelante","strSport":"Soccer","strLeagueAlternate":"Segunda División"},{"idLeague":"4401","strLeague":"French Ligue 2","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4403","strLeague":"Swedish Superettan","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4404","strLeague":"Brazilian Brasileirao Serie B","strSport":"Soccer","strLeagueAlternate":null},{"idLeague":"4405","strLeague":"CFL","strSport":"American Football","strLeagueAlternate":""},{"idLeague":"4406","strLeague":"Argentinian Primera Division","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4407","strLeague":"MotoGP","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4408","strLeague":"Spanish Liga ACB","strSport":"Basketball","strLeagueAlternate":"Liga Endesa"},{"idLeague":"4409","strLeague":"WRC","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4410","strLeague":"British GT Championship","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4411","strLeague":"WTCC","strSport":"Motorsport","strLeagueAlternate":"FIA World Touring Car Championship"},{"idLeague":"4412","strLeague":"Super GT series","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4413","strLeague":"WEC","strSport":"Motorsport","strLeagueAlternate":"FIA World Endurance Championship"},{"idLeague":"4414","strLeague":"Rugby Union Premiership","strSport":"Rugby","strLeagueAlternate":"Premiership Rugby"},{"idLeague":"4415","strLeague":"English Rugby League Super League","strSport":"Rugby","strLeagueAlternate":""},{"idLeague":"4416","strLeague":"Australian National Rugby League","strSport":"Rugby","strLeagueAlternate":"NRL, National Rugby League"},{"idLeague":"4417","strLeague":"Australian National Rugby Championship","strSport":"Rugby","strLeagueAlternate":""},{"idLeague":"4419","strLeague":"Swedish Hockey League","strSport":"Ice Hockey","strLeagueAlternate":""},{"idLeague":"4422","strLeague":"Polish Ekstraklasa","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4423","strLeague":"LNB","strSport":"Basketball","strLeagueAlternate":""},{"idLeague":"4424","strLeague":"MLB","strSport":"Baseball","strLeagueAlternate":""},{"idLeague":"4425","strLeague":"PGA Tour","strSport":"Golf","strLeagueAlternate":""},{"idLeague":"4426","strLeague":"European Tour","strSport":"Golf","strLeagueAlternate":""},{"idLeague":"4427","strLeague":"Can-Am","strSport":"Baseball","strLeagueAlternate":""},{"idLeague":"4428","strLeague":"Frontier League","strSport":"Baseball","strLeagueAlternate":""},{"idLeague":"4429","strLeague":"FIFA World Cup","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4430","strLeague":"Top 14","strSport":"Rugby","strLeagueAlternate":""},{"idLeague":"4431","strLeague":"British Basketball League","strSport":"Basketball","strLeagueAlternate":""},{"idLeague":"4432","strLeague":"Uruguayan Primera Division","strSport":"Soccer","strLeagueAlternate":null},{"idLeague":"4433","strLeague":"Italian Lega Basket","strSport":"Basketball","strLeagueAlternate":""},{"idLeague":"4434","strLeague":"Australian NBL","strSport":"Basketball","strLeagueAlternate":null},{"idLeague":"4435","strLeague":"American NASL","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4436","strLeague":"Moto2","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4437","strLeague":"Moto3","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4438","strLeague":"DTM","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4439","strLeague":"Blancpain Endurance","strSport":"Motorsport","strLeagueAlternate":"Blancpain"},{"idLeague":"4440","strLeague":"Blancpain Sprint","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4441","strLeague":"German BBL","strSport":"Basketball","strLeagueAlternate":null},{"idLeague":"4442","strLeague":"Chinese CBA","strSport":"Basketball","strLeagueAlternate":null},{"idLeague":"4443","strLeague":"UFC","strSport":"Fighting","strLeagueAlternate":"Ultimate Fighting Championship"},{"idLeague":"4444","strLeague":"WWE","strSport":"Fighting","strLeagueAlternate":"World Wrestling Entertainment"},{"idLeague":"4445","strLeague":"Boxing","strSport":"Fighting","strLeagueAlternate":""},{"idLeague":"4446","strLeague":"Pro14","strSport":"Rugby","strLeagueAlternate":"Celtic League, Magners League, RaboDirect Pro12"},{"idLeague":"4447","strLeague":"Dakar Rally","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4448","strLeague":"ROH","strSport":"Fighting","strLeagueAlternate":"Ring of Honor"},{"idLeague":"4449","strLeague":"NJPW","strSport":"Fighting","strLeagueAlternate":"New Japan Pro Wrestling"},{"idLeague":"4450","strLeague":"WCW","strSport":"Fighting","strLeagueAlternate":"World Championship Wrestling"},{"idLeague":"4451","strLeague":"ECW","strSport":"Fighting","strLeagueAlternate":"Extreme Championship Wrestling"},{"idLeague":"4452","strLeague":"Greek Basket League","strSport":"Basketball","strLeagueAlternate":""},{"idLeague":"4453","strLeague":"NWA","strSport":"Fighting","strLeagueAlternate":"National Wrestling Alliance"},{"idLeague":"4454","strLeague":"SBK","strSport":"Motorsport","strLeagueAlternate":" SBK, World Superbike, WSB, or WSBK"},{"idLeague":"4455","strLeague":"TNA","strSport":"Fighting","strLeagueAlternate":"Impact Wrestling"},{"idLeague":"4456","strLeague":"AFL","strSport":"Australian football","strLeagueAlternate":"Australian rules football"},{"idLeague":"4457","strLeague":"Norwegian 1. Divisjon","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4458","strLeague":"County Championship Division 1","strSport":"Cricket","strLeagueAlternate":""},{"idLeague":"4459","strLeague":"County Championship Division 2","strSport":"Cricket","strLeagueAlternate":""},{"idLeague":"4460","strLeague":"Indian Premier League","strSport":"Cricket","strLeagueAlternate":""},{"idLeague":"4461","strLeague":"Big Bash League","strSport":"Cricket","strLeagueAlternate":"KFC Big Bash League"},{"idLeague":"4462","strLeague":"Ram Slam T20 Challenge","strSport":"Cricket","strLeagueAlternate":""},{"idLeague":"4463","strLeague":"t20 Blast","strSport":"Cricket","strLeagueAlternate":""},{"idLeague":"4464","strLeague":"ATP World Tour","strSport":"Tennis","strLeagueAlternate":"Association of Tennis Professionals"},{"idLeague":"4465","strLeague":"UCI World Tour","strSport":"Cycling","strLeagueAlternate":""},{"idLeague":"4466","strLeague":"Electric GT Championship","strSport":"Motorsport","strLeagueAlternate":"Electric GT"},{"idLeague":"4467","strLeague":"Bellator MMA","strSport":"Fighting","strLeagueAlternate":"Bellator Fighting Championships"},{"idLeague":"4468","strLeague":"AMA Supercross","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4469","strLeague":"Pro Motocross","strSport":"Motorsport","strLeagueAlternate":"AMA Motocross Championship"},{"idLeague":"4470","strLeague":"Arena Football League","strSport":"American Football","strLeagueAlternate":"AFL"},{"idLeague":"4471","strLeague":"Indoor Football League","strSport":"American Football","strLeagueAlternate":"IFL"},{"idLeague":"4472","strLeague":"Welsh Premier League","strSport":"Soccer","strLeagueAlternate":""},{"idLeague":"4473","strLeague":"National Arena League","strSport":"American Football","strLeagueAlternate":"NAL"},{"idLeague":"4474","strLeague":"Israeli Basketball Premier League","strSport":"Basketball","strLeagueAlternate":"Ligat HaAl, ליגת העל‎"},{"idLeague":"4475","strLeague":"Turkish Basketbol Super Ligi","strSport":"Basketball","strLeagueAlternate":"BSL"},{"idLeague":"4476","strLeague":"Russian VTB United League","strSport":"Basketball","strLeagueAlternate":"Единая Лига ВТБ"},{"idLeague":"4477","strLeague":"Adriatic ABA League","strSport":"Basketball","strLeagueAlternate":"Adriatic League"},{"idLeague":"4478","strLeague":"Lithuanian LKL","strSport":"Basketball","strLeagueAlternate":"Lietuvos krepšinio lyga"},{"idLeague":"4479","strLeague":"NCAA Division 1","strSport":"American Football","strLeagueAlternate":"NCAA Division I"},{"idLeague":"4480","strLeague":"UEFA Champions League","strSport":"Soccer","strLeagueAlternate":"Champions League"},{"idLeague":"4481","strLeague":"UEFA Europa League","strSport":"Soccer","strLeagueAlternate":"Europa League"},{"idLeague":"4482","strLeague":"FA Cup","strSport":"Soccer","strLeagueAlternate":"Football Association Cup, The Emirates FA Cup"},{"idLeague":"4483","strLeague":"Copa del Rey","strSport":"Soccer","strLeagueAlternate":"Spanish Cup"},{"idLeague":"4484","strLeague":"Coupe de France","strSport":"Soccer","strLeagueAlternate":"French Cup"},{"idLeague":"4485","strLeague":"DFB-Pokal","strSport":"Soccer","strLeagueAlternate":"DFB Cup"},{"idLeague":"4486","strLeague":"Formula 2","strSport":"Motorsport","strLeagueAlternate":"F2, Formula Two, Formula2, Formula-2"},{"idLeague":"4487","strLeague":"GP3 Series","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4488","strLeague":"SportsCar Championship","strSport":"Motorsport","strLeagueAlternate":""},{"idLeague":"4489","strLeague":"V8 Supercars","strSport":"Motorsport","strLeagueAlternate":"Supercars Championship, Virgin Australia Supercars Championship"},{"idLeague":"4490","strLeague":"UEFA Nations League","strSport":"Soccer","strLeagueAlternate":"UNL"},{"idLeague":"4491","strLeague":"Rizin Fighting","strSport":"Fighting","strLeagueAlternate":"Rizin Fighting Federation"}]}
        """)
    }
}

// https://www.programcreek.com/java-api-examples/?api=au.com.dius.pact.consumer.Pact
// https://github.com/DiUS/pact-workshop-android
// https://blog.doordash.com/contract-testing-with-pact-7cf108ced8c4
// https://docs.pact.io/implementation_guides