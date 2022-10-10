package com.demox.currency.repository

import com.demox.currency.model.Curency
import com.demox.currency.model.Setting
import com.demox.currency.services.CurrencyService
import com.demox.shared_pref.storage.UserRepositoryConst
import com.demox.shared_pref.storage.UserStorage
import com.demox.shared_pref.storage.getFromJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService,
    private val userStorage: UserStorage

) : CurrencyRepository {

    override suspend fun getCurrency(): Flow<List<Curency>> {
        val setting = userStorage.getFromJson<Setting>(UserRepositoryConst.SHAREDPREF) ?: Setting()
        val content = currencyService.getCurrency().body()
        return flow {
            emit(
                content!!.currencyList!!.map {
                    Curency(
                        highlight = if (setting.active) {
                            ((it.value!!.replace(",", ".").toDouble()) > setting.value)
                        } else {
                            false
                        },
                        data = it.data.toString(),
                        value = it.value!!.replace(",", ".").toDouble()
                    )
                }
            )
        }.flowOn(Dispatchers.IO)
    }
}
