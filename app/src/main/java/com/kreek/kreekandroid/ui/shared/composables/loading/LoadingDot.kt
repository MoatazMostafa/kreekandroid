package com.kreek.kreekandroid.ui.shared.composables.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme

@Composable
fun LoadingDot(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = color)
    )
}


@Preview
@Composable
private fun LoadingDotPreview() {
    KreekandroidTheme {
        LoadingDot(color = MaterialTheme.colorScheme.primary)
    }
}