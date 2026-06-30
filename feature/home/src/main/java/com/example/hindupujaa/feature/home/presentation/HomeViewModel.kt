package com.example.hindupujaa.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindupujaa.core.domain.model.Category
import com.example.hindupujaa.core.domain.model.Puja
import com.example.hindupujaa.core.domain.repository.PujaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class HomeUiState(
    val categories: List<Category> = emptyList(),
    val trendingPujas: List<Puja> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pujaRepository: PujaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        _uiState.update { it.copy(isLoading = true) }
        
        combine(
            pujaRepository.getCategories(),
            pujaRepository.getPujas()
        ) { categories, pujas ->
            HomeUiState(
                categories = categories,
                trendingPujas = pujas.take(5), // Simple trending logic
                isLoading = false
            )
        }.onEach { newState ->
            _uiState.value = newState
        }.catch { e ->
            _uiState.update { it.copy(isLoading = false, error = e.message) }
        }.launchIn(viewModelScope)
    }
}
