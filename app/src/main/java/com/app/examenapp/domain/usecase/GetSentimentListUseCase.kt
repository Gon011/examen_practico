package com.app.examenapp.domain.usecase

import com.app.examenapp.domain.common.Result
import com.app.examenapp.domain.model.Sentiment
import com.app.examenapp.domain.repository.SentimentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSentimentListUseCase
    @Inject
    constructor(
        private val repository: SentimentRepository,
    ) {
        operator fun invoke(): Flow<Result<List<Sentiment>>> =
            flow {
                try {
                    emit(Result.Loading)
                    val sentimentHistory = repository.getSentimentList()
                    emit(Result.Success(sentimentHistory))
                } catch (e: Exception) {
                    emit(Result.Error(e))
                }
            }
    }
