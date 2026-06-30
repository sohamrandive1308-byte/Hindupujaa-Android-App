package com.example.hindupujaa.core.data.api

import com.example.hindupujaa.core.domain.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class HinduPujaaApi(private val client: HttpClient) {
    
    suspend fun getPujas(): List<Puja> = client.get("pujas").body()
    
    suspend fun getPujaDetail(slug: String): Puja = client.get("pujas/$slug").body()
    
    suspend fun getKitItems(pujaId: String): List<KitItem> = client.get("pujas/$pujaId/kit_items").body()
    
    suspend fun getStoreProducts(): List<StoreProduct> = client.get("store/products").body()
    
    suspend fun createOrder(order: Order): Order = client.post("orders") {
        setBody(order)
    }.body()
    
    // Add other endpoints as per Section 5 of PRD v2.0.0
}
