package com.example.hindupujaa.core.data.repository

import com.example.hindupujaa.core.domain.model.Category
import com.example.hindupujaa.core.domain.model.KitItem
import com.example.hindupujaa.core.domain.model.Puja
import com.example.hindupujaa.core.domain.model.StoreProduct
import com.example.hindupujaa.core.domain.repository.PujaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockPujaRepository @Inject constructor() : PujaRepository {

    private val categories = listOf(
        Category("1", "Daily Puja", "android.resource://com.example.hindupujaa/drawable/daily_puja", "Pujas for daily routine"),
        Category("2", "Festivals", "android.resource://com.example.hindupujaa/drawable/festivals", "Pujas for special festivals"),
        Category("3", "Havan", "android.resource://com.example.hindupujaa/drawable/havan", "Sacred fire ceremonies"),
        Category("4", "Wedding", "android.resource://com.example.hindupujaa/drawable/wedding", "Marriage related ceremonies"),
        Category("5", "New Home", "android.resource://com.example.hindupujaa/drawable/new_home", "Griha Pravesh and Vastu")
    )

    private val pujas = listOf(
        Puja(
            id = "p1",
            nameEn = "Ganesh Puja",
            backstory = "Invoke the blessings of Lord Ganesha for success and removal of obstacles.",
            heroImagePath = "android.resource://com.example.hindupujaa/drawable/daily_puja",
            category = "Daily Puja",
            basePrice = 1100.0,
            durationLabel = "1.5 hours",
            benefits = listOf("Success in new ventures", "Removal of obstacles", "Prosperity")
        ),
        Puja(
            id = "p2",
            nameEn = "Satyanarayan Katha",
            backstory = "A sacred ritual dedicated to Lord Vishnu for peace and abundance.",
            heroImagePath = "android.resource://com.example.hindupujaa/drawable/daily_puja",
            category = "Daily Puja",
            basePrice = 2100.0,
            durationLabel = "3 hours",
            benefits = listOf("Family peace", "Spiritual growth", "Fulfillment of desires")
        ),
        Puja(
            id = "p3",
            nameEn = "Maha Mrityunjaya Havan",
            backstory = "Powerful Havan for health, longevity, and protection.",
            heroImagePath = "android.resource://com.example.hindupujaa/drawable/havan",
            category = "Havan",
            basePrice = 5100.0,
            durationLabel = "4 hours",
            benefits = listOf("Good health", "Protection from negativity", "Longevity")
        ),
        Puja(
            id = "p4",
            nameEn = "Griha Pravesh",
            backstory = "Blessing your new home with positive energy and divine protection.",
            heroImagePath = "android.resource://com.example.hindupujaa/drawable/new_home",
            category = "New Home",
            basePrice = 7500.0,
            durationLabel = "5 hours",
            benefits = listOf("Vastu purification", "Divine protection", "Happiness in new home")
        )
    )

    override fun getPujas(): Flow<List<Puja>> = flowOf(pujas)

    override fun getCategories(): Flow<List<Category>> = flowOf(categories)

    override fun getPujasByCategory(categoryId: String): Flow<List<Puja>> = 
        flowOf(pujas.filter { it.category == categoryId })

    override fun getPujaById(pujaId: String): Flow<Puja?> = 
        flowOf(pujas.find { it.id == pujaId })

    override suspend fun searchPujas(query: String): List<Puja> = 
        pujas.filter { it.nameEn.contains(query, ignoreCase = true) }

    override fun getKitItems(pujaId: String): Flow<List<KitItem>> = flowOf(emptyList())

    override fun getStoreProducts(): Flow<List<StoreProduct>> = flowOf(emptyList())
}
