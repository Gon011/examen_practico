package com.app.examenapp.presentation.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize().padding(top = 100.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        CircularProgressIndicator()
    }
}
