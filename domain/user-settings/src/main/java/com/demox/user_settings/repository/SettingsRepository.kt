package com.demox.user_settings.repository

import com.demox.user_settings.model.CurrencySetting

interface SettingsRepository {

    fun getSettings(): CurrencySetting
    fun saveSettings(setting: CurrencySetting)
}
