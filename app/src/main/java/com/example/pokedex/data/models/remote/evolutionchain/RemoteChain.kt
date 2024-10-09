package com.example.pokedex.data.models.remote.evolutionchain


import com.example.pokedex.data.models.domain.evolutionchain.Chain
import com.example.pokedex.data.models.remote.pokemon.Species
import com.example.pokedex.data.models.remote.pokemon.toDomainSpecies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteChain(
    @SerialName("evolution_details")
    val remoteEvolutionDetails: List<RemoteEvolutionDetail>?,
    @SerialName("evolves_to")
    val remoteEvolvesTo: List<RemoteEvolvesTo>?,
    @SerialName("is_baby")
    val isBaby: Boolean?,
    @SerialName("species")
    val species: Species?
)

fun RemoteChain.toDomainChain(): Chain {
    return Chain(
        isBaby = isBaby ?: false,
        species = species?.toDomainSpecies()
            ?: com.example.pokedex.data.models.domain.pokemon.Species("", ""),
        evolvesTo = remoteEvolvesTo?.map { it.toDomainEvolvesTo() } ?: emptyList()
    )
}