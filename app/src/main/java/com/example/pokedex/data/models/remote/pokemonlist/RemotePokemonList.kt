package com.example.pokedex.data.models.remote.pokemonlist


import com.example.pokedex.data.models.domain.pokemonList.PokemonList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePokemonList(
    @SerialName("count")
    val count: Int?,
    @SerialName("next")
    val next: String?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<RemoteSimplePokemon?>?
)

fun RemotePokemonList.toDomainPokemonList(): PokemonList {
    return PokemonList(
        count = count ?: 0,
        next = next ?: "",
        previous = previous ?: "",
        results = results?.mapNotNull { it?.toDomainSimplePokemon() } ?: emptyList()
    )
}