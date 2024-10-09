package com.example.pokedex.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.utils.capitalizeName

@Composable
fun PokemonAbout(data: Pokemon) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AboutDataComponent(
            "Species", capitalizeName(
                data.species.name
            )
        )
        AboutDataComponent(
            "Height", capitalizeName(
                data.height.toString() + " Dc"
            )
        )
        AboutDataComponent(
            "Weight", capitalizeName(
                data.weight.toString() + " Hg"
            )
        )
        AboutDataComponent(
            "Abilities",
            data.abilities.joinToString(", ") { capitalizeName(it.name) }

        )
        AboutDataComponent(
            "Forms",
            data.forms.joinToString(", ") { capitalizeName(it.name) }
        )
    }

}