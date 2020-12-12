package cl.nodalnet.plaplix

import android.util.Log
import androidx.lifecycle.LiveData
import cl.nodalnet.plaplix.retrofit.DetailsList
import cl.nodalnet.plaplix.retrofit.ProductsList
import cl.nodalnet.plaplix.retrofit.RetrofitClient
import cl.nodalnet.plaplix.room.DetailsDAO
import cl.nodalnet.plaplix.room.DetailsItem
import cl.nodalnet.plaplix.room.ProductsDAO
import cl.nodalnet.plaplix.room.ProductsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepository(private val mProductsDAO: ProductsDAO, private val mDetailsDAO: DetailsDAO) {
    private val service = RetrofitClient.getRetrofitClient()

    val mLiveData = mProductsDAO.getAllData()
    val mLiveDataDetails = mDetailsDAO.getAllData()
    //val mDataProductsItem = mutableListOf<ProductsItem>()

    fun getDataFromProducts() {
        val mCall = service.getDataFromApi()
        mCall.enqueue(object : Callback<ProductsList> {
            override fun onResponse(call: Call<ProductsList>, response: Response<ProductsList>) {
                //Log.d("Arroz onResponse", response.code().toString())
                when(response.code()){

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch{
                        response.body()?.let{
                            //Log.d("Arroz desde API", it.toString())
                            mProductsDAO.insertAllData(it)
                        }

                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<ProductsList>, t: Throwable) {
                Log.e("MyRepository", t.message.toString())
            }

        })
    }

    fun getDataFromDetails() {
        val mCall = service.getDataFromOne()
        mCall.enqueue(object : Callback<DetailsList> {
            override fun onResponse(call: Call<DetailsList>, response: Response<DetailsList>) {
                //Log.d("Arroz onResponse", response.code().toString())
                when(response.code()){

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch{
                        response.body()?.let{
                            //Log.d("Arroz desde API", it.toString())
                            mDetailsDAO.insertAllData(it)
                        }

                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<DetailsList>, t: Throwable) {
                Log.e("MyRepository", t.message.toString())
            }

        })
    }

    fun getOneGoods(mID :Int): LiveData<DetailsItem> {
        return mDetailsDAO.getOneGoods(mID)
    }


}