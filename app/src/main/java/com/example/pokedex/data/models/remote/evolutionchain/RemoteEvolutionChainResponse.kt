package com.example.pokedex.data.models.remote.evolutionchain


import com.example.pokedex.data.models.domain.evolutionchain.Chain
import com.example.pokedex.data.models.domain.evolutionchain.EvolutionChain
import com.example.pokedex.data.models.domain.pokemon.Species
import com.example.pokedex.data.models.remote.pokemon.RemoteItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteEvolutionChainResponse(
    @SerialName("baby_trigger_item")
    val babyTriggerRemoteItem: RemoteItem?,
    @SerialName("chain")
    val remoteChain: RemoteChain?,
    @SerialName("id")
    val id: Int?
)

fun RemoteEvolutionChainResponse.toDomainEvolutionChainResponse(): EvolutionChain {
    return EvolutionChain(
        id = id ?: -1,
        chain = remoteChain?.toDomainChain()
            ?: Chain(
                isBaby = false,
                Species("", ""),
                emptyList()
            )
    )
}