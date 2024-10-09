package com.example.pokedex.presentation.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.pokedex.data.models.domain.pokemonList.PokemonList
import com.example.pokedex.data.models.domain.pokemonList.SimplePokemon
import com.example.pokedex.data.network.response.onError
import com.example.pokedex.data.network.response.onSuccess
import com.example.pokedex.domain.usecases.GetSimplePokemonListUseCase
import com.example.pokedex.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getSimplePokemonListUseCase: GetSimplePokemonListUseCase) :
    ViewModel() {

    private val defaultOffset = 20

    private val _viewState = MutableStateFlow<UiState<List<SimplePokemon>>>(UiState.Loading)

    val viewState = _viewState.asStateFlow()

    private val fetchedPokemonListPages = mutableListOf<PokemonList>()

    fun fetchPokemonList() = viewModelScope.launch {
        if (fetchedPokemonListPages.isNotEmpty()) return@launch
        getSimplePokemonListUseCase(0)
            .onSuccess { pokemonList ->
                fetchedPokemonListPages.clear()
                fetchedPokemonListPages.add(pokemonList)
                _viewState.update {
                    UiState.Success(data = pokemonList.results)
                }
            }
            .onError { error ->
                _viewState.update {
                    UiState.Error(error.message)
                }

            }

    }

    fun fetchNextPokemonList() = viewModelScope.launch {
        val nextOffset = fetchedPokemonListPages.size * defaultOffset
        getSimplePokemonListUseCase(nextOffset)
            .onSuccess { pokemonList ->
                fetchedPokemonListPages.add(pokemonList)
                _viewState.update { currentState ->
                    val currentPokemon = (currentState as? UiState.Success)?.data ?: emptyList()
                    UiState.Success(data = currentPokemon + pokemonList.results)
                }
            }
            .onError { error ->
                _viewState.update {
                    UiState.Error(error.message)
                }

            }
    }

    fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bitmap).generate { palette ->
            palette?.lightVibrantSwatch?.rgb?.let {
                onFinish(Color(it))
            }
        }
    }

}