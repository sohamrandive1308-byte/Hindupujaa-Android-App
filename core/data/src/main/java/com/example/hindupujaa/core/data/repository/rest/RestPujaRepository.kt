package com.example.hindupujaa.core.data.repository.rest

import com.example.hindupujaa.core.data.api.HinduPujaaApi
import com.example.hindupujaa.core.data.local.dao.PujaDao
import com.example.hindupujaa.core.data.local.entities.toDomain
import com.example.hindupujaa.core.data.local.entities.toEntity
import com.example.hindupujaa.core.domain.model.Category
import com.example.hindupujaa.core.domain.model.KitItem
import com.example.hindupujaa.core.domain.model.Puja
import com.example.hindupujaa.core.domain.model.StoreProduct
import com.example.hindupujaa.core.domain.repository.PujaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RestPujaRepository @Inject constructor(
    private val api: HinduPujaaApi,
    private val pujaDao: PujaDao
) : PujaRepository {

    override fun getPujas(): Flow<List<Puja>> {
        return pujaDao.getPujas().map { entities -> 
            entities.map { it.toDomain() }
        }.onEach {
            try {
                val remotePujas = api.getPujas()
                pujaDao.insertPujas(remotePujas.map { it.toEntity() })
            } catch (e: Exception) {
                // Network error, stay with local data
            }
        }
    }

    override fun getCategories(): Flow<List<Category>> {
        // Categories can be hardcoded or fetched from a simplified endpoint
        return kotlinx.coroutines.flow.flowOf(emptyList()) 
    }

    override fun getPujasByCategory(categoryId: String): Flow<List<Puja>> {
        return getPujas().map { pujas -> pujas.filter { it.category == categoryId } }
    }

    override fun getPujaById(pujaId: String): Flow<Puja?> {
        // Using slug as ID for REST usually
        return pujaDao.getPujaBySlug(pujaId).map { it?.toDomain() }
    }

    override suspend fun searchPujas(query: String): List<Puja> {
        // Implementation for searching
        return emptyList()
    }

    override fun getKitItems(pujaId: String): Flow<List<KitItem>> {
        return pujaDao.getKitItems(pujaId).map { entities -> 
            entities.map { it.toDomain() }
        }.onEach {
            try {
                val remoteItems = api.getKitItems(pujaId)
                pujaDao.insertKitItems(remoteItems.map { it.toEntity(pujaId) })
            } catch (e: Exception) {}
        }
    }

    override fun getStoreProducts(): Flow<List<StoreProduct>> = kotlinx.coroutines.flow.flowOf(emptyList())
}
