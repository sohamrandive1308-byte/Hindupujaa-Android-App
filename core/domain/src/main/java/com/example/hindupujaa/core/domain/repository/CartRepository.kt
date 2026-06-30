package com.example.hindupujaa.core.domain.repository

import com.example.hindupujaa.core.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addToCart(item: CartItem)
    suspend fun removeFromCart(pujaId: String)
    suspend fun clearCart()
}
