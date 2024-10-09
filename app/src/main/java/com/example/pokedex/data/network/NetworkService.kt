package com.example.pokedex.data.network

import com.example.pokedex.data.models.domain.evolutionchain.EvolutionChain
import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.data.models.domain.pokemonList.PokemonList
import com.example.pokedex.data.models.domain.pokemonspecies.PokemonSpecies
import com.example.pokedex.data.models.remote.evolutionchain.RemoteEvolutionChainResponse
import com.example.pokedex.data.models.remote.evolutionchain.toDomainEvolutionChainResponse
import com.example.pokedex.data.models.remote.pokemon.RemotePokemon
import com.example.pokedex.data.models.remote.pokemon.toDomainPokemon
import com.example.pokedex.data.models.remote.pokemonlist.RemotePokemonList
import com.example.pokedex.data.models.remote.pokemonlist.toDomainPokemonList
import com.example.pokedex.data.models.remote.pokemonspecies.RemotePokemonSpecies
import com.example.pokedex.data.models.remote.pokemonspecies.toDomainPokemonSpecies
import com.example.pokedex.data.network.error.NetworkError
import com.example.pokedex.data.network.response.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.JsonConvertException
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import javax.inject.Inject

class NetworkService @Inject constructor(private val client: HttpClient) {

    suspend fun fetchSimplePokemonList(offset: Int): Response<PokemonList, NetworkError> {
        return safeNetworkCall(
            networkCall = { client.get("pokemon/?offset=${offset}&limit=20") },
            successHandler = { response ->
                response.body<RemotePokemonList>()
                    .toDomainPokemonList()
            }
        )
    }

    suspend fun fetchPokemonDetails(pokemonName: String): Response<Pokemon, NetworkError> {
        return safeNetworkCall(
            networkCall = {
                client.get("pokemon/${pokemonName}")
            },
            successHandler = { response ->
                response.body<RemotePokemon>()
                    .toDomainPokemon()
            }
        )
    }

    suspend fun fetchPokemonEvolutionChain(evolutionChainId: String): Response<EvolutionChain, NetworkError> {
        return safeNetworkCall(
            networkCall = {
                client.get("evolution-chain/${evolutionChainId}")
            },
            successHandler = { response ->
                response.body<RemoteEvolutionChainResponse>()
                    .toDomainEvolutionChainResponse()
            }
        )
    }

    suspend fun fetchPokemonSpecies(pokemonId: Int): Response<PokemonSpecies, NetworkError> {
        return safeNetworkCall(
            networkCall = {
                client.get("pokemon-species/$pokemonId")
            },
            successHandler = { response ->
                response.body<RemotePokemonSpecies>()
                    .toDomainPokemonSpecies()
            }
        )
    }


    private inline fun <D> safeNetworkCall(
        networkCall: () -> HttpResponse,
        successHandler: (HttpResponse) -> D
    ): Response<D, NetworkError> {
        val response = try {
            networkCall()
        } catch (e: UnresolvedAddressException) {
            return Response.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Response.Error(NetworkError.SERIALIZATION)
        } catch (e: IOException) {
            return Response.Error(NetworkError.NO_INTERNET)
        }
        return when (response.status.value) {
            in 200..299 -> {
                try {
                    val data = successHandler(response)
                    Response.Success(data)
                } catch (e: SerializationException) {
                    Response.Error(NetworkError.SERIALIZATION)
                } catch (e: JsonConvertException) {
                    Response.Error(NetworkError.SERIALIZATION)
                }
            }

            401 -> Response.Error(NetworkError.UNAUTHORIZED)
            404 -> Response.Error(NetworkError.NOT_FOUND)
            409 -> Response.Error(NetworkError.CONFLICT)
            408 -> Response.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Response.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..509 -> Response.Error(NetworkError.SERVER_ERROR)
            else -> Response.Error(NetworkError.UNKNOWN)
        }
    }

}