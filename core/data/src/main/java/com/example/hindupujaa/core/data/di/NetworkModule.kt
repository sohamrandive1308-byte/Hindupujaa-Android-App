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

import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import com.example.hindupujaa.core.common.util.TokenManager

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(tokenManager: TokenManager): HttpClient {
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
            install(Auth) {
                bearer {
                    loadTokens {
                        tokenManager.getAccessToken()?.let { BearerTokens(it, tokenManager.getRefreshToken() ?: "") }
                    }
                    refreshTokens {
                        // Implement token refresh logic here
                        null
                    }
                }
            }
            defaultRequest {
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
