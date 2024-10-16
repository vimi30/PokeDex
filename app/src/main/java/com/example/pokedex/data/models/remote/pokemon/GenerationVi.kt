package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVi(
    @SerialName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire?,
    @SerialName("x-y")
    val xY: XY?
)