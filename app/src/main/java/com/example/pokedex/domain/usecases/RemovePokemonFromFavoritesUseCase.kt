package com.example.pokedex.domain.usecases

import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.domain.repository.PokeRepository
import javax.inject.Inject

class RemovePokemonFromFavoritesUseCase @Inject constructor( private val repository: PokeRepository ) {
    suspend operator fun invoke(pokemon: Pokemon) {
        repository.removeFromFavorites(pokemon = pokemon)
    }
}