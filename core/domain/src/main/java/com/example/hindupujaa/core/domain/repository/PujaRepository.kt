package com.example.hindupujaa.core.domain.repository

import com.example.hindupujaa.core.domain.model.Category
import com.example.hindupujaa.core.domain.model.KitItem
import com.example.hindupujaa.core.domain.model.Puja
import com.example.hindupujaa.core.domain.model.StoreProduct
import kotlinx.coroutines.flow.Flow

interface PujaRepository {
    fun getPujas(): Flow<List<Puja>>
    fun getCategories(): Flow<List<Category>>
    fun getPujasByCategory(categoryId: String): Flow<List<Puja>>
    fun getPujaById(pujaId: String): Flow<Puja?>
    suspend fun searchPujas(query: String): List<Puja>
    fun getKitItems(pujaId: String): Flow<List<KitItem>>
    fun getStoreProducts(): Flow<List<StoreProduct>>
}
