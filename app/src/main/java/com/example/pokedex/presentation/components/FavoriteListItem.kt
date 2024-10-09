package com.example.pokedex.presentation.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.presentation.components.commons.PokemonNameComponent
import com.example.pokedex.utils.capitalizeName
import com.example.pokedex.utils.getContrastingTextColor
import com.example.pokedex.utils.hexStringToColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FavoriteListItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon,
    onClick: (String, String) -> Unit
) {
    val dominantColor = remember(pokemon.dominantColor) { hexStringToColor(pokemon.dominantColor) }
    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            dominantColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { onClick(
            pokemon.name,
            pokemon.dominantColor
        ) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            PokemonImageComponent(
                animatedVisibilityScope = animatedVisibilityScope,
                imageUrl = pokemon.imageUrl,
                modifier = Modifier
                    .size(160.dp)
                    .padding(16.dp)
                    .aspectRatio(1f)
            ) { }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                PokemonNameComponent(
                    animatedVisibilityScope = animatedVisibilityScope,
                    name = capitalizeName(pokemon.name),
                    fontSize = 24.sp,
                    textColor = getContrastingTextColor(dominantColor),
                    modifier = Modifier
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                pokemon.types.forEach { type ->
                    ElevatedFilterChip(
                        colors = FilterChipDefaults.filterChipColors(
                            dominantColor,
                        ),
                        label = {
                            Text(
                                text = capitalizeName(type.name),
                                color = getContrastingTextColor(dominantColor)
                            )
                        },
                        onClick = {},
                        selected = false,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }
        }
    }

}
