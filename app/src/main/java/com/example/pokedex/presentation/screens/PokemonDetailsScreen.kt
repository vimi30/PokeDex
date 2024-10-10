package com.example.pokedex.presentation.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.pokedex.R
import com.example.pokedex.data.models.domain.evolutionchain.EvolutionStage
import com.example.pokedex.data.models.domain.pokemon.Pokemon
import com.example.pokedex.presentation.components.PokemonAbout
import com.example.pokedex.presentation.components.PokemonEvolution
import com.example.pokedex.presentation.components.PokemonStats
import com.example.pokedex.presentation.components.commons.CustomSnackbar
import com.example.pokedex.presentation.components.commons.ErrorComponent
import com.example.pokedex.presentation.components.commons.LoadingState
import com.example.pokedex.presentation.components.commons.NavigationIconComponent
import com.example.pokedex.presentation.components.commons.PokemonNameComponent
import com.example.pokedex.ui.theme.GhostWhite
import com.example.pokedex.ui.theme.SoftBeige
import com.example.pokedex.utils.UiState
import com.example.pokedex.utils.capitalizeName
import com.example.pokedex.utils.getContrastingTextColor
import com.example.pokedex.utils.toHexString
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.PokemonDetailsSScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonName: String,
    dominantColor: Color,
    viewState: StateFlow<UiState<Pokemon>>,
    onBackClicked: () -> Unit,
    fetchDetails: (String) -> Unit,
    pokemonEvolutionState: StateFlow<UiState<List<EvolutionStage>>>,
    fetchEvolutionChain: (Int) -> Unit,
    onAddToFavorite: (Pokemon, String) -> Unit,
    removeFromFavorite: (Pokemon) -> Unit
) {
    val screenState by viewState.collectAsStateWithLifecycle()

    var isFavorite by remember { mutableStateOf(false) }

    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("About", "Base Stats", "Evolution")

    LaunchedEffect(Unit) {
        fetchDetails(pokemonName)
    }

    LaunchedEffect(screenState) {
        if (screenState is UiState.Success) {
            val currentPokemon = (screenState as UiState.Success<Pokemon>).data
            isFavorite = currentPokemon.isFavorite
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = dominantColor,
        contentWindowInsets = WindowInsets(0.dp),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    CustomSnackbar(
                        snackbarData = snackbarData,
                        backgroundColor = SoftBeige,
                        contentColor = Color.Black
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp)
                    .fillMaxSize()
            )
            {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NavigationIconComponent(
                        icon = painterResource(R.drawable.arrow_back),
                        modifier = Modifier,
                        onClick = { onBackClicked() },
                        iconTint = getContrastingTextColor(dominantColor)
                    )

                    if (screenState is UiState.Success) {
                        val currentPokemon = (screenState as UiState.Success).data
                        NavigationIconComponent(
                            icon = if (isFavorite) {
                                painterResource(R.drawable.added_to_favorite_icon)
                            } else {
                                painterResource(R.drawable.add_to_favorite_icon)
                            },
                            modifier = Modifier,

                            onClick = {
                                if (isFavorite) {
                                    removeFromFavorite(
                                        currentPokemon
                                    )
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "Pokemon removed from favorites"
                                        )

                                    }
                                } else {
                                    onAddToFavorite(
                                        currentPokemon,
                                        dominantColor.toHexString()
                                    )
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "Pokemon added to favorites"
                                        )
                                    }

                                }
                                isFavorite = !isFavorite

                            },
                            iconTint = getContrastingTextColor(dominantColor)
                        )
                    }
                }

                when (val state = screenState) {
                    is UiState.Error -> {
                        ErrorComponent(state.errorMessage)
                    }

                    UiState.Loading -> {
                        LoadingState()
                    }

                    is UiState.Success -> {
                        PokemonNameComponent(
                            animatedVisibilityScope = animatedVisibilityScope,
                            name = capitalizeName(state.data.name),
                            textColor = getContrastingTextColor(dominantColor),
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            state.data.types.forEach { type ->
                                ElevatedFilterChip(
                                    colors = FilterChipDefaults.filterChipColors(
                                        dominantColor,
                                    ),
                                    label = {
                                        Text(
                                            text = capitalizeName(type.name),
                                            color = getContrastingTextColor(dominantColor)
                                        )
                                    },
                                    onClick = {},
                                    selected = false,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxHeight(0.7f)
                                    .offset(y = 120.dp),
                                colors = CardDefaults.cardColors(GhostWhite),
                                elevation = CardDefaults.cardElevation(6.dp),
                                shape = RoundedCornerShape(12.dp),

                                ) {

                                Spacer(modifier = Modifier.height(16.dp))
                                TabRow(
                                    selectedTabIndex = tabIndex,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    tabs.forEachIndexed { index: Int, title: String ->
                                        Tab(
                                            text = { Text(title) },
                                            selected = tabIndex == index,
                                            onClick = { tabIndex = index }
                                        )
                                    }

                                }
                                when (tabIndex) {
                                    0 -> PokemonAbout(state.data)
                                    1 -> PokemonStats(state.data.stats)
                                    2 -> PokemonEvolution(
                                        pokemonId = state.data.id,
                                        pokemonEvolutionState = pokemonEvolutionState,
                                        fetchEvolutionChain = fetchEvolutionChain,
                                        dominantColor = dominantColor
                                    )
                                }
                            }
                            SubcomposeAsyncImage(
                                model = state.data.imageUrl,
                                contentScale = ContentScale.Crop,
                                contentDescription = "Pokemon Image",
                                modifier = Modifier
                                    .sharedElement(
                                        state = rememberSharedContentState(key = state.data.imageUrl),
                                        animatedVisibilityScope = animatedVisibilityScope
                                    )
                                    .fillMaxWidth(0.5f)
                                    .aspectRatio(1f),
                                loading = { LoadingState(modifier = Modifier.size(20.dp)) }
                            )

                        }
                    }
                }

            }
        }
    )

}