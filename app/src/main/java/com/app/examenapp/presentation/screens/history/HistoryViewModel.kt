package com.app.examenapp.presentation.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenapp.domain.common.Result
import com.app.examenapp.domain.usecase.GetSentimentListUseCase
import com.app.examenapp.presentation.screens.history.HistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
    @Inject
    constructor(
        private val getSentimentListUseCase: GetSentimentListUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(HistoryUiState())
        val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

        fun loadHistory() {
            viewModelScope.launch {
                getSentimentListUseCase().collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = true,
                                    sentimentList = emptyList(),
                                    error = null,
                                )
                            }
                        }

                        is Result.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    sentimentList = result.data,
                                    error = null,
                                )
                            }
                        }

                        is Result.Error -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    error = result.exception.message ?: "Unknown error",
                                    sentimentList = emptyList(),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
