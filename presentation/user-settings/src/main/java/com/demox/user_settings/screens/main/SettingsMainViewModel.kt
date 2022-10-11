package com.demox.user_settings.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demox.user_settings.model.CurrencySetting
import com.demox.user_settings.usecase.GetCurrencySettingsUseCase
import com.demox.user_settings.usecase.SaveCurrencySettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsMainViewModel @Inject constructor(
    private val getCurrencySettings: GetCurrencySettingsUseCase,
    private val saveCurrencySettings: SaveCurrencySettingsUseCase
) : ViewModel() {

    private val _settingState = MutableStateFlow(false)
    val settingState = _settingState.asStateFlow()
    private val _settingValue = MutableStateFlow("")
    val settingValue = _settingValue.asStateFlow()
    private val _warningState = MutableStateFlow(false)
    val warningState = _warningState.asStateFlow()

    private val _currencySetting = MutableStateFlow<CurrencySetting>(CurrencySetting())
    val currencySetting = _currencySetting.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _currencySetting.value = getCurrencySettings.getCurrencySettings()
            _settingState.value = _currencySetting.value.active
            _settingValue.value = _currencySetting.value.value.toString()
        }
    }

    fun changeValue(value:String){
        _settingValue.value = value
    }

    fun changeSettingState() {
        _settingState.value = settingState.value.not()
    }

    fun changeWarningState(){
        _warningState.value = warningState.value.not()
    }

    fun saveSetting(active:Boolean,value:Double){
        saveCurrencySettings.saveSettings(CurrencySetting(
            active = active,
            value = value
        ))
    }
}
