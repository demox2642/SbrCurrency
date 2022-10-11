package com.demox.cashe_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demox.cashe_database.conteins.CurrencyCasheConteins
import com.demox.cashe_database.models.CurrencyCashe

@Dao
interface CurrencyCasheDAO {

    @Query("DELETE FROM ${CurrencyCasheConteins.TABLE_NAME} ")
    suspend fun deleteAllCurrency()

    @Insert
    suspend fun saveCurrency(currencyList: List<CurrencyCashe>)

    @Query("SELECT * FROM ${CurrencyCasheConteins.TABLE_NAME}")
    suspend fun getAllCurrency(): List<CurrencyCashe>
}