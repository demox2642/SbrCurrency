package com.demox.currency.usecase

import com.demox.currency.model.Curency
import com.demox.currency.repository.CurrencyRepository

class SaveCurrencyListUseCase(private val currencyRepository: CurrencyRepository) {

    suspend fun saveCurrencyList(currencyList: List<Curency>) = currencyRepository.saveCurrency(currencyList)
}
