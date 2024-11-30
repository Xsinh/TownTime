package com.implosion.towntime.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.implosion.towntime.domain.CapitalModel
import com.implosion.towntime.domain.repository.CapitalRepository
import com.implosion.towntime.domain.repository.TimeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    val capitalRepository: CapitalRepository,
    val timeRepository: TimeRepository,
) : ViewModel() {

    private val _capitalMutableState = mutableStateOf<List<CapitalModel>>(emptyList())
    val capitalMutableState = _capitalMutableState

    private val _selectedCapital = MutableStateFlow<CapitalModel?>(null)
    val selectedCapital: StateFlow<CapitalModel?> get() = _selectedCapital.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val timeFlow: Flow<String> = _selectedCapital
        .flatMapLatest { capital ->
            capital.let {
                timeRepository.getUpdatedTime(it?.timeZone.orEmpty())
            }
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
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
                .getLastCapital()
        )
    }

    private fun getLastChooseCapital() = viewModelScope.launch {
        _selectedCapital.emit(
            capitalRepository
                .getLastCapital()
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