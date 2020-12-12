package cl.nodalnet.plaplix.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    fun getDataFromApi(): Call<ProductsList>

    @GET("details")
    fun getDataFromOne(): Call<DetailsList>
}