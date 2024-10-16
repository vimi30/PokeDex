package com.example.pokedex.presentation.components.commons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val defaultModifier = Modifier
    .fillMaxSize()
    .padding(128.dp)

@Composable
fun LoadingState(modifier: Modifier = defaultModifier, indicatorColor: Color = Color.Gray) {
    CircularProgressIndicator(
        modifier = modifier,
        color = indicatorColor
    )
}