package com.example.pokedex.data.models.remote.evolutionchain


import com.example.pokedex.data.models.domain.evolutionchain.EvolvesTo
import com.example.pokedex.data.models.remote.pokemon.Species
import com.example.pokedex.data.models.remote.pokemon.toDomainSpecies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteEvolvesTo(
    @SerialName("evolution_details")
    val remoteEvolutionDetails: List<RemoteEvolutionDetail>?,
    @SerialName("evolves_to")
    val remoteEvolvesTo: List<RemoteEvolvesTo>?,
    @SerialName("is_baby")
    val isBaby: Boolean?,
    @SerialName("species")
    val species: Species?
)

fun RemoteEvolvesTo.toDomainEvolvesTo(): EvolvesTo {
    return EvolvesTo(
        isBaby = isBaby ?: false,
        species = species?.toDomainSpecies()
            ?: com.example.pokedex.data.models.domain.pokemon.Species("", ""),
        evolvesTo = remoteEvolvesTo?.map { it.toDomainEvolvesTo() } ?: emptyList()
    )
}