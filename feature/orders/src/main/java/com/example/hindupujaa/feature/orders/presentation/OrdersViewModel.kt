package com.example.hindupujaa.feature.orders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindupujaa.core.domain.model.Order
import com.example.hindupujaa.core.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class OrdersUiState(
    val activeOrders: List<Order> = emptyList(),
    val pastOrders: List<Order> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrdersUiState())
    val uiState: StateFlow<OrdersUiState> = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        _uiState.update { it.copy(isLoading = true) }
        
        orderRepository.getOrders()
            .onEach { orders ->
                val active = orders.filter { it.orderStatus != "delivered" && it.orderStatus != "cancelled" }
                val past = orders.filter { it.orderStatus == "delivered" || it.orderStatus == "cancelled" }
                _uiState.update { it.copy(activeOrders = active, pastOrders = past, isLoading = false) }
            }
            .catch { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
            .launchIn(viewModelScope)
    }
}
