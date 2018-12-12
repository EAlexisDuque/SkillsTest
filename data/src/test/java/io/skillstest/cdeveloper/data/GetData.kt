package io.skillstest.cdeveloper.data

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactVerification
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.PactFragment
import io.reactivex.observers.TestObserver
import io.skillstest.cdeveloper.data.endpoints.INetworkService
import io.skillstest.cdeveloper.data.model.ApiTournament
import org.junit.Test
import java.io.UnsupportedEncodingException

/**
 * @author Alexis Duque on 12/12/18.
 * @company Condor Labs.
 * @email eduque@condorlabs.io.
 */

class GetData : BasePactTest<INetworkService>() {

    override var service: INetworkService? = null
    override var type: Class<INetworkService>? = INetworkService::class.java
    override var response: String? = Response.LEAGUES_RESPONSE
    override var url: String? = Endpoints.GET_LEAGUES
    override var method: String? = GET_REQUEST

    @Pact(provider = WALLET_API, consumer = CONSUMER)
    @Throws(UnsupportedEncodingException::class)
    fun createPact(builder: PactDslWithProvider): PactFragment {
        return builder
                .uponReceiving(GET_LEAGUES_DESCRIPTION_TEST)
                .path(url)
                .method(method)
                .willRespondWith()
                .status(SUCCESS_STATUS_CODE)
                .headers(mapOf(CONTENT_TYPE to JSON_CONTENT_TYPE))
                .body(response)
                .toFragment()
    }

    @Test
    @PactVerification(WALLET_API)
    fun shouldGetLeagues() {
        val testObserver = TestObserver<ApiTournament>()
        service?.obtainLeagues()?.subscribeWith(testObserver)

        testObserver.assertNoErrors()
    }
}
