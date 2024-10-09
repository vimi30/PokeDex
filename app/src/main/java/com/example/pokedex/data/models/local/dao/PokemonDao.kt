package com.example.pokedex.data.models.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.pokedex.data.models.local.entities.AbilityEntity
import com.example.pokedex.data.models.local.entities.FormEntity
import com.example.pokedex.data.models.local.entities.MoveEntity
import com.example.pokedex.data.models.local.entities.PokemonEntity
import com.example.pokedex.data.models.local.entities.SpeciesEntity
import com.example.pokedex.data.models.local.entities.StatEntity
import com.example.pokedex.data.models.local.entities.TypeEntity
import com.example.pokedex.data.models.local.relationships.PokemonWithDetails

@Dao
interface PokemonDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilities: List<AbilityEntity> )

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(types: List<TypeEntity>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoves(moves: List<MoveEntity>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: List<StatEntity>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForms(forms: List<FormEntity>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: SpeciesEntity)

    @Transaction
    @Query("SELECT *  From pokemons Where isFavorite is 1")
    suspend fun getAllFavoritePokemon(): List<PokemonWithDetails>

    @Transaction
    @Query("SELECT *  From pokemons Where name = :pokemonName")
    suspend fun getPokemonDetailsByName(pokemonName: String): PokemonWithDetails?

}