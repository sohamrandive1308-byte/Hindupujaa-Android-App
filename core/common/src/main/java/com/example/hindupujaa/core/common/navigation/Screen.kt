package com.example.hindupujaa.core.common.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Splash : Screen
    
    @Serializable
    data object Onboarding : Screen
    
    @Serializable
    data object Home : Screen
    
    @Serializable
    data class PujaDetail(val pujaId: String) : Screen
    
    @Serializable
    data class KitBuilder(val pujaId: String) : Screen
    
    @Serializable
    data class CategoryDetail(val categoryId: String) : Screen
    
    @Serializable
    data object Cart : Screen
    
    @Serializable
    data object Checkout : Screen

    @Serializable
    data class OrderSuccess(val orderId: String) : Screen
    
    @Serializable
    data object Profile : Screen
    
    @Serializable
    data object Auth : Screen

    @Serializable
    data object ProfileSetup : Screen

    @Serializable
    data object Store : Screen

    @Serializable
    data object Orders : Screen

    @Serializable
    data object Catering : Screen
}
