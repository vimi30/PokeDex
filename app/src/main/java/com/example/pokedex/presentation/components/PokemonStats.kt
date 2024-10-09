package com.example.pokedex.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.pokedex.data.models.domain.pokemon.Stat

@Composable
fun PokemonStats(baseStats: List<Stat>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        baseStats.forEach { stat ->
            PokemonStatComponent(statLabel = stat.name, stat.baseStat)
        }
    }
}