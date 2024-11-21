package com.example.kotlin.examen_moviles.Data.repositories

import android.util.Log
import com.example.kotlin.examen_moviles.Data.network.ApiService
import com.example.kotlin.examen_moviles.Data.network.model.HistoricalEvent


class HistoricalRepository(private val apiService: ApiService) {
    private val appId = "APP_ID"
    private val apiKey = "MASTER_KEY"

    suspend fun fetchHistoricalEvents(page: Int): Result<List<HistoricalEvent>> {
        Log.d("Repository", "Fetching events for page $page")
        return try {
            val response = apiService.getHistoricalEvents(
                appId,
                apiKey,
                mapOf("page" to page) // Enviar el número de página como cuerpo
            )
            if (response.isSuccessful) {
                val events = response.body()?.result?.data ?: emptyList()
                Log.d("Repository", "Fetched ${events.size} events.")
                Result.success(events)
            } else {
                Log.e("Repository", "API Error: Code ${response.code()}, Message: ${response.message()}")
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("Repository", "Exception: ${e.message}")
            Result.failure(e)
        }
    }

}
