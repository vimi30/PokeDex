package com.example.pokedex.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedStatBarComponent(indicatorProgress: Float, modifier: Modifier) {
    var progress by remember { mutableFloatStateOf(0f) }
    val progressAnimDuration = 1500
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
        label = ""
    )

    LinearProgressIndicator(
        progress = { progressAnimation },
        modifier = modifier,
        color = if (progressAnimation < 0.5f) Color.Red else Color.Green,
        drawStopIndicator = {},
        strokeCap = StrokeCap.Round,
        gapSize = 0.dp
    )

    LaunchedEffect(indicatorProgress) {
        progress = indicatorProgress
    }
}