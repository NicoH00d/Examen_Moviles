package com.example.kotlin.examen_moviles.Data.network.model

import com.google.gson.annotations.SerializedName

data class HistoricalEvent(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("date") val date: String,
    @SerializedName("category") val category: String,
    @SerializedName("region") val region: String
)
