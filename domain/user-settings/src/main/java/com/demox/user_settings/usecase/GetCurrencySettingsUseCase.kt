package com.demox.user_settings.usecase

import com.demox.user_settings.repository.SettingsRepository

class GetCurrencySettingsUseCase(private val settingsRepository: SettingsRepository) {

    fun getCurrencySettings() = settingsRepository.getSettings()
}
