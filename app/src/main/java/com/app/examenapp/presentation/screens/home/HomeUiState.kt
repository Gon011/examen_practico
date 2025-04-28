package com.app.examenapp.presentation.screens.home

import com.app.examenapp.domain.model.Sentiment

data class HomeUiState(
    val sentiment: Sentiment? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
