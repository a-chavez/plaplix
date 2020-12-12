package cl.nodalnet.plaplix.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(mProductsDB: List<ProductsItem>)

    @Query("SELECT * FROM master_table")
    fun getAllData(): LiveData<List<ProductsItem>>

    @Query("SELECT * FROM master_table WHERE id=:mID")
    fun getOneGoods(mID: Int): LiveData<ProductsItem>
}
