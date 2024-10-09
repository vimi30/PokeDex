package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Species(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)

fun Species.toDomainSpecies(): com.example.pokedex.data.models.domain.pokemon.Species {
    return com.example.pokedex.data.models.domain.pokemon.Species(
        name = name ?: "",
        url = url ?: ""
    )
}