package com.example.pokedex.presentation.screens

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class PokemonDetails(val pokemonName: String, val dominantColor: String)

@Serializable
object FavoritePokemonListScreen