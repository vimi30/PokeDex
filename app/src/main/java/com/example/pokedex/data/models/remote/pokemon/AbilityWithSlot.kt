package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityWithSlot(
    @SerialName("ability")
    val ability: Ability?,
    @SerialName("is_hidden")
    val isHidden: Boolean?,
    @SerialName("slot")
    val slot: Int?
)

fun AbilityWithSlot.toDomainAbility(): com.example.pokedex.data.models.domain.pokemon.Ability {
    return com.example.pokedex.data.models.domain.pokemon.Ability(
        name = ability?.name ?: "",
        url = ability?.url ?: ""
    )
}