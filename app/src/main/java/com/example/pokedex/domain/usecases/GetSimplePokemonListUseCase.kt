package com.example.pokedex.domain.usecases

import com.example.pokedex.data.models.domain.pokemonList.PokemonList
import com.example.pokedex.data.network.error.NetworkError
import com.example.pokedex.data.network.response.Response
import com.example.pokedex.domain.repository.PokeRepository
import javax.inject.Inject

class GetSimplePokemonListUseCase @Inject constructor(private val repository: PokeRepository) {
    suspend operator fun invoke(offset: Int): Response<PokemonList, NetworkError> {
        return repository.fetchSimplePokemon(offSet = offset)
    }
}