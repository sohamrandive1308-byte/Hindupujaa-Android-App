package com.example.hindupujaa.core.data.di

import android.content.Context
import androidx.room.Room
import com.example.hindupujaa.core.data.local.HinduPujaaDatabase
import com.example.hindupujaa.core.data.local.dao.PujaDao
import com.example.hindupujaa.core.data.local.dao.StoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HinduPujaaDatabase {
        return Room.databaseBuilder(
            context,
            HinduPujaaDatabase::class.java,
            "hindupujaa.db"
        ).build()
    }

    @Provides
    fun providePujaDao(db: HinduPujaaDatabase): PujaDao = db.pujaDao()

    @Provides
    fun provideStoreDao(db: HinduPujaaDatabase): StoreDao = db.storeDao()
}
