package com.app.examenapp.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.examenapp.presentation.home.HomeViewModel
import com.app.examenapp.presentation.screens.common.ErrorMessage
import com.app.examenapp.presentation.screens.common.LoadingIndicator
import com.app.examenapp.presentation.screens.common.SentimentResultCard
import com.app.examenapp.presentation.screens.home.components.AnalyzeButton
import com.app.examenapp.presentation.screens.home.components.TextInputField

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    onHistoryClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var textInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Análisis de Sentimiento")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            TextInputField(
                value = textInput,
                onValueChange = { textInput = it },
                label = "Escribe o pega el texto a analizar",
            )

            Spacer(modifier = Modifier.height(16.dp))

            AnalyzeButton(
                text = "Analizar Sentimiento",
                enabled = textInput.isNotBlank() && !uiState.isLoading,
                onClick = {
                    viewModel.analyzeText(textInput)
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onHistoryClick,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Ver historial de análisis")
            }

            Spacer(modifier = Modifier.height(24.dp))

            when {
                uiState.isLoading -> {
                    LoadingIndicator()
                }
                uiState.error != null -> {
                    ErrorMessage(message = uiState.error ?: "Error desconocido")
                }
                uiState.sentiment != null -> {
                    SentimentResultCard(sentiment = uiState.sentiment!!)
                }
            }
        }
    }
}
