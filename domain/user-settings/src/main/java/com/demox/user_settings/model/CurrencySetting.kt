package com.demox.user_settings.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrencySetting(
    val name: String? = "currency observer",
    val active: Boolean? = false,
    val value: Double = 0.0
)
