package com.demox.currency.repository

import android.util.Log
import androidx.room.withTransaction
import com.demox.cashe_database.CacheDatabase
import com.demox.cashe_database.models.CurrencyCashe
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
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService,
    private val userStorage: UserStorage,
    private val cacheDatabase: CacheDatabase
) : CurrencyRepository {
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private val calendar = Calendar.getInstance()
    private val endDate = calendar.timeInMillis

    private val cacheDatabaseDAO = cacheDatabase.currencyCacheDAO()

    override suspend fun getCurrency(): Flow<List<Curency>> {
        calendar.add(Calendar.DAY_OF_YEAR, -30)
        val startDate = calendar
        val endDateString = dateFormat.format(endDate)
        val startDateString = dateFormat.format(startDate.time)

        val setting = userStorage.getFromJson<Setting>(UserRepositoryConst.SHAREDPREF) ?: Setting()
        val downloadData = userStorage.get(UserRepositoryConst.LOAD_DATA, "")
        val url = "XML_dynamic.asp?date_req1=$startDateString&date_req2=$endDateString&VAL_NM_RQ=R01235"

        return if (downloadData != endDateString) {
            val content = currencyService.getCurrency(url = url).body()
            flow {
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
        } else {
            flow {
                emit(
                    cacheDatabaseDAO.getAllCurrency().map {
                        Curency(
                            highlight = if (setting.active) {
                                (it.value) > setting.value
                            } else {
                                false
                            },
                            data = it.data,
                            value = it.value
                        )
                    }
                )
            }.flowOn(Dispatchers.IO)
        }
    }

    override suspend fun saveCurrency(currencyList: List<Curency>) {
        val downloadData = userStorage.get(UserRepositoryConst.LOAD_DATA, "")
        if (downloadData != dateFormat.format(endDate)) {
            cacheDatabase.withTransaction {
                try {
                    cacheDatabaseDAO.saveCurrency(
                        currencyList.map {
                            CurrencyCashe(
                                data = it.data,
                                value = it.value
                            )
                        }
                    )
                    userStorage.set(UserRepositoryConst.LOAD_DATA, dateFormat.format(endDate))
                } catch (e: Exception) {
                    Log.e("SaveCurrency ERROR:", "$e")
                }
            }
        }
    }
}
