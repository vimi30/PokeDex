package com.example.pokedex.presentation.components

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.data.models.domain.pokemonList.SimplePokemon
import com.example.pokedex.ui.theme.LightYellow
import com.example.pokedex.utils.capitalizeName
import com.example.pokedex.utils.toHexString

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonGridItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier,
    pokemon: SimplePokemon,
    onClick: (String) -> Unit,
    calDominantColor: (Drawable, (Color) -> Unit) -> Unit
) {

    var dominantColor by remember { mutableStateOf(LightYellow) }

    Card(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .aspectRatio(1f),
        onClick = {
            onClick(
                dominantColor.toHexString()
            )
        },
        colors = CardDefaults.cardColors(dominantColor),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            PokemonImageComponent(
                animatedVisibilityScope = animatedVisibilityScope,
                imageUrl = pokemon.imageUrl,
                sendDominantColor = { drawable ->
                    calDominantColor(drawable) { domColor ->
                        dominantColor = domColor
                    }
                },
                modifier = Modifier
                    .size(150.dp)
                    .padding(16.dp)
            )
            val name = capitalizeName(pokemon.name)
            Text(
                textAlign = TextAlign.Center,
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = name),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            )
        }
    }
}


//@Preview
//@Composable
//fun GridItemPreview() {
//    PokeDexTheme {
//        PokemonGridItem(
//            modifier = Modifier,
//            onClick = {},
//            calDominantColor =
//            { _, onColorCalculated ->
//                onColorCalculated(Color.LightGray) // Set a default color for the preview
//            },
//            pokemon = SimplePokemon(
//                name = "Ditto",
//                url = "",
//                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png"
//            )
//
//        )
//    }
//}