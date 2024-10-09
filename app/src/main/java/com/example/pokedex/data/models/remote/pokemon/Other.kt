package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Other(
    @SerialName("dream_world")
    val dreamWorld: DreamWorld?,
    @SerialName("home")
    val home: Home?,
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork?,
    @SerialName("showdown")
    val showdown: Showdown?
)