package com.demox.sbrcurrency.di

import com.demox.currency.repository.CurrencyRepository
import com.demox.currency.usecase.GetCurrencyListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providesGetCurrencyListUseCase(currencyRepository: CurrencyRepository): GetCurrencyListUseCase = GetCurrencyListUseCase(currencyRepository = currencyRepository)

}
