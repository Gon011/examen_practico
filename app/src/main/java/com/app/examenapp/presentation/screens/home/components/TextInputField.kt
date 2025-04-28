package com.app.examenapp.presentation.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("ktlint:standard:function-naming")
@Composable
fun TextInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = false,
        maxLines = 5,
        modifier = Modifier.fillMaxWidth(),
    )
}
