package com.app.examenapp.domain.usecase

import com.app.examenapp.domain.common.Result
import com.app.examenapp.domain.model.Sentiment
import com.app.examenapp.domain.repository.SentimentRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnalyzeSentimentUseCase
    @Inject
    constructor(
        private val repository: SentimentRepository,
    ) {
        operator fun invoke(text: String): Flow<Result<Sentiment>> =
            flow {
                try {
                    emit(Result.Loading)
                    val sentiment = repository.analyzeSentiment(text)
                    emit(Result.Success(sentiment))
                } catch (e: Exception) {
                    emit(Result.Error(e))
                }
            }
    }
