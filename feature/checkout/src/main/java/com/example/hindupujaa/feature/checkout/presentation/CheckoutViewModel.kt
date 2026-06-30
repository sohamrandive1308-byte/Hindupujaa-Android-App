package com.example.hindupujaa.feature.checkout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindupujaa.core.domain.model.Address
import com.example.hindupujaa.core.domain.model.CartItem
import com.example.hindupujaa.core.domain.model.Order
import com.example.hindupujaa.core.domain.model.OrderItem
import com.example.hindupujaa.core.domain.repository.CartRepository
import com.example.hindupujaa.core.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CheckoutUiState(
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val isOrderPlaced: Boolean = false,
    val error: String? = null,
    val address: String = ""
) {
    val totalPrice: Double get() = cartItems.sumOf { it.price * it.quantity }
}

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckoutUiState())
    val uiState: StateFlow<CheckoutUiState> = _uiState.asStateFlow()

    init {
        loadCart()
    }

    private fun loadCart() {
        _uiState.update { it.copy(isLoading = true) }
        cartRepository.getCartItems()
            .take(1)
            .onEach { items ->
                _uiState.update { it.copy(cartItems = items, isLoading = false) }
            }
            .launchIn(viewModelScope)
    }

    fun onAddressChange(address: String) {
        _uiState.update { it.copy(address = address) }
    }

    fun placeOrder(paymentId: String) {
        val state = uiState.value
        val firstItem = state.cartItems.firstOrNull()
        
        val order = Order(
            pujaId = firstItem?.pujaId ?: "",
            pujaName = firstItem?.pujaName ?: "",
            totalAmount = state.totalPrice,
            deliveryAddress = Address(addressLine = state.address),
            razorpayPaymentId = paymentId,
            paymentStatus = "paid",
            selectedItems = state.cartItems.map { 
                OrderItem(id = it.pujaId, nameEn = it.pujaName, price = it.price) 
            }
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                orderRepository.createOrder(order)
                cartRepository.clearCart()
                _uiState.update { it.copy(isLoading = false, isOrderPlaced = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}
