package com.example.pokedex.presentation.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun NavigationIconComponent(
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier,
    iconTint: Color
) {
    Box(modifier = Modifier
        .background(
            shape = RoundedCornerShape(12.dp),
            color = Color.Transparent
        )
        .clip(shape = RoundedCornerShape(12.dp))
        .padding(8.dp)
        .clickable { onClick() }
        .then(modifier)
    ) {
        Icon(
            painter = icon,
            contentDescription = "back arrow",
            tint = iconTint
        )
    }
}