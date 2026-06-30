package com.example.hindupujaa.core.data.local.dao

import androidx.room.*
import com.example.hindupujaa.core.data.local.entities.PujaEntity
import com.example.hindupujaa.core.data.local.entities.KitItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PujaDao {
    @Query("SELECT * FROM pujas WHERE isActive = 1 ORDER BY sortOrder")
    fun getPujas(): Flow<List<PujaEntity>>

    @Query("SELECT * FROM pujas WHERE slug = :slug")
    fun getPujaBySlug(slug: String): Flow<PujaEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPujas(pujas: List<PujaEntity>)

    @Query("SELECT * FROM kit_items WHERE pujaId = :pujaId ORDER BY sortOrder")
    fun getKitItems(pujaId: String): Flow<List<KitItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKitItems(items: List<KitItemEntity>)
}
