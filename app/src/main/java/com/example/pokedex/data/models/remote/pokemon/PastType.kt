package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PastType(
    @SerialName("generation")
    val generation: Generation?,
    @SerialName("types")
    val typeWithSlots: List<TypeWithSlot>?
)