package com.example.hindupujaa.core.data.repository

import com.example.hindupujaa.core.domain.model.Category
import com.example.hindupujaa.core.domain.model.KitItem
import com.example.hindupujaa.core.domain.model.Puja
import com.example.hindupujaa.core.domain.model.StoreProduct
import com.example.hindupujaa.core.domain.repository.PujaRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebasePujaRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : PujaRepository {

    override fun getPujas(): Flow<List<Puja>> {
        return firestore.collection("pujas")
            .snapshots()
            .map { snapshot ->
                snapshot.toObjects(Puja::class.java)
            }
    }

    override fun getCategories(): Flow<List<Category>> {
        return firestore.collection("categories")
            .snapshots()
            .map { snapshot ->
                snapshot.toObjects(Category::class.java)
            }
    }

    override fun getPujasByCategory(categoryId: String): Flow<List<Puja>> {
        return firestore.collection("pujas")
            .whereEqualTo("categoryId", categoryId)
            .snapshots()
            .map { snapshot ->
                snapshot.toObjects(Puja::class.java)
            }
    }

    override fun getPujaById(pujaId: String): Flow<Puja?> {
        return firestore.collection("pujas")
            .document(pujaId)
            .snapshots()
            .map { snapshot ->
                snapshot.toObject(Puja::class.java)
            }
    }

    override suspend fun searchPujas(query: String): List<Puja> {
        return firestore.collection("pujas")
            .whereGreaterThanOrEqualTo("name", query)
            .whereLessThanOrEqualTo("name", query + "\uf8ff")
            .get()
            .await()
            .toObjects(Puja::class.java)
    }

    override fun getKitItems(pujaId: String): Flow<List<KitItem>> {
        return firestore.collection("pujas")
            .document(pujaId)
            .collection("kit_items")
            .orderBy("sort_order")
            .snapshots()
            .map { snapshot ->
                snapshot.toObjects(KitItem::class.java)
            }
    }

    override fun getStoreProducts(): Flow<List<StoreProduct>> {
        return firestore.collection("store_products")
            .orderBy("sort_order")
            .snapshots()
            .map { snapshot ->
                snapshot.toObjects(StoreProduct::class.java)
            }
    }
}
