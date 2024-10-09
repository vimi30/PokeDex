package com.example.pokedex.data.models.remote.pokemonspecies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Description(
    @SerialName("description")
    val description: String?,
    @SerialName("language")
    val language: Language?,
)
