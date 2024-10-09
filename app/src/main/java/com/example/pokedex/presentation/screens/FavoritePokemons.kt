package com.example.pokedex.presentation.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedex.R
import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.presentation.components.FavoriteListItem
import com.example.pokedex.presentation.components.commons.ErrorComponent
import com.example.pokedex.presentation.components.commons.LoadingState
import com.example.pokedex.presentation.components.commons.NavigationIconComponent
import com.example.pokedex.presentation.components.commons.PokemonNameComponent
import com.example.pokedex.utils.UiState
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FavoritePokemons(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBackClick: () -> Unit,
    viewState: StateFlow<UiState<List<Pokemon>>>,
    fetchFavoritePokemons: () -> Unit,
    onItemClick: (String, String) -> Unit
) {
    val screenState by viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        fetchFavoritePokemons()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            NavigationIconComponent(
                icon = painterResource(R.drawable.arrow_back),
                iconTint = Color.Black,
                onClick = { onBackClick() },
                modifier = Modifier
            )
            PokemonNameComponent(
                animatedVisibilityScope = animatedVisibilityScope,
                name = "Favorites",
                modifier = Modifier,
                fontSize = 20.sp,
                textColor = Color.DarkGray
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            when (val state = screenState) {
                is UiState.Error -> {
                    item {
                        ErrorComponent(
                            state.errorMessage
                        )
                    }
                }

                UiState.Loading -> {
                    item {
                        LoadingState()
                    }
                }

                is UiState.Success -> {
                    items(
                        state.data
                    ) { pokemon ->
                        FavoriteListItem(
                            animatedVisibilityScope = animatedVisibilityScope,
                            pokemon = pokemon,
                            onClick = onItemClick
                        )
                    }
                }
            }

        }

    }
}