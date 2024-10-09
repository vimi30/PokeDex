package com.example.pokedex.data.models.remote.pokemonspecies


import com.example.pokedex.data.models.remote.pokemonlist.RemoteSimplePokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Variety(
    @SerialName("is_default")
    val isDefault: Boolean?,
    @SerialName("pokemon")
    val pokemon: RemoteSimplePokemon?
)