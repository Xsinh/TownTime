package com.implosion.towntime.data.network

import com.implosion.towntime.data.network.model.CapitalTimeNetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApi {

    @GET("https://www.timeapi.io/api/time/current/zone")
    suspend fun getData(@Query("timeZone") timeZone: String): Response<CapitalTimeNetworkResponse>
}