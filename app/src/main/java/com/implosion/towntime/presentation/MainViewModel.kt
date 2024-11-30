package com.implosion.towntime.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.implosion.towntime.domain.CapitalModel
import com.implosion.towntime.domain.repository.CapitalRepository
import com.implosion.towntime.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    val capitalRepository: CapitalRepository,
    val timeRepository: TimeRepository,
) : ViewModel() {

    private val _capitalMutableState = mutableStateOf<List<CapitalModel>>(emptyList())
    val capitalMutableState = _capitalMutableState

    private val _selectedCapital = MutableStateFlow<String>("")
    val selectedCapital: StateFlow<String> get() = _selectedCapital.asStateFlow()

    val timeFlow: Flow<String> = timeRepository
        .getUpdatedTime()
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Loading..."
        )

    init {
        getLastChooseCapital()
        loadCapitals()
    }

    fun selectCapital(capital: CapitalModel) = viewModelScope.launch {
        capitalRepository.saveCapital(capital)
        _selectedCapital.emit(
            capitalRepository
                .getLastCapital().name
        )
    }

    private fun getLastChooseCapital() = viewModelScope.launch {
        _selectedCapital.emit(
            capitalRepository
                .getLastCapital().name
        )
    }

    private fun loadCapitals() = viewModelScope.launch {
        try {
            val capitals = capitalRepository.getAllCapitals()
            _capitalMutableState.value = capitals


        } catch (e: Exception) {

        }
    }
}