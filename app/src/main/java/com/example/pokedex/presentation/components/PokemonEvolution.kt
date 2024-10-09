package com.example.pokedex.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedex.R
import com.example.pokedex.presentation.components.commons.ErrorComponent
import com.example.pokedex.presentation.components.commons.LoadingState
import com.example.pokedex.data.models.domain.evolutionchain.EvolutionStage
import com.example.pokedex.utils.UiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PokemonEvolution(
    pokemonId: Int,
    pokemonEvolutionState: StateFlow<UiState<List<EvolutionStage>>>,
    fetchEvolutionChain: (Int) -> Unit
) {

    val viewState by pokemonEvolutionState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        fetchEvolutionChain(pokemonId)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val state = viewState) {
            is UiState.Error -> {
                ErrorComponent(state.errorMessage)
            }

            UiState.Loading -> {
                LoadingState(modifier = Modifier.size(20.dp))
            }

            is UiState.Success -> {

                LazyVerticalGrid(
                    contentPadding = PaddingValues(all = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    columns = GridCells.Fixed(2),
                    content = {
                        itemsIndexed(
                            items = state.data,
                        ) { index, item ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                PokemonEvolutionComponent(
                                    evolutionStage = item
                                )
                                if (index != state.data.size - 1) {
                                    Icon(
                                        painter = painterResource(R.drawable.double_arrow_right),
                                        contentDescription = "arrow right"
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    }

}