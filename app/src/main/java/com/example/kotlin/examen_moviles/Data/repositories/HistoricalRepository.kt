package com.example.kotlin.examen_moviles.Data.repositories

import android.util.Log
import com.example.kotlin.examen_moviles.Data.network.ApiService
import com.example.kotlin.examen_moviles.Data.network.model.HistoricalEvent


class HistoricalRepository(private val apiService: ApiService) {
    private val appId = "APP_ID"
    private val apiKey = "MASTER_KEY"

    suspend fun fetchHistoricalEvents(page: Int, limit: Int): Result<List<HistoricalEvent>> {
        Log.d("Repository", "Fetching events for page $page with limit $limit")
        return try {
            val response = apiService.getHistoricalEvents(
                appId,
                apiKey,
                mapOf("page" to page, "limit" to limit)
            )
            if (response.isSuccessful) {
                val events = response.body()?.result?.data ?: emptyList()
                Log.d("Repository", "Fetched ${events.size} events from page $page")
                Result.success(events)
            } else {
                Log.e("Repository", "API Error: ${response.code()} - ${response.message()}")
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("Repository", "Exception: ${e.message}")
            Result.failure(e)
        }
    }


}
