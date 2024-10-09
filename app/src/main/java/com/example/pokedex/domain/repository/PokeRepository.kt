package com.example.pokedex.domain.repository

import com.example.pokedex.data.models.domain.evolutionchain.EvolutionChain
import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.data.models.domain.pokemon.toAbilityEntities
import com.example.pokedex.data.models.domain.pokemon.toFormEntities
import com.example.pokedex.data.models.domain.pokemon.toMoveEntities
import com.example.pokedex.data.models.domain.pokemon.toPokemonEntity
import com.example.pokedex.data.models.domain.pokemon.toSpeciesEntity
import com.example.pokedex.data.models.domain.pokemon.toStatEntities
import com.example.pokedex.data.models.domain.pokemon.toTypeEntities
import com.example.pokedex.data.models.domain.pokemonList.PokemonList
import com.example.pokedex.data.models.domain.pokemonspecies.PokemonSpecies
import com.example.pokedex.data.models.local.dao.PokemonDao
import com.example.pokedex.data.models.local.relationships.toDomainPokemon
import com.example.pokedex.data.network.NetworkService
import com.example.pokedex.data.network.error.NetworkError
import com.example.pokedex.data.network.response.Response
import com.example.pokedex.data.network.response.onError
import com.example.pokedex.data.network.response.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val networkService: NetworkService,
    private val pokemonDao: PokemonDao
) {
    suspend fun fetchSimplePokemon(offSet: Int): Response<PokemonList, NetworkError> {
        return networkService.fetchSimplePokemonList(offset = offSet)
    }

    suspend fun fetchPokemonDetails(pokemonName: String): Response<Pokemon, NetworkError> {

        val localPokemon = withContext(Dispatchers.IO) {
            pokemonDao.getPokemonDetailsByName(pokemonName = pokemonName)
        }

        if (localPokemon != null) {
            return Response.Success(localPokemon.toDomainPokemon())
        }

        return networkService.fetchPokemonDetails(pokemonName = pokemonName)
            .onSuccess { pokemon ->
                savePokemonToLocal(pokemon)
                Response.Success(pokemon)
            }
            .onError { networkError ->
                Response.Error(networkError)
            }
    }

    suspend fun fetchPokemonEvolutionChain(evolutionChainId: String): Response<EvolutionChain, NetworkError> {
        return networkService.fetchPokemonEvolutionChain(evolutionChainId = evolutionChainId)
    }

    suspend fun fetchPokemonSpecies(pokemonId: Int): Response<PokemonSpecies, NetworkError> {
        return networkService.fetchPokemonSpecies(pokemonId = pokemonId)
    }

    private suspend fun savePokemonToLocal(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            pokemonDao.insertPokemon(pokemon = pokemon.toPokemonEntity())
            pokemonDao.insertAbilities(abilities = pokemon.toAbilityEntities())
            pokemonDao.insertTypes(types = pokemon.toTypeEntities())
            pokemonDao.insertMoves(moves = pokemon.toMoveEntities())
            pokemonDao.insertStats(stats = pokemon.toStatEntities())
            pokemonDao.insertForms(forms = pokemon.toFormEntities())
            pokemonDao.insertSpecies(species = pokemon.toSpeciesEntity())
        }
    }

    suspend fun getFavoritePokemons(): Response<List<Pokemon>, NetworkError> {
        return try {
            val pokemonWithDetailsList = withContext(Dispatchers.IO) {
                pokemonDao.getAllFavoritePokemon()
            }
            val pokemonList = pokemonWithDetailsList.map { it.toDomainPokemon() }
            Response.Success(pokemonList)
        } catch (e: Exception) {
            Response.Error(NetworkError.DATABASE_ERROR)
        }
    }

    suspend fun addPokemonToFavorite(pokemon: Pokemon) {
        savePokemonToLocal(
            pokemon = pokemon
        )
    }

    suspend fun removeFromFavorites(pokemon: Pokemon) {
        savePokemonToLocal(
            pokemon = pokemon.copy(isFavorite = false)
        )
    }
}