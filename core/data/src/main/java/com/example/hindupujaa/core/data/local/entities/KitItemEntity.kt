package com.example.hindupujaa.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hindupujaa.core.domain.model.KitItem

@Entity(tableName = "kit_items")
data class KitItemEntity(
    @PrimaryKey val id: String,
    val pujaId: String,
    val nameEn: String,
    val nameMr: String,
    val description: String,
    val itemType: String,
    val price: Double,
    val isDefaultSelected: Boolean,
    val isPerishable: Boolean,
    val imagePath: String,
    val sortOrder: Int,
    val isActive: Boolean
)

fun KitItemEntity.toDomain() = KitItem(
    id = id,
    nameEn = nameEn,
    nameMr = nameMr,
    description = description,
    itemType = itemType,
    price = price,
    isDefaultSelected = isDefaultSelected,
    isPerishable = isPerishable,
    imagePath = imagePath,
    sortOrder = sortOrder,
    isActive = isActive
)

fun KitItem.toEntity(pujaId: String) = KitItemEntity(
    id = id,
    pujaId = pujaId,
    nameEn = nameEn,
    nameMr = nameMr,
    description = description,
    itemType = itemType,
    price = price,
    isDefaultSelected = isDefaultSelected,
    isPerishable = isPerishable,
    imagePath = imagePath,
    sortOrder = sortOrder,
    isActive = isActive
)
