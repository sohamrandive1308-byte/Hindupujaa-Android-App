package com.example.hindupujaa.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hindupujaa.core.data.local.dao.PujaDao
import com.example.hindupujaa.core.data.local.dao.StoreDao
import com.example.hindupujaa.core.data.local.entities.KitItemEntity
import com.example.hindupujaa.core.data.local.entities.PujaEntity
import com.example.hindupujaa.core.data.local.entities.StoreProductEntity

@Database(
    entities = [
        PujaEntity::class,
        KitItemEntity::class,
        StoreProductEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class HinduPujaaDatabase : RoomDatabase() {
    abstract fun pujaDao(): PujaDao
    abstract fun storeDao(): StoreDao
}
