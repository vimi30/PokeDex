package com.example.pokedex.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.pokedex.presentation.components.commons.LoadingState
import com.example.pokedex.data.models.domain.evolutionchain.EvolutionStage
import com.example.pokedex.utils.capitalizeName


@Composable
fun PokemonEvolutionComponent(
    evolutionStage: EvolutionStage
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = evolutionStage.imageUrl,
            contentDescription = "Character Image",
            modifier = Modifier
                .size(120.dp)
                .aspectRatio(1f),
            loading = { LoadingState(modifier = Modifier.size(20.dp)) },
        )

        Text(
            textAlign = TextAlign.Center,
            text = capitalizeName(evolutionStage.speciesName),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}