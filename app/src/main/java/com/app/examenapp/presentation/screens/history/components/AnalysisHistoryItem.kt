package com.app.examenapp.presentation.screens.history.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.examenapp.domain.model.Sentiment
import com.app.examenapp.presentation.screens.common.SentimentResultCard

@Suppress("ktlint:standard:function-naming")
@Composable
fun AnalysisHistoryItem(
    sentimentList: List<Sentiment>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(sentimentList) { sentiment ->
            SentimentResultCard(sentiment = sentiment)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
