package com.example.hindupujaa.core.domain.repository

import com.example.hindupujaa.core.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getOrders(): Flow<List<Order>>
    suspend fun createOrder(order: Order): String
    fun getOrderById(orderId: String): Flow<Order?>
}
