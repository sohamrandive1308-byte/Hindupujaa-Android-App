package com.example.hindupujaa.core.data.di

import com.example.hindupujaa.core.data.api.HinduPujaaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) {
                level = LogLevel.INFO
            }
            defaultRequest {
                // REPLACE with real base URL from Section 6.2
                url("https://api.hindupujaa.com/")
            }
        }
    }

    @Provides
    @Singleton
    fun provideHinduPujaaApi(client: HttpClient): HinduPujaaApi {
        return HinduPujaaApi(client)
    }
}
