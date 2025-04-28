package com.app.examenapp.data.repository

import com.app.examenapp.data.mapper.toDomain
import com.app.examenapp.data.remote.api.SentimentApi
import com.app.examenapp.domain.model.Sentiment
import com.app.examenapp.domain.repository.SentimentRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class SentimentRepositoryImpl
    @Inject
    constructor(
        private val api: SentimentApi,
    ) : SentimentRepository {
        override suspend fun analyzeSentiment(text: String): Sentiment = api.analyzeSentiment(text).toDomain()
    }
