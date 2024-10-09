package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokedex.presentation.screens.FavoritePokemonListScreen
import com.example.pokedex.presentation.screens.FavoritePokemons
import com.example.pokedex.presentation.screens.Home
import com.example.pokedex.presentation.screens.HomeScreen
import com.example.pokedex.presentation.screens.PokemonDetails
import com.example.pokedex.presentation.screens.PokemonDetailsSScreen
import com.example.pokedex.presentation.viewmodel.FavoritePokemonViewModel
import com.example.pokedex.presentation.viewmodel.MainViewModel
import com.example.pokedex.presentation.viewmodel.PokemonDetailsViewModel
import com.example.pokedex.ui.theme.PokeDexTheme
import com.example.pokedex.ui.theme.SoftBeige
import com.example.pokedex.utils.hexStringToColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeDexTheme {
                var containerColor by remember { mutableStateOf(SoftBeige) }
                val navController = rememberNavController()
                Scaffold(
                    containerColor = containerColor,
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SharedTransitionLayout {

                        NavHost(
                            navController = navController,
                            startDestination = Home,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable<Home> {
                                val viewModel by viewModels<MainViewModel>()
                                LaunchedEffect(Unit) {
                                    containerColor = SoftBeige
                                }
                                HomeScreen(
                                    animatedVisibilityScope = this@composable,
                                    onPokemonSelected = { pokemonName, dominantColor ->
                                        navController.navigate(
                                            PokemonDetails(
                                                pokemonName = pokemonName,
                                                dominantColor = dominantColor
                                            )
                                        )
                                    },
                                    screenState = viewModel.viewState,
                                    fetchInitialPokemonPage = viewModel::fetchPokemonList,
                                    fetchNextPokemonPage = viewModel::fetchNextPokemonList,
                                    calDominantColor = viewModel::calculateDominantColor,
                                    onListIconClick = {
                                        navController.navigate(
                                            FavoritePokemonListScreen
                                        )
                                    }
                                )
                            }

                            composable<PokemonDetails> { navBackStackEntry ->
                                val pokemonDetails = navBackStackEntry.toRoute<PokemonDetails>()
                                val viewModel by viewModels<PokemonDetailsViewModel>()
                                LaunchedEffect(Unit) {
                                    containerColor = hexStringToColor(pokemonDetails.dominantColor)
                                }
                                PokemonDetailsSScreen(
                                    animatedVisibilityScope = this@composable,
                                    pokemonName = pokemonDetails.pokemonName,
                                    dominantColor = hexStringToColor(pokemonDetails.dominantColor),
                                    viewState = viewModel.viewState,
                                    onBackClicked = {
                                        navController.navigateUp()
                                    },
                                    fetchDetails = viewModel::fetchPokemonDetails,
                                    pokemonEvolutionState = viewModel.pokemonEvolutionState,
                                    fetchEvolutionChain = viewModel::fetchPokemonEvolutionChain,
                                    onAddToFavorite = viewModel::addPokemonToFavoriteList,
                                    removeFromFavorite = viewModel::removeFromFavorites
                                )
                            }

                            composable<FavoritePokemonListScreen> {
                                val viewModel by viewModels<FavoritePokemonViewModel>()
                                LaunchedEffect(Unit) {
                                    containerColor = SoftBeige
                                }
                                FavoritePokemons(
                                    animatedVisibilityScope = this@composable,
                                    onBackClick = {
                                        navController.navigateUp()
                                    },
                                    viewState = viewModel.viewState,
                                    fetchFavoritePokemons = viewModel::getFavoritePokemons,
                                    onItemClick = { pokemonName, dominantColor ->
                                        navController.navigate(
                                            PokemonDetails(
                                                pokemonName = pokemonName,
                                                dominantColor = dominantColor
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}



