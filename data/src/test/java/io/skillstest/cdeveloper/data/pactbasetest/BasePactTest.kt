package io.skillstest.cdeveloper.data.pactbasetest

import au.com.dius.pact.consumer.PactProviderRuleMk2
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Alexis Duque on 12/12/18.
 * @company Condor Labs.
 * @email eduque@condorlabs.io.
 */

abstract class BasePactTest<T> {

    open var type: Class<T>? = null
    open var service: T? = null
    open var response: String? = null
    open var url: String? = null
    open var method: String? = null

    @Before
    fun setupService() {
        service = Retrofit.Builder()
                .baseUrl(Endpoints.BASE_ROUTE_LEAGUES)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build().create(type)
    }

    @get:Rule
    var mockProvider = PactProviderRuleMk2(WALLET_API, LOCALHOST, DEFAULT_PORT, this)
}
