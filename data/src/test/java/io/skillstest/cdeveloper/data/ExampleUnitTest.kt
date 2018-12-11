package io.skillstest.cdeveloper.data

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactProviderRuleMk2
import au.com.dius.pact.consumer.PactVerification
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.RequestResponsePact
import org.hamcrest.MatcherAssert.assertThat
import org.json.JSONObject
import org.junit.Rule
import org.junit.Test

class ExampleUnitTest {

    @Rule
    @JvmField
    val provider = PactProviderRuleMk2("consumer-mobile-bff", this)

    @Pact(provider = "", consumer = "consumer-android")
    fun createPact(builder: PactDslWithProvider): RequestResponsePact {
        return builder
                .given("default")
                .uponReceiving("Fetching application bootstrap information")
                .path("/v1/bootstrap")
                .method(METHOD_GET)
                .willRespondWith()
                .status(STATUS_OK)
                .body(JSON_CONTRACT)
                .headers(mapOf(CONTENT_TYPE to APPLICATION_JSON))
                .toPact()
    }

    @Test
    @PactVerification
    fun `should fetch bootstrap information from provider`() {
        val client = buildRetrofitClient(provider.url)
        val response = client.bootstrap().blockingGet()

        val version = response.version
        assertThat(version.currentVersion).isNotBlank()
        assertThat(version.minimumVersion).isNotBlank()
    }

    companion object {
        const val STATUS_OK = 200
        const val METHOD_GET = "GET"
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json"
        val JSON_CONTRACT = JSONObject("""
            {
               "leagues":[
                  {
                     "idLeague":"4328",
                     "strLeague":"English Premier League",
                     "strSport":"Soccer",
                     "strLeagueAlternate":"Premier League"
                  }
               ]
            }
        """)
    }
}

// https://www.programcreek.com/java-api-examples/?api=au.com.dius.pact.consumer.Pact
// https://github.com/DiUS/pact-workshop-android
// https://blog.doordash.com/contract-testing-with-pact-7cf108ced8c4
// https://docs.pact.io/implementation_guides