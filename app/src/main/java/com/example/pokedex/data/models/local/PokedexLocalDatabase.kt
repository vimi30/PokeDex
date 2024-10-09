package com.example.pokedex.data.models.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.data.models.local.dao.PokemonDao
import com.example.pokedex.data.models.local.entities.AbilityEntity
import com.example.pokedex.data.models.local.entities.FormEntity
import com.example.pokedex.data.models.local.entities.MoveEntity
import com.example.pokedex.data.models.local.entities.PokemonEntity
import com.example.pokedex.data.models.local.entities.SpeciesEntity
import com.example.pokedex.data.models.local.entities.StatEntity
import com.example.pokedex.data.models.local.entities.TypeEntity

@Database(
    entities = [PokemonEntity::class, AbilityEntity::class, MoveEntity::class, TypeEntity::class, StatEntity::class, SpeciesEntity::class, FormEntity::class],
    version = 1
)
abstract class PokedexLocalDatabase : RoomDatabase() {
    abstract fun pokemonDoa(): PokemonDao
}