package com.example.kotlin.examen_moviles.Data.network


import com.example.kotlin.examen_moviles.Data.network.model.HistoricalEvent

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("functions/hello")
    suspend fun getHistoricalEvents(
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String,
        @Query("page") page: Int
    ): Response<List<HistoricalEvent>>
}
