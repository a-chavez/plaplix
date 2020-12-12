package cl.nodalnet.plaplix.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import cl.nodalnet.plaplix.retrofit.ApiInterface
import cl.nodalnet.plaplix.room.ProductsDAO
import cl.nodalnet.plaplix.room.ProductsDB
import org.junit.After
import org.junit.Before
import org.junit.Rule

class DaoTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mTestDao: ProductsDAO
    private lateinit var db : ProductsDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ProductsDB::class.java).build()
        mTestDao = db.getMasterDAO()
    }

    @After
    fun tearDown() {
        db.close()
    }

}