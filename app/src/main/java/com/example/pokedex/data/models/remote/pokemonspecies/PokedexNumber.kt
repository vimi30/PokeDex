package com.example.pokedex.data.models.remote.pokemonspecies


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexNumber(
    @SerialName("entry_number")
    val entryNumber: Int?,
    @SerialName("pokedex")
    val pokedex: Pokedex?
)