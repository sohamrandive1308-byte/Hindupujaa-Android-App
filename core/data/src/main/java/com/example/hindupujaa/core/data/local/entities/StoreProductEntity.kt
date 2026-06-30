package com.example.hindupujaa.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hindupujaa.core.domain.model.StoreProduct

@Entity(tableName = "store_products")
data class StoreProductEntity(
    @PrimaryKey val id: String = "",
    val nameEn: String = "",
    val category: String = "",
    val unitLabel: String = "",
    val mrp: Double = 0.0,
    val price: Double = 0.0,
    val discountPct: Int = 0,
    val badge: String = "",
    val imagePath: String = "",
    val stock: Int = 0,
    val isActive: Boolean = true,
    val sortOrder: Int = 0,
    val description: String = ""
)

fun StoreProductEntity.toDomain() = StoreProduct(
    id = id,
    nameEn = nameEn,
    category = category,
    unitLabel = unitLabel,
    mrp = mrp,
    price = price,
    discountPct = discountPct,
    badge = badge,
    imagePath = imagePath,
    stock = stock,
    isActive = isActive,
    sortOrder = sortOrder,
    description = description
)

fun StoreProduct.toEntity() = StoreProductEntity(
    id = id,
    nameEn = nameEn,
    category = category,
    unitLabel = unitLabel,
    mrp = mrp,
    price = price,
    discountPct = discountPct,
    badge = badge,
    imagePath = imagePath,
    stock = stock,
    isActive = isActive,
    sortOrder = sortOrder,
    description = description
)
