package cl.nodalnet.plaplix.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DetailsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(mProductsDB: List<DetailsItem>)

    @Query("SELECT * FROM details_table")
    fun getAllData(): LiveData<List<DetailsItem>>

    @Query("SELECT * FROM details_table WHERE id=:mID")
    fun getOneGoods(mID: Int): LiveData<DetailsItem>
}