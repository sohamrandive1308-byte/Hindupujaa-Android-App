package com.example.hindupujaa.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val pujaId: String,
    val pujaName: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int = 1
)
