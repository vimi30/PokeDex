package com.example.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.data.network.response.onError
import com.example.pokedex.data.network.response.onSuccess
import com.example.pokedex.domain.usecases.GetFavoritePokemonUseCase
import com.example.pokedex.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritePokemonViewModel @Inject constructor(private val getFavoritePokemonUseCase: GetFavoritePokemonUseCase) :
    ViewModel() {

    private val _viewState = MutableStateFlow<UiState<List<Pokemon>>>(UiState.Loading)

    val viewState = _viewState.asStateFlow()

    fun getFavoritePokemons() = viewModelScope.launch {
        getFavoritePokemonUseCase()
            .onSuccess { pokemons ->
                _viewState.update {
                    UiState.Success(pokemons)
                }
            }
            .onError { error ->
                _viewState.update {
                    UiState.Error(error.message)
                }
            }
    }

}