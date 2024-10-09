package com.example.pokedex.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.data.models.local.PokedexLocalDatabase
import com.example.pokedex.data.models.local.dao.PokemonDao
import com.example.pokedex.data.network.NetworkService
import com.example.pokedex.domain.repository.PokeRepository
import com.example.pokedex.domain.usecases.GetSimplePokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            defaultRequest { url("https://pokeapi.co/api/v2/") }
            install(Logging) {
                logger = Logger.ANDROID
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    @Provides
    @Singleton
    fun providerNetworkService(httpClient: HttpClient) = NetworkService(client = httpClient)

    @Provides
    @Singleton
    fun provideLocalDatabase( @ApplicationContext context: Context): PokedexLocalDatabase{
        return Room.databaseBuilder(
            context = context,
            PokedexLocalDatabase::class.java,
            name = "local_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(pokedexLocalDatabase: PokedexLocalDatabase): PokemonDao {
        return pokedexLocalDatabase.pokemonDoa()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(networkService: NetworkService, pokemonDao: PokemonDao) =
        PokeRepository(networkService = networkService, pokemonDao = pokemonDao)

    @Provides
    fun provideGetSimplePokemonListUseCase(repository: PokeRepository) =
        GetSimplePokemonListUseCase(repository = repository)

}