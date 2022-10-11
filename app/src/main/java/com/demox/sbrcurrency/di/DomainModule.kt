package com.demox.sbrcurrency.di

import com.demox.currency.repository.CurrencyRepository
import com.demox.currency.usecase.GetCurrencyListUseCase
import com.demox.currency.usecase.SaveCurrencyListUseCase
import com.demox.user_settings.repository.SettingsRepository
import com.demox.user_settings.usecase.GetCurrencySettingsUseCase
import com.demox.user_settings.usecase.SaveCurrencySettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providesGetCurrencyListUseCase(currencyRepository: CurrencyRepository): GetCurrencyListUseCase = GetCurrencyListUseCase(currencyRepository = currencyRepository)

    @Provides
    fun providesSaveCurrencyListUseCase(currencyRepository: CurrencyRepository): SaveCurrencyListUseCase = SaveCurrencyListUseCase(currencyRepository = currencyRepository)

    @Provides
    fun providesGetCurrencySettingsUseCase(settingsRepository: SettingsRepository): GetCurrencySettingsUseCase = GetCurrencySettingsUseCase(settingsRepository = settingsRepository)

    @Provides
    fun providesSaveCurrencySettingsUseCase(settingsRepository: SettingsRepository): SaveCurrencySettingsUseCase = SaveCurrencySettingsUseCase(settingsRepository = settingsRepository)
}
