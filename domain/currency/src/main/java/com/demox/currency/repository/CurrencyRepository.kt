package com.demox.currency.repository

import com.demox.currency.model.Curency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun getCurrency(): Flow<List<Curency>>
    suspend fun saveCurrency(currencyList: List<Curency>)
}
