package cl.nodalnet.plaplix.viewmodel

import android.app.Application
import android.telecom.Call
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cl.nodalnet.plaplix.MyRepository
import cl.nodalnet.plaplix.room.DetailsItem
import cl.nodalnet.plaplix.room.ProductsDB
import cl.nodalnet.plaplix.room.ProductsItem

class MyViewModel(application: Application):AndroidViewModel(application) {
    private val mMyRepository : MyRepository
    val mAllProducts : LiveData<List<ProductsItem>>
    val mAllDetails : LiveData<List<DetailsItem>>

    init {
        val mProductsDAO = ProductsDB.getDataBase(application).getMasterDAO()
        val mDetailsDAO = ProductsDB.getDataBase(application).getDetailstDAO()
        mMyRepository = MyRepository(mProductsDAO, mDetailsDAO)
        mAllProducts = mMyRepository.mLiveData
        mAllDetails = mMyRepository.mLiveDataDetails
        mMyRepository.getDataFromProducts()
        mMyRepository.getDataFromDetails()
    }

    fun getOneGoods(mID :Int):LiveData<DetailsItem>{
        return mMyRepository.getOneGoods(mID)
    }


}