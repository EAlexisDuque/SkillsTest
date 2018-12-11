package io.skillstest.cdeveloper.data.service.base

import au.com.dius.pact.consumer.PactProviderRuleMk2
import io.skillstest.cdeveloper.data.ExampleUnitTest.Companion.CONSUMER
import io.skillstest.cdeveloper.data.util.BASE_URL
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Alexis Duque on 12/11/18.
 * @company Condor Labs.
 * @email eduque@condorlabs.io.
 */

abstract class BasePactTest<T> {

    open var type: Class<T>? = null
    open var service: T? = null
    open var response: String? = null
    open var url: String? = null
    open var method: String? = null

    @get:Rule
    open var mockProvider = PactProviderRuleMk2(CONSUMER, this)

    @Before
    fun setup() {
        setupWebClient()
    }

    fun setupWebClient() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(type)
}
