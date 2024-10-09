package com.example.pokedex.presentation.components

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.pokedex.presentation.components.commons.LoadingState

private val defaultModifier = Modifier
    .fillMaxWidth()
    .clip(RoundedCornerShape(12.dp))

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonImageComponent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    imageUrl: String,
    modifier: Modifier = defaultModifier,
    sendDominantColor: (Drawable) -> Unit
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = "Character Image",
        modifier = Modifier
            .sharedElement(
                state = rememberSharedContentState(key = imageUrl),
                animatedVisibilityScope = animatedVisibilityScope
            ).then(modifier),
        loading = { LoadingState(modifier = Modifier.size(20.dp)) },
        onSuccess = {
            it.result.drawable.let(sendDominantColor)
        }
    )
}