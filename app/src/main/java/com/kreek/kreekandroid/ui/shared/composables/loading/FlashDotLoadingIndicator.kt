package com.kreek.kreekandroid.ui.shared.composables.loading

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme


@Composable
fun FlashDotLoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    indicatorSpacing: Dp = 4.dp,
    numOfIndicators: Int = 3,
    indicatorSize: Int = 20,
    animationDurationInMillis: Int = 300
) {

    val animationDelayInMillis = animationDurationInMillis / numOfIndicators
    val animatedValues = List(numOfIndicators) { index ->
        var animatedValue by remember(key1 = Unit) { mutableStateOf(0f) }
        LaunchedEffect(key1 = Unit) {
            animate(
                initialValue = indicatorSize / 2f,
                targetValue = -indicatorSize / 2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = animationDurationInMillis),
                    repeatMode = RepeatMode.Reverse,
                    initialStartOffset = StartOffset(animationDelayInMillis * index),
                ),
            ) { value, _ -> animatedValue = value }
        }
        animatedValue
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        animatedValues.forEach { animatedValue ->
            LoadingDot(
                modifier = Modifier
                    .padding(horizontal = indicatorSpacing)
                    .width(indicatorSize.dp)
                    .aspectRatio(1f)
                    .offset(y = animatedValue.dp),
                color = color,
            )
        }
    }
}

@Preview
@Composable
private fun FlashDotLoadingIndicatorPreview() {
    KreekandroidTheme {
        FlashDotLoadingIndicator()
    }
}