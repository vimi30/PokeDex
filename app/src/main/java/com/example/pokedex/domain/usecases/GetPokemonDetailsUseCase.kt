package com.example.pokedex.domain.usecases

import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.data.network.error.NetworkError
import com.example.pokedex.data.network.response.Response
import com.example.pokedex.domain.repository.PokeRepository
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(private val repository: PokeRepository) {
    suspend operator fun invoke(pokemonName: String): Response<Pokemon, NetworkError> {
        return repository.fetchPokemonDetails(pokemonName = pokemonName)
    }
}