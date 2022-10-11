package com.demox.currency.services

import com.demox.currency.model.SbResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CurrencyService {
    @GET
    suspend fun getCurrency(
        @Url url: String
    ): Response<SbResponse>
}
