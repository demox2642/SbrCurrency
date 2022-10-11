package com.demox.user_settings.usecase

import com.demox.user_settings.model.CurrencySetting
import com.demox.user_settings.repository.SettingsRepository

class SaveCurrencySettingsUseCase(private val settingsRepository: SettingsRepository) {

    fun saveSettings(currencySetting: CurrencySetting) = settingsRepository.saveSettings(currencySetting)
}
