package com.example.hindupujaa.feature.kit_builder.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindupujaa.core.domain.model.CartItem
import com.example.hindupujaa.core.domain.model.KitItem
import com.example.hindupujaa.core.domain.model.Puja
import com.example.hindupujaa.core.domain.repository.CartRepository
import com.example.hindupujaa.core.domain.repository.PujaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class KitBuilderUiState(
    val puja: Puja? = null,
    val items: List<KitItem> = emptyList(),
    val selectionMap: Map<String, Boolean> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val totalPrice: Double get() = items.filter { selectionMap[it.id] == true }.sumOf { it.price }
    val selectedCount: Int get() = selectionMap.values.count { it }
}

@HiltViewModel
class KitBuilderViewModel @Inject constructor(
    private val pujaRepository: PujaRepository,
    private val cartRepository: CartRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pujaId: String = checkNotNull(savedStateHandle["pujaId"])

    private val _uiState = MutableStateFlow(KitBuilderUiState())
    val uiState: StateFlow<KitBuilderUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.update { it.copy(isLoading = true) }
        
        viewModelScope.launch {
            pujaRepository.getPujaById(pujaId).collect { puja ->
                _uiState.update { it.copy(puja = puja) }
            }
        }

        viewModelScope.launch {
            pujaRepository.getKitItems(pujaId).collect { items ->
                val initialMap = items.associate { it.id to it.isDefaultSelected }
                _uiState.update { it.copy(items = items, selectionMap = initialMap, isLoading = false) }
            }
        }
    }

    fun toggleSelection(itemId: String) {
        _uiState.update { state ->
            val current = state.selectionMap[itemId] ?: false
            state.copy(selectionMap = state.selectionMap + (itemId to !current))
        }
    }

    fun selectAll(select: Boolean) {
        _uiState.update { state ->
            val newMap = state.items.associate { it.id to select }
            state.copy(selectionMap = newMap)
        }
    }

    fun proceedToCheckout(onReady: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val puja = uiState.value.puja ?: return@launch
            
            cartRepository.addToCart(
                CartItem(
                    pujaId = puja.id,
                    pujaName = puja.nameEn,
                    price = uiState.value.totalPrice,
                    imageUrl = puja.heroImagePath,
                    quantity = 1
                )
            )
            _uiState.update { it.copy(isLoading = false) }
            onReady()
        }
    }
}
