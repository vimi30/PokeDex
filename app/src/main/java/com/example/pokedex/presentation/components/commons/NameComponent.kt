package com.example.pokedex.presentation.components.commons

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.theme.GhostWhite
import com.example.pokedex.ui.theme.PokeDexTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonNameComponent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    name: String,
    textColor: Color,
    fontSize: TextUnit = 36.sp,
    modifier: Modifier
) {
    Text(
        text = name,
        fontSize =fontSize,
        lineHeight = fontSize,
        fontWeight = FontWeight.Bold,
        color = textColor,
        modifier = Modifier
            .sharedElement(
                state = rememberSharedContentState(key = name),
                animatedVisibilityScope = animatedVisibilityScope
            ).then(modifier)

    )
}


@Preview
@Composable
fun PokemonNameComponentPreview() {

    PokeDexTheme {
        Text(
            text = "Chalizard"
        )
    }
}