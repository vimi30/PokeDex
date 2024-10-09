package com.example.pokedex.data.models.domain.evolutionchain

import com.example.pokedex.data.models.domain.pokemon.Species

data class Chain(
    val isBaby: Boolean,
    val species: Species,
    val evolvesTo: List<EvolvesTo>
)
