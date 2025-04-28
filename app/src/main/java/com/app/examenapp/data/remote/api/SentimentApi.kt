package com.app.examenapp.data.remote.api

import com.app.examenapp.data.remote.dto.SentimentDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SentimentApi {
    @GET("sentiment")
    suspend fun analyzeSentiment(
        @Query("text") text: String,
    ): SentimentDto
}
