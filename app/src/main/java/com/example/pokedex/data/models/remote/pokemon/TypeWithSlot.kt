package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeWithSlot(
    @SerialName("slot")
    val slot: Int?,
    @SerialName("type")
    val type: Type?
)

fun TypeWithSlot.toDomainType(): com.example.pokedex.data.models.domain.pokemon.Type {
    return com.example.pokedex.data.models.domain.pokemon.Type(
        name = type?.name ?: "",
        url = type?.url ?: ""
    )
}