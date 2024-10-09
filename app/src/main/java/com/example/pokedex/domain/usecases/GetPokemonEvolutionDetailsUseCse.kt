package com.example.pokedex.domain.usecases

import com.example.pokedex.data.models.domain.evolutionchain.Chain
import com.example.pokedex.data.models.domain.evolutionchain.EvolutionStage
import com.example.pokedex.data.models.domain.evolutionchain.EvolvesTo
import com.example.pokedex.data.network.error.NetworkError
import com.example.pokedex.data.network.response.Response
import com.example.pokedex.domain.repository.PokeRepository
import com.example.pokedex.utils.extractIdFromUrl
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetPokemonEvolutionDetailsUseCse @Inject constructor(private val repository: PokeRepository) {
    suspend operator fun invoke(pokemonId: Int): Response<List<EvolutionStage>, NetworkError> {

        val speciesResult = repository.fetchPokemonSpecies(pokemonId = pokemonId)
        if (speciesResult is Response.Error) return speciesResult

        val species =
            (speciesResult as Response.Success).data

        val evolutionChainId = extractIdFromUrl(species.pokemonEvolutionChainUrl)

        val evolutionChainResponse =
            repository.fetchPokemonEvolutionChain(evolutionChainId = evolutionChainId)
        if (evolutionChainResponse is Response.Error) return evolutionChainResponse

        val evolutionChain =
            (evolutionChainResponse as Response.Success).data

        val evolutionStages = getAllDetailedEvolutions(evolutionChain.chain)

        return fetchImagesForEvolutions(evolutionStages)

    }

    private suspend fun fetchImagesForEvolutions(
        evolutions: List<EvolutionStage>
    ): Response<List<EvolutionStage>, NetworkError> {
        return coroutineScope {
            val deferredList = evolutions.map { evolution ->
                async {
                    val pokemonResponse = repository.fetchPokemonDetails(
                        evolution.speciesName,
                    )
                    if (pokemonResponse is Response.Success) {
                        val imageUrl = pokemonResponse.data.imageUrl
                        evolution.copy(imageUrl = imageUrl)
                    } else {
                        evolution
                    }
                }
            }
            try {
                val updatedEvolutions = deferredList.map { it.await() }
                Response.Success(
                    updatedEvolutions
                )
            } catch (e: Exception) {
                Response.Error(
                    NetworkError.UNKNOWN
                )
            }
        }
    }

    private fun getAllDetailedEvolutions(remoteChain: Chain): List<EvolutionStage> {
        val evolutions =
            mutableListOf<EvolutionStage>()

        // Base case
        evolutions.add(
            EvolutionStage(
                speciesName = remoteChain.species.name,
                imageUrl = ""
            )
        )

        // Recursive case
        remoteChain.evolvesTo.forEach { evolvesTo ->
            evolutions.addAll(getDetailedEvolutionsFromEvolvesTo(evolvesTo))
        }

        return evolutions
    }

    private fun getDetailedEvolutionsFromEvolvesTo(remoteEvolvesTo: EvolvesTo): List<EvolutionStage> {
        val evolutions =
            mutableListOf<EvolutionStage>()

        evolutions.add(
            EvolutionStage(
                speciesName = remoteEvolvesTo.species.name,
                imageUrl = ""
            )
        )

        remoteEvolvesTo.evolvesTo.forEach { nextEvolution ->
            evolutions.addAll(getDetailedEvolutionsFromEvolvesTo(nextEvolution))
        }

        return evolutions
    }
}