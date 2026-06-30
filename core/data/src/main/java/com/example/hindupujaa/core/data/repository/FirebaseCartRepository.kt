package com.example.hindupujaa.core.data.repository

import com.example.hindupujaa.core.domain.model.CartItem
import com.example.hindupujaa.core.domain.repository.CartRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseCartRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : CartRepository {

    private val cartCollection get() = auth.currentUser?.let { 
        firestore.collection("users").document(it.uid).collection("cart")
    }

    override fun getCartItems(): Flow<List<CartItem>> = callbackFlow {
        val collection = cartCollection
        if (collection == null) {
            trySend(emptyList())
            return@callbackFlow
        }

        val listener = collection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val items = snapshot?.toObjects(CartItem::class.java) ?: emptyList()
            trySend(items)
        }
        awaitClose { listener.remove() }
    }

    override suspend fun addToCart(item: CartItem) {
        cartCollection?.document(item.pujaId)?.set(item)?.await()
    }

    override suspend fun removeFromCart(pujaId: String) {
        cartCollection?.document(pujaId)?.delete()?.await()
    }

    override suspend fun clearCart() {
        cartCollection?.get()?.await()?.documents?.forEach { 
            it.reference.delete()
        }
    }
}
