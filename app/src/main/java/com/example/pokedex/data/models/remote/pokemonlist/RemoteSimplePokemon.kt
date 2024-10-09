package com.example.pokedex.data.models.remote.pokemonlist


import com.example.pokedex.data.models.domain.pokemonList.SimplePokemon
import com.example.pokedex.utils.getImageUrlFromPokemonUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSimplePokemon(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)


fun RemoteSimplePokemon.toDomainSimplePokemon(): SimplePokemon {
    return SimplePokemon(
        name = name ?: "",
        url = url ?: "",
        imageUrl = getImageUrlFromPokemonUrl(url ?: "")
    )
}
