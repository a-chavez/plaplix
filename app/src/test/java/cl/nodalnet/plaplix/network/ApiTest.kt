package cl.nodalnet.plaplix.network

import androidx.test.ext.truth.app.NotificationActionSubject.assertThat
import cl.nodalnet.plaplix.retrofit.ApiInterface
import cl.nodalnet.plaplix.retrofit.ProductsList
import cl.nodalnet.plaplix.room.ProductsItem
import com.google.common.truth.Truth
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class ApiTest {
    lateinit var mMockWebServer: MockWebServer
    lateinit var mApiInterface: ApiInterface

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val mRetrofit = Retrofit.Builder()
            .baseUrl(mMockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiInterface = mRetrofit.create(ApiInterface::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getAllProducts_test() = runBlocking {
        val mProductsItem = ArrayList<ProductsItem>()

        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mProductsItem)))




    }




}