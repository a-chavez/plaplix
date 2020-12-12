package cl.nodalnet.plaplix.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        private const val URL_BASE = "http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun getRetrofitClient() : ApiInterface {
            val mRetrofitClient = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return mRetrofitClient.create(ApiInterface::class.java)
        }
    }
}
