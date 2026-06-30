package com.example.hindupujaa.core.domain.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class StoreProduct(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("name_en") @set:PropertyName("name_en") var nameEn: String = "",
    @get:PropertyName("category") @set:PropertyName("category") var category: String = "",
    @get:PropertyName("unit_label") @set:PropertyName("unit_label") var unitLabel: String = "",
    @get:PropertyName("mrp") @set:PropertyName("mrp") var mrp: Double = 0.0,
    @get:PropertyName("price") @set:PropertyName("price") var price: Double = 0.0,
    @get:PropertyName("discount_pct") @set:PropertyName("discount_pct") var discountPct: Int = 0,
    @get:PropertyName("badge") @set:PropertyName("badge") var badge: String = "",
    @get:PropertyName("image_path") @set:PropertyName("image_path") var imagePath: String = "",
    @get:PropertyName("stock") @set:PropertyName("stock") var stock: Int = 0,
    @get:PropertyName("is_active") @set:PropertyName("is_active") var isActive: Boolean = true,
    @get:PropertyName("sort_order") @set:PropertyName("sort_order") var sortOrder: Int = 0
)
