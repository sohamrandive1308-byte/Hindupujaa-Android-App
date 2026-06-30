package com.example.hindupujaa.core.data.local.dao

import androidx.room.*
import com.example.hindupujaa.core.data.local.entities.StoreProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    @Query("SELECT * FROM store_products WHERE isActive = 1 ORDER BY sortOrder")
    fun getProducts(): Flow<List<StoreProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<StoreProductEntity>)
}
