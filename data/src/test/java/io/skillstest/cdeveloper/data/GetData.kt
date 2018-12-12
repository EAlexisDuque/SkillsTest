package io.skillstest.cdeveloper.data

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactProviderRule
import au.com.dius.pact.consumer.PactVerification
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.PactFragment
import com.google.gson.Gson
import io.reactivex.observers.TestObserver
import io.skillstest.cdeveloper.data.endpoints.INetworkService
import io.skillstest.cdeveloper.data.model.ApiTournament
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.UnsupportedEncodingException

/**
 * @author Alexis Duque on 12/12/18.
 * @company Condor Labs.
 * @email eduque@condorlabs.io.
 */

class GetData {

    private val url = BOARD_CERTIFICATION_EP
    var service: INetworkService? = null

    @Before
    fun setupService(){
        service = Retrofit.Builder()
                .baseUrl("http://localhost:8085")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build().create(INetworkService::class.java)
    }

    @get:Rule
    open var mockProvider = PactProviderRule(WALLET_API, LOCALHOST, DEFAULT_PORT, this)

    @Pact(provider = WALLET_API, consumer = CONSUMER)
    @Throws(UnsupportedEncodingException::class)
    fun createfragment(builder: PactDslWithProvider): PactFragment {
        val headers = HashMap<String, String>()
        headers[CONTENT_TYPE] = JSON_CONTENT_TYPE
        return builder
                .uponReceiving("Description")
                .path(url)
                .method("GET")
                .willRespondWith()
                .status(SUCCESS_STATUS_CODE)
                .headers(headers)
                .body("{}")
                .toFragment()
    }

    @Test
    @PactVerification(WALLET_API)
    fun shouldGetBoardCertifications() {
        val testObserver = TestObserver<ApiTournament>()
        service?.obtainLeagues()?.subscribeWith(testObserver)

        testObserver.assertNoErrors()
    }
}
