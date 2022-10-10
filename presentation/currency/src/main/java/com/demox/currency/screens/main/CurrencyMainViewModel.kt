package com.demox.currency.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demox.currency.usecase.GetCurrencyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyMainViewModel @Inject constructor(
    private val getCurrencyListUseCase: GetCurrencyListUseCase
) : ViewModel() {

    private val _test = MutableStateFlow("")
    val teste = _test.asStateFlow()

    init {
        Log.e("CurrencyMainViewModel", "init")
        viewModelScope.launch {
            try {
                getCurrencyListUseCase.getCurrencyList()
                    .collectLatest {
                        Log.e("CurrencyMainViewModel", "test=$it")
                    }
            } catch (e: Throwable) {
                Log.e("CurrencyMainViewModel", "e=$e")
            }
        }
    }
}
