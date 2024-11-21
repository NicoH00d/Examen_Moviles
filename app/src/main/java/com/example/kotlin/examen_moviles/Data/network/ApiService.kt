package com.example.kotlin.examen_moviles.Data.network

import com.example.kotlin.examen_moviles.Data.network.model.HistoricalResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("functions/hello")
    suspend fun getHistoricalEvents(
        @Header("X-Parse-Application-Id") appId: String,
        @Header("X-Parse-REST-API-Key") apiKey: String,
        @Body params: Map<String, Int> // Usamos un cuerpo JSON para enviar "page"
    ): Response<HistoricalResponse>
}
