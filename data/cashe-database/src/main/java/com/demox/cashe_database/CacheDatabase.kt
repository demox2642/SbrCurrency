package com.demox.cashe_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demox.cashe_database.dao.CurrencyCasheDAO
import com.demox.cashe_database.models.CurrencyCashe

@Database(
    entities = [CurrencyCashe::class],
    version = 1
)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun currencyCacheDAO(): CurrencyCasheDAO
}
