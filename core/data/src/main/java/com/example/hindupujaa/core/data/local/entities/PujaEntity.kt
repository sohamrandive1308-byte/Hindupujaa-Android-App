package com.example.hindupujaa.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hindupujaa.core.domain.model.Puja

@Entity(tableName = "pujas")
data class PujaEntity(
    @PrimaryKey val id: String,
    val nameEn: String,
    val nameHi: String,
    val slug: String,
    val category: String,
    val durationLabel: String,
    val isTrending: Boolean,
    val heroImagePath: String,
    val thumbnailPath: String,
    val backstory: String,
    val benefits: List<String>,
    val basePrice: Double,
    val isActive: Boolean,
    val sortOrder: Int
)

fun PujaEntity.toDomain() = Puja(
    id = id,
    nameEn = nameEn,
    nameHi = nameHi,
    slug = slug,
    category = category,
    durationLabel = durationLabel,
    isTrending = isTrending,
    heroImagePath = heroImagePath,
    thumbnailPath = thumbnailPath,
    backstory = backstory,
    benefits = benefits,
    basePrice = basePrice,
    isActive = isActive,
    sortOrder = sortOrder
)

fun Puja.toEntity() = PujaEntity(
    id = id,
    nameEn = nameEn,
    nameHi = nameHi,
    slug = slug,
    category = category,
    durationLabel = durationLabel,
    isTrending = isTrending,
    heroImagePath = heroImagePath,
    thumbnailPath = thumbnailPath,
    backstory = backstory,
    benefits = benefits,
    basePrice = basePrice,
    isActive = isActive,
    sortOrder = sortOrder
)
