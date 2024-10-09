package com.example.pokedex.data.models.domain.evolutionchain

import com.example.pokedex.data.models.domain.pokemon.Species

data class EvolvesTo(
    val isBaby: Boolean,
    val evolvesTo: List<EvolvesTo>,
    val species: Species
)
