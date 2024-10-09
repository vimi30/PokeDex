package com.example.pokedex.data.models.remote.pokemonspecies


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PalParkEncounter(
    @SerialName("area")
    val area: Area?,
    @SerialName("base_score")
    val baseScore: Int?,
    @SerialName("rate")
    val rate: Int?
)