package com.demox.currency.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demox.currency.model.Curency
import com.demox.currency.usecase.GetCurrencyListUseCase
import com.demox.currency.usecase.SaveCurrencyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyMainViewModel @Inject constructor(
    private val getCurrencyListUseCase: GetCurrencyListUseCase,
    private val saveCurrencyListUseCase: SaveCurrencyListUseCase
) : ViewModel() {

    private val _currencyList = MutableStateFlow<List<Curency>>(emptyList())
    val currencyList = _currencyList.asStateFlow()
    private val _loadState = MutableStateFlow(false)
    val loadState = _loadState.asStateFlow()

    private val _errorDialogState = MutableStateFlow(false)
    val errorDialogState = _errorDialogState.asStateFlow()
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _loadState.value = true
                getCurrencyListUseCase.getCurrencyList()
                    .collectLatest {
                        _currencyList.value = it
                        saveCurrencyListUseCase.saveCurrencyList(it)
                    }
            } catch (e: Throwable) {
                _errorDialogState.value = true
                _errorMessage.value = e.toString()
            } finally {
                _loadState.value = false
            }
        }
    }
}
