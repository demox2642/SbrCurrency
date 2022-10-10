package com.demox.currency.repository

import com.demox.currency.model.Curency
import com.demox.currency.services.CurrencyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService
) : CurrencyRepository {

    override suspend fun getCurrency(): Flow<List<Curency>> {
        val content = currencyService.getCurrency().body()
        return flow {
            emit(
                content!!.currencyList!!.map {
                    Curency(
                        data = it.data.toString(),
                        value = it.value!!.replace(",", ".").toDouble()
                    )
                }
            )
        }.flowOn(Dispatchers.IO)
    }
}
