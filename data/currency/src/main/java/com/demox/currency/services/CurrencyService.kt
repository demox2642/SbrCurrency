package com.demox.currency.services

import com.demox.currency.model.CurrencyResponse
import com.demox.currency.model.SbResponse
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.GET

interface CurrencyService {

    @GET("XML_dynamic.asp?date_req1=02/03/2022&date_req2=14/03/2022&VAL_NM_RQ=R01235")
    suspend fun getCurrency(): Response<SbResponse>

    @get:GET("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=02/03/2022&date_req2=14/03/2022&VAL_NM_RQ=R01235")
    val feed: Call<SbResponse?>?
}