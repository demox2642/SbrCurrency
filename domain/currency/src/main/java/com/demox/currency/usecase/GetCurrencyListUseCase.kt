package com.demox.currency.usecase

import com.demox.currency.model.Curency
import com.demox.currency.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCurrencyListUseCase(private val currencyRepository: CurrencyRepository) {
    suspend fun getCurrencyList(): Flow<List<Curency>> = currencyRepository.getCurrency()
}
