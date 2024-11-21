package com.example.kotlin.examen_moviles.Data.network.model

import com.google.gson.annotations.SerializedName

data class HistoricalEvent(
    @SerializedName("date") val date: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("lang") val lang: String?,
    @SerializedName("category1") val category1: String?,
    @SerializedName("category2") val category2: String?,
    @SerializedName("granularity") val granularity: String?,
    @SerializedName("createdAt") val createdAt: String?,
    @SerializedName("updatedAt") val updatedAt: String?,
    @SerializedName("objectId") val objectId: String?,
    @SerializedName("__type") val type: String?,
    @SerializedName("className") val className: String?
)
