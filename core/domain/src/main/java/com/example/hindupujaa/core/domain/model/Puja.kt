package com.example.hindupujaa.core.domain.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class Puja(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("name_en") @set:PropertyName("name_en") var nameEn: String = "",
    @get:PropertyName("name_hi") @set:PropertyName("name_hi") var nameHi: String = "",
    @get:PropertyName("slug") @set:PropertyName("slug") var slug: String = "",
    @get:PropertyName("category") @set:PropertyName("category") var category: String = "",
    @get:PropertyName("duration_label") @set:PropertyName("duration_label") var durationLabel: String = "",
    @get:PropertyName("is_trending") @set:PropertyName("is_trending") var isTrending: Boolean = false,
    @get:PropertyName("hero_image_path") @set:PropertyName("hero_image_path") var heroImagePath: String = "",
    @get:PropertyName("thumbnail_path") @set:PropertyName("thumbnail_path") var thumbnailPath: String = "",
    @get:PropertyName("backstory") @set:PropertyName("backstory") var backstory: String = "",
    @get:PropertyName("benefits") @set:PropertyName("benefits") var benefits: List<String> = emptyList(),
    @get:PropertyName("who_can_perform") @set:PropertyName("who_can_perform") var whoCanPerform: List<String> = emptyList(),
    @get:PropertyName("when_to_perform") @set:PropertyName("when_to_perform") var whenToPerform: List<String> = emptyList(),
    @get:PropertyName("vidhi_steps") @set:PropertyName("vidhi_steps") var vidhiSteps: List<String> = emptyList(),
    @get:PropertyName("aarti_text") @set:PropertyName("aarti_text") var aartiText: String = "",
    @get:PropertyName("gallery_paths") @set:PropertyName("gallery_paths") var galleryPaths: List<String> = emptyList(),
    @get:PropertyName("base_price") @set:PropertyName("base_price") var basePrice: Double = 0.0,
    @get:PropertyName("is_active") @set:PropertyName("is_active") var isActive: Boolean = true,
    @get:PropertyName("sort_order") @set:PropertyName("sort_order") var sortOrder: Int = 0,
    @get:PropertyName("created_at") @set:PropertyName("created_at") var createdAt: Long = 0,
    @get:PropertyName("updated_at") @set:PropertyName("updated_at") var updatedAt: Long = 0
)
