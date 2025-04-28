package com.app.examenapp.data.mapper

import com.app.examenapp.data.remote.dto.SentimentDto
import com.app.examenapp.domain.model.Sentiment

fun SentimentDto.toDomain(): Sentiment =
    Sentiment(
        score = score,
        text = text,
        sentiment = sentiment,
    )
