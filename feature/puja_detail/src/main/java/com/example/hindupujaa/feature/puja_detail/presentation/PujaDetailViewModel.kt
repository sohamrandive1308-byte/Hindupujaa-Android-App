package com.example.hindupujaa.feature.puja_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindupujaa.core.domain.model.CartItem
import com.example.hindupujaa.core.domain.model.Puja
import com.example.hindupujaa.core.domain.repository.CartRepository
import com.example.hindupujaa.core.domain.repository.PujaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PujaDetailUiState(
    val puja: Puja? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAddedToCart: Boolean = false
)

@HiltViewModel
class PujaDetailViewModel @Inject constructor(
    private val pujaRepository: PujaRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PujaDetailUiState())
    val uiState: StateFlow<PujaDetailUiState> = _uiState.asStateFlow()

    fun loadPujaDetail(pujaId: String) {
        _uiState.update { it.copy(isLoading = true) }
        pujaRepository.getPujaById(pujaId)
            .onEach { puja ->
                _uiState.update { it.copy(puja = puja, isLoading = false) }
            }
            .catch { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
            .launchIn(viewModelScope)
    }

    fun addToCart() {
        val puja = uiState.value.puja ?: return
        viewModelScope.launch {
            cartRepository.addToCart(
                CartItem(
                    pujaId = puja.id,
                    pujaName = puja.nameEn,
                    price = puja.basePrice,
                    imageUrl = puja.heroImagePath
                )
            )
            _uiState.update { it.copy(isAddedToCart = true) }
        }
    }
}
