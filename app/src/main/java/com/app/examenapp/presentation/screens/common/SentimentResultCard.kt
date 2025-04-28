package com.app.examenapp.presentation.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.examenapp.domain.model.Sentiment

@Suppress("ktlint:standard:function-naming")
@Composable
fun SentimentResultCard(sentiment: Sentiment) {
    val (backgroundColor, emotionLabel) =
        when (sentiment.sentiment) {
            "POSITIVE" ->
                Pair(
                    Color(0xFFE0F7FA),
                    "Entusiasta",
                )
            "WEAK_POSITIVE" ->
                Pair(
                    Color(0xFFF1F8E9),
                    "Agradable",
                )
            "NEGATIVE" ->
                Pair(
                    Color(0xFFFFEBEE),
                    "Devastado",
                )
            "WEAK_NEGATIVE" ->
                Pair(
                    Color(0xFFFBE9E7),
                    "Apático",
                )
            "NEUTRAL" ->
                Pair(
                    Color(0xFFECEFF1),
                    "Calmado",
                )
            else ->
                Pair(
                    Color.LightGray,
                    "Desconocido",
                )
        }

    val score = sentiment.score
    val showPercentage = score != 0.0
    val percentage = (kotlin.math.abs(score) * 100).toInt()

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = backgroundColor,
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = emotionLabel,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )

            if (showPercentage) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text =
                        if (score > 0) {
                            "Motivación $percentage%"
                        } else {
                            "Depresión $percentage%"
                        },
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "\"${sentiment.text}\"",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
        }
    }
}
