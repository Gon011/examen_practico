package com.app.examenapp.domain.repository

import com.app.examenapp.domain.model.Sentiment

interface SentimentRepository {
    suspend fun analyzeSentiment(text: String): Sentiment
}
