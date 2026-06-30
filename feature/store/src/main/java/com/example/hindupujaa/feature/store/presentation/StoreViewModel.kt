package com.example.hindupujaa.feature.store.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindupujaa.core.domain.model.CartItem
import com.example.hindupujaa.core.domain.model.StoreProduct
import com.example.hindupujaa.core.domain.repository.CartRepository
import com.example.hindupujaa.core.domain.repository.PujaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StoreUiState(
    val products: List<StoreProduct> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "All",
    val isLoading: Boolean = false,
    val cartItemCount: Int = 0,
    val error: String? = null
)

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val pujaRepository: PujaRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoreUiState())
    val uiState: StateFlow<StoreUiState> = _uiState.asStateFlow()

    init {
        loadStoreData()
        observeCart()
    }

    private fun observeCart() {
        cartRepository.getCartItems()
            .onEach { items ->
                _uiState.update { it.copy(cartItemCount = items.sumOf { it.quantity }) }
            }
            .launchIn(viewModelScope)
    }

    private fun loadStoreData() {
        _uiState.update { it.copy(isLoading = true) }
        
        pujaRepository.getStoreProducts()
            .onEach { products ->
                val categories = listOf("All") + products.map { it.category }.distinct()
                _uiState.update { it.copy(products = products, categories = categories, isLoading = false) }
            }
            .catch { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
            .launchIn(viewModelScope)
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun addToCart(product: StoreProduct) {
        viewModelScope.launch {
            cartRepository.addToCart(
                CartItem(
                    pujaId = product.id,
                    pujaName = product.nameEn,
                    price = product.price,
                    imageUrl = product.imagePath
                )
            )
        }
    }
}
