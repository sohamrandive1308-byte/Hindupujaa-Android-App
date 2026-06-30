package com.example.hindupujaa.core.data.di

import com.example.hindupujaa.core.data.repository.FirebasePujaRepository
import com.example.hindupujaa.core.domain.repository.PujaRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.example.hindupujaa.core.data.repository.FirebaseCartRepository
import com.example.hindupujaa.core.domain.repository.CartRepository

import com.example.hindupujaa.core.data.repository.FirebaseOrderRepository
import com.example.hindupujaa.core.domain.repository.OrderRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindPujaRepository(
        firebasePujaRepository: FirebasePujaRepository
    ): PujaRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        firebaseCartRepository: FirebaseCartRepository
    ): CartRepository

    @Binds
    @Singleton
    abstract fun bindOrderRepository(
        firebaseOrderRepository: FirebaseOrderRepository
    ): OrderRepository

    companion object {
        @Provides
        @Singleton
        fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

        @Provides
        @Singleton
        fun provideAuth(): FirebaseAuth = FirebaseAuth.getInstance()

        @Provides
        @Singleton
        fun provideRealtimeDatabase(): FirebaseDatabase {
            return FirebaseDatabase.getInstance("https://com-example-hindupujaa-11d79-default-rtdb.asia-southeast1.firebasedatabase.app/")
        }
    }
}
