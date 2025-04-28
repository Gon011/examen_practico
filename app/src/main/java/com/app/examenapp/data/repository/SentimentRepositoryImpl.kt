package com.app.examenapp.data.repository

import com.app.examenapp.data.mapper.toDomain
import com.app.examenapp.data.remote.api.SentimentApi
import com.app.examenapp.domain.model.Sentiment
import com.app.examenapp.domain.repository.SentimentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SentimentRepositoryImpl
    @Inject
    constructor(
        private val api: SentimentApi,
    ) : SentimentRepository {
        private val sentimentHistory = mutableListOf<Sentiment>()

        override suspend fun analyzeSentiment(text: String): Sentiment {
            val response = api.analyzeSentiment(text)
            val sentiment = response.toDomain()
            sentimentHistory.add(sentiment)
            return sentiment
        }

        override fun getSentimentList(): List<Sentiment> = sentimentHistory
    }
