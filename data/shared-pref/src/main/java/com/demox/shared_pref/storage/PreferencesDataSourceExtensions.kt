package com.demox.shared_pref.storage

import com.google.gson.Gson

inline fun <reified T> UserStorage.setAsJson(key: String, value: T) {
    try {
        if (value == null) {
            set(key, "")
        } else {
            val gson = Gson()
            set(key, gson.toJson(value))
        }
    } catch (e: Exception) {
        set(key, "")
    }
}

inline fun <reified T> UserStorage.getFromJson(key: String = T :: class.java.name): T? {
    return try {
        val json = get(key, "")
        if (json.isEmpty()) {
            null
        } else {
            val gson = Gson()
            gson.fromJson(json, T :: class.java)
        }
    } catch (e: Exception) {
        null
    }
}
