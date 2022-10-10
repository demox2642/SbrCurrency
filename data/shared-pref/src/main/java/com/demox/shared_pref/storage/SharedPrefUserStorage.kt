package com.demox.shared_pref.storage

import android.content.Context

class SharedPrefUserStorage (private val context: Context) : UserStorage {
    val sharedPreferences = context.getSharedPreferences(UserRepositoryConst.SHAREDPREF, Context.MODE_PRIVATE)!!

    override fun set(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun get(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue)!!
    }


}