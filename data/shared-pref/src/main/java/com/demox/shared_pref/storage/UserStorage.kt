package com.demox.shared_pref.storage

interface UserStorage {
    fun set(key: String, value: String)
    fun get(key: String, defaultValue: String): String
}
