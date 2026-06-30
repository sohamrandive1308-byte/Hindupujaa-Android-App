package com.example.hindupujaa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.hindupujaa.core.common.navigation.Screen
import com.example.hindupujaa.core.ui.theme.HinduPujaaTheme
import com.example.hindupujaa.feature.home.presentation.HomeScreen
import com.example.hindupujaa.feature.puja_detail.presentation.PujaDetailScreen
import com.example.hindupujaa.feature.auth.presentation.LoginScreen
import com.example.hindupujaa.feature.auth.presentation.SplashScreen
import com.example.hindupujaa.feature.auth.presentation.OnboardingScreen
import com.example.hindupujaa.feature.auth.presentation.ProfileSetupScreen
import com.example.hindupujaa.feature.cart.presentation.CartScreen
import com.example.hindupujaa.feature.profile.presentation.ProfileScreen
import com.example.hindupujaa.feature.checkout.presentation.CheckoutScreen
import com.example.hindupujaa.feature.kit_builder.presentation.KitBuilderScreen
import com.example.hindupujaa.feature.store.presentation.StoreScreen
import com.example.hindupujaa.feature.orders.presentation.OrdersScreen
import com.example.hindupujaa.feature.catering.presentation.CateringScreen
import com.razorpay.PaymentResultListener
import dagger.hilt.android.AndroidEntryPoint

import androidx.compose.animation.*

import com.example.hindupujaa.feature.checkout.presentation.OrderSuccessScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity(), PaymentResultListener {
    
    private var onPaymentSuccess: ((String) -> Unit)? = null
    private var onPaymentError: ((Int, String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HinduPujaaTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val showBottomBar = currentDestination?.route?.let { route ->
                    route.contains("Home") || route.contains("Store") || route.contains("Profile") || route.contains("Orders")
                } ?: false

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar {
                                NavigationBarItem(
                                    icon = { 
                                        val isSelected = currentDestination?.hierarchy?.any { it.hasRoute<Screen.Home>() } == true
                                        Icon(if (isSelected) Icons.Default.Home else Icons.Outlined.Home, contentDescription = "Home Tab") 
                                    },
                                    label = { Text("Home") },
                                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Screen.Home>() } == true,
                                    onClick = {
                                        navController.navigate(Screen.Home) {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = { 
                                        val isSelected = currentDestination?.hierarchy?.any { it.hasRoute<Screen.Store>() } == true
                                        Icon(if (isSelected) Icons.Default.ShoppingCart else Icons.Outlined.ShoppingCart, contentDescription = "Store Tab") 
                                    },
                                    label = { Text("Store") },
                                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Screen.Store>() } == true,
                                    onClick = {
                                        navController.navigate(Screen.Store) {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = { 
                                        val isSelected = currentDestination?.hierarchy?.any { it.hasRoute<Screen.Orders>() } == true
                                        Icon(if (isSelected) Icons.Default.List else Icons.Outlined.List, contentDescription = "Orders Tab") 
                                    },
                                    label = { Text("Orders") },
                                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Screen.Orders>() } == true,
                                    onClick = {
                                        navController.navigate(Screen.Orders) {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = { 
                                        val isSelected = currentDestination?.hierarchy?.any { it.hasRoute<Screen.Profile>() } == true
                                        Icon(if (isSelected) Icons.Default.Person else Icons.Outlined.Person, contentDescription = "Profile Tab") 
                                    },
                                    label = { Text("Profile") },
                                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Screen.Profile>() } == true,
                                    onClick = {
                                        navController.navigate(Screen.Profile) {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Splash,
                        modifier = Modifier.padding(innerPadding),
                        enterTransition = { fadeIn() + slideInHorizontally { it } },
                        exitTransition = { fadeOut() + slideOutHorizontally { -it } },
                        popEnterTransition = { fadeIn() + slideInHorizontally { -it } },
                        popExitTransition = { fadeOut() + slideOutHorizontally { it } }
                    ) {
                        composable<Screen.Splash> {
                            SplashScreen(
                                onNavigateNext = { isLoggedIn ->
                                    if (isLoggedIn) {
                                        navController.navigate(Screen.Home) {
                                            popUpTo(Screen.Splash) { inclusive = true }
                                        }
                                    } else {
                                        navController.navigate(Screen.Onboarding) {
                                            popUpTo(Screen.Splash) { inclusive = true }
                                        }
                                    }
                                }
                            )
                        }

                        composable<Screen.Onboarding> {
                            OnboardingScreen(
                                onFinish = {
                                    navController.navigate(Screen.Auth) {
                                        popUpTo(Screen.Onboarding) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable<Screen.Auth> {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate(Screen.ProfileSetup) {
                                        popUpTo(Screen.Auth) { inclusive = true }
                                    }
                                },
                                onSignUpClick = { /* TODO */ }
                            )
                        }

                        composable<Screen.ProfileSetup> {
                            ProfileSetupScreen(
                                onComplete = {
                                    navController.navigate(Screen.Home) {
                                        popUpTo(Screen.ProfileSetup) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable<Screen.Home> {
                            HomeScreen(
                                onPujaClick = { id ->
                                    navController.navigate(Screen.PujaDetail(id))
                                },
                                onCategoryClick = { id ->
                                    // navController.navigate(Screen.CategoryDetail(id))
                                },
                                onCateringClick = {
                                    navController.navigate(Screen.Catering)
                                },
                                onCartClick = {
                                    navController.navigate(Screen.Cart)
                                },
                                onProfileClick = {
                                    navController.navigate(Screen.Profile)
                                }
                            )
                        }
                        
                        composable<Screen.PujaDetail> { backStackEntry ->
                            val pujaDetail: Screen.PujaDetail = backStackEntry.toRoute()
                            PujaDetailScreen(
                                pujaId = pujaDetail.pujaId,
                                onBackClick = { navController.popBackStack() },
                                onBookNowClick = { id ->
                                    navController.navigate(Screen.KitBuilder(id))
                                }
                            )
                        }

                        composable<Screen.KitBuilder> { backStackEntry ->
                            val kitBuilder: Screen.KitBuilder = backStackEntry.toRoute()
                            KitBuilderScreen(
                                pujaId = kitBuilder.pujaId,
                                onBackClick = { navController.popBackStack() },
                                onProceedClick = { id, items ->
                                    navController.navigate(Screen.Checkout)
                                }
                            )
                        }

                        composable<Screen.Store> {
                            StoreScreen(
                                onProductClick = { /* TODO */ },
                                onCartClick = { navController.navigate(Screen.Cart) }
                            )
                        }

                        composable<Screen.Orders> {
                            OrdersScreen(
                                onOrderClick = { id ->
                                    // navController.navigate(Screen.OrderDetail(id))
                                }
                            )
                        }

                        composable<Screen.Cart> {
                            CartScreen(
                                onCheckoutClick = {
                                    navController.navigate(Screen.Checkout)
                                },
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        composable<Screen.Checkout> {
                            CheckoutScreen(
                                onOrderSuccess = { orderId ->
                                    navController.navigate(Screen.OrderSuccess(orderId)) {
                                        popUpTo(Screen.Home)
                                    }
                                },
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        composable<Screen.OrderSuccess> { backStackEntry ->
                            val orderSuccess: Screen.OrderSuccess = backStackEntry.toRoute()
                            OrderSuccessScreen(
                                orderId = orderSuccess.orderId,
                                onViewOrderClick = {
                                    // Navigate to WhatsApp
                                    val url = "https://wa.me/919175799251"
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    startActivity(intent)
                                },
                                onBackHomeClick = {
                                    navController.navigate(Screen.Home) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable<Screen.Profile> {
                            ProfileScreen(
                                onLogout = {
                                    navController.navigate(Screen.Auth) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable<Screen.Catering> {
                            CateringScreen(
                                onBackClick = { navController.popBackStack() },
                                onDiscussClick = {
                                    val url = "https://wa.me/919175799251"
                                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                                },
                                onCallClick = {
                                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919175799251"))
                                    startActivity(intent)
                                }
                            )
                        }
                        
                        composable<Screen.CategoryDetail> { backStackEntry ->
                            // val categoryDetail: Screen.CategoryDetail = backStackEntry.toRoute()
                        }
                    }
                }
            }
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        onPaymentSuccess?.invoke(razorpayPaymentId ?: "")
    }

    override fun onPaymentError(code: Int, response: String?) {
        onPaymentError?.invoke(code, response ?: "")
    }
}
