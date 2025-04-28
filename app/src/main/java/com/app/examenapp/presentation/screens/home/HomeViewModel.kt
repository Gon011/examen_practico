package com.app.examenapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenapp.domain.common.Result
import com.app.examenapp.domain.usecase.AnalyzeSentimentUseCase
import com.app.examenapp.presentation.screens.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val analyzeSentimentUseCase: AnalyzeSentimentUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(HomeUiState())
        val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

        fun analyzeText(text: String) {
            viewModelScope.launch {
                analyzeSentimentUseCase(text).collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = true,
                                    sentiment = null,
                                    error = null,
                                )
                            }
                        }

                        is Result.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    sentiment = result.data,
                                    error = null,
                                )
                            }
                        }

                        is Result.Error -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    error = result.exception.message ?: "Unknown error",
                                    sentiment = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
