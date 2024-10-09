package com.example.pokedex.data.models.remote.pokemonspecies


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Color(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)