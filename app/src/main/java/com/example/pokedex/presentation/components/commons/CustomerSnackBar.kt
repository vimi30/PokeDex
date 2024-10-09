package com.example.pokedex.presentation.components.commons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun CustomSnackbar(
    snackbarData: SnackbarData,
    backgroundColor: Color,
    contentColor: Color = Color.White,
    actionColor: Color = Color.Yellow,
    shape: Shape = RoundedCornerShape(12.dp)
) {
    Snackbar(
        snackbarData = snackbarData,
        containerColor = backgroundColor,
        contentColor = contentColor,
        actionColor = actionColor,
        shape = shape
    )
}