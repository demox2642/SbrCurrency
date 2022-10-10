package com.demox.user_settings.repository

import com.demox.shared_pref.storage.UserRepositoryConst
import com.demox.shared_pref.storage.UserStorage
import com.demox.shared_pref.storage.getFromJson
import com.demox.shared_pref.storage.setAsJson
import com.demox.user_settings.model.CurrencySetting
import javax.inject.Inject

class UserSettingRepositoryImpl @Inject constructor(private val userStorage: UserStorage) : SettingsRepository {
    override fun getSettings(): CurrencySetting {
        return userStorage.getFromJson<CurrencySetting>(UserRepositoryConst.SHAREDPREF) ?: CurrencySetting()
    }

    override fun saveSettings(setting: CurrencySetting) {
        userStorage.setAsJson(UserRepositoryConst.SHAREDPREF, setting)
    }
}
