package com.app.examenapp.presentation.screens.history

import com.app.examenapp.domain.model.Sentiment

data class HistoryUiState(
    val sentimentList: List<Sentiment> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
