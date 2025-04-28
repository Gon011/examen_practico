package com.app.examenapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SentimentDto(
    @SerializedName("score") val score: Double,
    @SerializedName("text") val text: String,
    @SerializedName("sentiment") val sentiment: String,
)
