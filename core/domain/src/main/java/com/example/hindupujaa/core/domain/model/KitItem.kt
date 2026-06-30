package com.example.hindupujaa.core.domain.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class KitItem(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("name_en") @set:PropertyName("name_en") var nameEn: String = "",
    @get:PropertyName("name_mr") @set:PropertyName("name_mr") var nameMr: String = "",
    @get:PropertyName("description") @set:PropertyName("description") var description: String = "",
    @get:PropertyName("item_type") @set:PropertyName("item_type") var itemType: String = "included", // "included" | "rental"
    @get:PropertyName("price") @set:PropertyName("price") var price: Double = 0.0,
    @get:PropertyName("is_default_selected") @set:PropertyName("is_default_selected") var isDefaultSelected: Boolean = true,
    @get:PropertyName("is_perishable") @set:PropertyName("is_perishable") var isPerishable: Boolean = false,
    @get:PropertyName("image_path") @set:PropertyName("image_path") var imagePath: String = "",
    @get:PropertyName("sort_order") @set:PropertyName("sort_order") var sortOrder: Int = 0,
    @get:PropertyName("is_active") @set:PropertyName("is_active") var isActive: Boolean = true
)
