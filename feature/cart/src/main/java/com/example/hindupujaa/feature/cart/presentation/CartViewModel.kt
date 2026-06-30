package com.example.hindupujaa.feature.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindupujaa.core.domain.model.CartItem
import com.example.hindupujaa.core.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val totalPrice: Double get() = items.sumOf { it.price * it.quantity }
}

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        loadCart()
    }

    private fun loadCart() {
        _uiState.update { it.copy(isLoading = true) }
        cartRepository.getCartItems()
            .onEach { items ->
                _uiState.update { it.copy(items = items, isLoading = false) }
            }
            .catch { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
            .launchIn(viewModelScope)
    }

    fun removeFromCart(pujaId: String) {
        viewModelScope.launch {
            cartRepository.removeFromCart(pujaId)
        }
    }
}
