package com.example.pokedex.data.models.remote.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseStat(
    @SerialName("base_stat")
    val baseStat: Int?,
    @SerialName("effort")
    val effort: Int?,
    @SerialName("stat")
    val stat: Stat?
)

fun BaseStat.toDomainStat(): com.example.pokedex.data.models.domain.pokemon.Stat {
    return com.example.pokedex.data.models.domain.pokemon.Stat(
        baseStat = baseStat ?: -1,
        name = stat?.name ?: "",
        url = stat?.url ?: ""
    )
}