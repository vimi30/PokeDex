package com.example.pokedex.presentation.screens

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedex.R
import com.example.pokedex.data.models.domain.pokemonList.SimplePokemon
import com.example.pokedex.presentation.components.PokemonGridItem
import com.example.pokedex.presentation.components.commons.ErrorComponent
import com.example.pokedex.presentation.components.commons.LoadingState
import com.example.pokedex.presentation.components.commons.NavigationIconComponent
import com.example.pokedex.presentation.components.commons.PokemonNameComponent
import com.example.pokedex.utils.UiState
import kotlinx.coroutines.flow.StateFlow


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onPokemonSelected: (String, String) -> Unit,
    screenState: StateFlow<UiState<List<SimplePokemon>>>,
    fetchInitialPokemonPage: () -> Unit,
    fetchNextPokemonPage: () -> Unit,
    calDominantColor: (Drawable, (Color) -> Unit) -> Unit,
    onListIconClick: () -> Unit
) {
    val viewState by screenState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        fetchInitialPokemonPage()
    })

    val scrollState = rememberLazyGridState()

    val fetchNextPage: Boolean by remember {
        derivedStateOf {
            val currentPokemonCount =
                (viewState as? UiState.Success)?.data?.size
                    ?: return@derivedStateOf false

            val lastDisplayedIndex = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: return@derivedStateOf false

            return@derivedStateOf lastDisplayedIndex >= currentPokemonCount - 10
        }
    }

    LaunchedEffect(key1 = fetchNextPage, block = {
        if (fetchNextPage) fetchNextPokemonPage()
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val state = viewState) {
            is UiState.Success -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PokemonNameComponent(
                        animatedVisibilityScope = animatedVisibilityScope,
                        name = "Pokemons",
                        textColor = Color.DarkGray,
                        fontSize = 28.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)

                    )
                    NavigationIconComponent(
                        icon = painterResource(R.drawable.list_icon),
                        modifier = Modifier
                            .padding(8.dp),
                        onClick = { onListIconClick() },
                        iconTint = Color.Black
                    )
                }

                LazyVerticalGrid(
                    state = scrollState,
                    contentPadding = PaddingValues(all = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(
                            items = state.data,
                            key = { it.hashCode() }
                        ) { pokemon ->
                            PokemonGridItem(
                                animatedVisibilityScope = animatedVisibilityScope,
                                modifier = Modifier,
                                pokemon = pokemon,
                                calDominantColor = calDominantColor,
                                onClick = { dominantColor ->
                                    onPokemonSelected(pokemon.name, dominantColor)
                                }
                            )
                        }
                    }
                )
            }

            UiState.Loading -> LoadingState()
            is UiState.Error -> {
                ErrorComponent(errorMessage = state.errorMessage)
            }
        }


    }

}