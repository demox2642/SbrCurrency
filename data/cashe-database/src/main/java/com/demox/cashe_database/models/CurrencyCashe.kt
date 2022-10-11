package com.demox.cashe_database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demox.cashe_database.conteins.CurrencyCasheConteins

@Entity(
    tableName = CurrencyCasheConteins.TABLE_NAME

)

data class CurrencyCashe(
    @PrimaryKey
    @ColumnInfo(name = CurrencyCasheConteins.Colums.DATA)
    val data: String,
    @ColumnInfo(name = CurrencyCasheConteins.Colums.VALUE)
    val value: Double
)
