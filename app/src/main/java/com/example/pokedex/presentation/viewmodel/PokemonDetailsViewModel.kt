package com.example.pokedex.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.models.domain.evolutionchain.EvolutionStage
import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.data.network.response.onError
import com.example.pokedex.data.network.response.onSuccess
import com.example.pokedex.domain.usecases.AddPokemonToFavoriteUseCase
import com.example.pokedex.domain.usecases.GetPokemonDetailsUseCase
import com.example.pokedex.domain.usecases.GetPokemonEvolutionDetailsUseCse
import com.example.pokedex.domain.usecases.RemovePokemonFromFavoritesUseCase
import com.example.pokedex.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val getPokemonEvolutionDetailsUseCse: GetPokemonEvolutionDetailsUseCse,
    private val addPokemonToFavoriteUseCase: AddPokemonToFavoriteUseCase,
    private val removePokemonFromFavoritesUseCase: RemovePokemonFromFavoritesUseCase
) :
    ViewModel() {

    private val _viewState = MutableStateFlow<UiState<Pokemon>>(UiState.Loading)

    val viewState = _viewState.asStateFlow()

    private val _evolutionState = MutableStateFlow<UiState<List<EvolutionStage>>>(UiState.Loading)

    val pokemonEvolutionState = _evolutionState.asStateFlow()

    fun fetchPokemonDetails(pokemonName: String) = viewModelScope.launch {
        getPokemonDetailsUseCase.invoke(pokemonName = pokemonName)
            .onSuccess { pokemon ->
                _viewState.update {
                    UiState.Success(pokemon)
                }
            }
            .onError { error ->
                _viewState.update {
                    UiState.Error(errorMessage = error.message)
                }
            }

    }

    fun fetchPokemonEvolutionChain(pokemonId: Int) = viewModelScope.launch {
        _evolutionState.update {
            UiState.Loading
        }
        getPokemonEvolutionDetailsUseCse(pokemonId = pokemonId)
            .onSuccess { evolutionStages ->
                _evolutionState.update {
                    UiState.Success(evolutionStages)
                }
            }
            .onError { error ->
                _evolutionState.update {
                    UiState.Error(error.message)
                }
            }
    }

    fun addPokemonToFavoriteList( pokemon: Pokemon, dominantColor: String) = viewModelScope.launch {
        addPokemonToFavoriteUseCase.invoke(pokemon = pokemon, dominantColor = dominantColor)
    }

    fun removeFromFavorites( pokemon: Pokemon) = viewModelScope.launch {
        removePokemonFromFavoritesUseCase.invoke(pokemon = pokemon)
    }

}

